package de.mpg.mpi.uima.engines.salie

import de.mpg.mpi.uima.`type`.{Constituent, MinIEConstituent, MinIEOpenFact, SalIEOpenFact}
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Token
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * For each MinIEOpenFact it generates a proper SalIEOpenFact that is used for fact salience computation.
  * SalIEOpenFact is a simplified version of MinIEOpenFact that incorporate negative words into the
  * fact's relation, it has a salient score and it also contains the respective MinIEOpenFact from
  * which it has been generated.
  *
  */
class MinIEFacts2SalIEFactsAnalysisEngine extends JCasAnnotator_ImplBase {
  private val logger = LoggerFactory.getLogger(classOf[MinIEFacts2SalIEFactsAnalysisEngine])


  override def process(jCas: JCas) = {
    val minieFacts = JCasUtil.select(jCas, classOf[MinIEOpenFact]).asScala

    minieFacts
      .filter(minieFact => isValidMinIEOpenFact(minieFact))
      .foreach(minieFact => addSalieOpenFact(jCas, minieFact))
  }


  /**
    * SalIEOpenFact will be created only for MinIEOpenFact that satisfy this function.
    *
    * @param minIEOpenFact
    * @return
    */
  private def isValidMinIEOpenFact(minIEOpenFact: MinIEOpenFact) : Boolean = {
    if(minIEOpenFact.getText.contains("QUANT_")) return false   // quantities are usually not so informative

    // check fact is no implicit
    isValidConstituent(minIEOpenFact.getSubject) && isValidConstituent(minIEOpenFact.getRelation) &&
      isValidConstituent(minIEOpenFact.getObject)
  }

  private def isValidConstituent(constituent: Constituent) : Boolean = {
    constituent.getBegin >= 0 && constituent.getEnd >= 0  // check all positions make sense (no implicit facts)
  }


  /**
    * Creates a SalIEOpenFact from a MinIEOpenFact.
    *
    * @param jCas
    * @param minieOpenFact
    * @return
    */
  private def addSalieOpenFact(jCas: JCas, minieOpenFact: MinIEOpenFact) = {
    val salieOpenFact = new SalIEOpenFact(jCas, minieOpenFact.getBegin, minieOpenFact.getEnd)


    // setting constituents

    salieOpenFact.setSubject( minieConstituent2Constituent(jCas, minieOpenFact.getSubject.asInstanceOf[MinIEConstituent]) )

    salieOpenFact.setRelation( minieRelation2Constituent(
      jCas, minieOpenFact.getRelation.asInstanceOf[MinIEConstituent], minieOpenFact.getPolarity.getNegativeTokens) )

    salieOpenFact.setObject( minieConstituent2Constituent(jCas, minieOpenFact.getObject.asInstanceOf[MinIEConstituent]) )


    // finalizing

    salieOpenFact.setText( "%s %s %s".format(
      salieOpenFact.getSubject.getText, salieOpenFact.getRelation.getText, salieOpenFact.getObject.getText)
    )

    salieOpenFact.setMinieOpenFact(minieOpenFact)

    salieOpenFact.addToIndexes()
    salieOpenFact

  }


  /**
    * Used for converting a relation (i.e. MinIEConstituent) into a simpler constituent. This function also
    * re-incorporate the negative tokens dropped by MinIE into the new constituent.
    *
    * @param jCas
    * @param minieRelation
    * @param negativeTokens
    * @return
    */
  private def minieRelation2Constituent(jCas: JCas, minieRelation: MinIEConstituent, negativeTokens: FSArray) = {
    val constituent = new Constituent(jCas, minieRelation.getBegin, minieRelation.getEnd)

    constituent.setTokens( copyTokens(jCas, List(minieRelation.getTokens, negativeTokens)) )
    constituent.setHead(minieRelation.getHead)
    constituent.setText( tokens2string(constituent.getTokens) )

    constituent.addToIndexes()
    constituent
  }


  /**
    * Used for converting MinIEOpenFact's subject or object into standard and simpler constituent.
    * For relation a different method is used (for re-incorporate into the fact's relation the dropped negative words).
    *
    * @param jCas
    * @param minieConstituent
    */
  private def minieConstituent2Constituent(jCas: JCas, minieConstituent: MinIEConstituent) = {
    val constituent = new Constituent(jCas, minieConstituent.getBegin, minieConstituent.getEnd)

    constituent.setTokens( copyTokens(jCas, minieConstituent.getTokens) )
    constituent.setHead(minieConstituent.getHead)
    constituent.setText( tokens2string(constituent.getTokens) )

    constituent.addToIndexes()
    constituent
  }


  /**
    * Returns a copy of a list of tokens into another single FSArray.
    * @param jCas
    * @param tokens
    * @return
    */
  private def copyTokens(jCas: JCas, tokens: List[FSArray]) = {

    val flatTokens = tokens.flatMap(fsArray => fsArray2List(fsArray))
    val copiedTokens = new FSArray(jCas, flatTokens.size)
    (0 until flatTokens.size).foreach(i => copiedTokens.set(i, flatTokens(i)))

    copiedTokens.addToIndexes()
    copiedTokens
  }

  private def copyTokens(jCas: JCas, tokens: FSArray) : FSArray = copyTokens(jCas, List(tokens))


  private def fsArray2List(tokens : FSArray) = {
    (0 until tokens.size()).map(i => tokens.get(i).asInstanceOf[Token]).sortBy(token => token.getBegin).toList
  }


  /**
    * Given an array of tokens, it returns its conversion to string.
    * @param tokens
    * @return
    */
  private def tokens2string(tokens: FSArray) = {
    (0 until tokens.size())
      .map(i => tokens.get(i).asInstanceOf[Token])
      .sortBy(t => t.getBegin)
      .map(t => t.getCoveredText) mkString " "
  }

}
