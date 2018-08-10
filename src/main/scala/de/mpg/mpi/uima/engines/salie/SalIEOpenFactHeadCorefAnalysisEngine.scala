package de.mpg.mpi.uima.engines.salie

import de.mpg.mpi.uima.`type`.{Constituent, SalIEOpenFact}
import de.mpg.mpi.uima.utils.{SemanticSentences, StopWordUtils}
import de.tudarmstadt.ukp.dkpro.core.api.coref.`type`.CoreferenceChain
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Token
import edu.stanford.nlp.ling.IndexedWord
import edu.stanford.nlp.semgraph.SemanticGraph
import edu.stanford.nlp.trees.EnglishGrammaticalRelations
import it.unimi.dsi.fastutil.objects.{Object2ObjectOpenHashMap, ObjectOpenHashSet}
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class SalIEOpenFactHeadCorefAnalysisEngine extends JCasAnnotator_ImplBase {

  private val logger = LoggerFactory.getLogger(classOf[SalIEOpenFactHeadCorefAnalysisEngine])

  override def process(jCas: JCas) = {
    val semanticSentences = new SemanticSentences(jCas)

    val token2corefHead = new Object2ObjectOpenHashMap[Token, Token]()


    //
    // Collects coreference information by assigning to each token its corresponding coreferenced token head.

    for(corefChain <- JCasUtil.select(jCas, classOf[CoreferenceChain]).asScala) {

      var link = corefChain.getFirst
      logger.info(link.toString)
      // val corefTokenHead = JCasUtil.selectAt(jCas, classOf[Token], link.getBegin, link.getEnd).get(0)
      var corefTokenHead = JCasUtil.selectCovered(jCas, classOf[Token], link.getBegin, link.getEnd).get(0)


      // Trying to see if coref from here to somewherelse?
      // Maybe token2corefHead.containsKey(corefTokenHead) == true we can maybe do something?
      // Anyway the actual implementation is safer.


      if( !StopWordUtils.isStopWord(corefTokenHead.getCoveredText) &&
        corefTokenHead.getPos.getPosValue.startsWith("NN") ) {

        // the real corefTokenHead is the one highest in the dependency tree from corefTokenHead

        val semanticGraph = semanticSentences.getSemanticGraph(corefTokenHead)
        val corefHighIndexedWord = token2HighestNounCompoundIndexedWord(corefTokenHead, semanticGraph)
        corefTokenHead = JCasUtil.selectSingleAt(jCas, classOf[Token],
          corefHighIndexedWord.beginPosition(), corefHighIndexedWord.endPosition())


        // Saves all mentions that can be coreferenced by corefTokenHead

        while(link.getNext != null) {
          link = link.getNext
          if (!link.getReferenceType.equals("NOMINAL")) {

            val tokenMention = JCasUtil.selectSingleAt(jCas, classOf[Token], link.getBegin, link.getEnd)
            if( StopWordUtils.isPronoun(tokenMention.getCoveredText) ) {
              token2corefHead.put(tokenMention, corefTokenHead)
            }

          }
        }
      }
    }


    //
    // Updates open fact's subjects with the proper coreferenced tokens.

    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala
      .foreach(salIEOpenFact => corefSubjectSalIEOpenFact(salIEOpenFact, token2corefHead))

  }


  /**
    * Coreferences the salIEOpenFact subject by updating subject's tokens, head and text
    * with the new coreferenced tokens/
    *
    * @param salIEOpenFact
    * @param token2corefHead
    */
  private def corefSubjectSalIEOpenFact(salIEOpenFact: SalIEOpenFact,
                                        token2corefHead: Object2ObjectOpenHashMap[Token, Token]) = {

    val factTokens = new ObjectOpenHashSet[Token]()
    factTokens.addAll( constituent2tokens( salIEOpenFact.getSubject ).asJavaCollection )
    factTokens.addAll( constituent2tokens( salIEOpenFact.getRelation ).asJavaCollection )
    factTokens.addAll( constituent2tokens( salIEOpenFact.getObject ).asJavaCollection )


    val subject = salIEOpenFact.getSubject
    val subjectTokens = constituent2tokens(salIEOpenFact.getSubject)
    var subjectHead = subject.getHead

    // new subjects tokens, properly coreferenced
    val corefSubjectTokens = subjectTokens.map(
      token => {

        if(!token2corefHead.containsKey(token)) token // not to be coreferenced
        else {

          val corefToken = token2corefHead.get(token)

          // not to be coreferenced again, we avoid coreference within same fact because otherwise
          // it generates weirdly readable coreferenced facts
          if (factTokens.contains(corefToken)) token

          else {

            // we are coreferencing the head
            if(token.equals(subjectHead)) {
              subjectHead = corefToken
            }

            corefToken
          }
        }
    })

    subject.setHead(subjectHead)
    Range(0, corefSubjectTokens.size).foreach(i => subject.getTokens.set(i, corefSubjectTokens(i)))
    subject.setText( corefSubjectTokens.map(token => token.getCoveredText) mkString(" ") )

    salIEOpenFact.setText("%s %s %s".format(
      salIEOpenFact.getSubject.getText,
      salIEOpenFact.getRelation.getText,
      salIEOpenFact.getObject.getText
    ))

  }

  private def constituent2tokens(constituent: Constituent) = {
    Range(0, constituent.getTokens.size()).map(i => constituent.getTokens.get(i).asInstanceOf[Token]).toList
  }


  /**
    * Returns the highest token which is a noun compound modifier
    *
    * @param token
    * @param semanticGraph
    * @return
    */
  private def token2HighestNounCompoundIndexedWord(token: Token, semanticGraph: SemanticGraph) : IndexedWord = {
    val indexedWord = semanticGraph.vertexListSorted.stream()
      .filter(x => token.getBegin == x.beginPosition() && token.getEnd == token.getEnd)
      .findFirst

    if(!indexedWord.isPresent) {
      return null // should not happen
    }


    // maybe easier by using dkpro Dependency directly

    val highestIndexedWord = semanticGraph.getIncomingEdgesSorted(indexedWord.get()).stream()
      .filter( x => x.getRelation.equals(EnglishGrammaticalRelations.NOUN_COMPOUND_MODIFIER))
      .findFirst

    if(highestIndexedWord.isPresent) return highestIndexedWord.get.getGovernor
    indexedWord.get()
  }


}
