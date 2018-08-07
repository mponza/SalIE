package de.mpg.mpi.uima.engines

import de.mpg.mpi.uima.`type`.{Constituent, MinIEOpenFact}
import de.mpg.mpi.uima.utils.SemanticSentences
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Token
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.internal.TokenKey
import de.uni_mannheim.minie.MinIE
import de.uni_mannheim.minie.annotation.AnnotatedPhrase
import edu.stanford.nlp.semgraph.SemanticGraph
import org.apache.uima.UimaContext
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.descriptor.ConfigurationParameter
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class MinIEAnalysisEngine extends JCasAnnotator_ImplBase {

  private val logger = LoggerFactory.getLogger(classOf[MinIEAnalysisEngine])

  @ConfigurationParameter
  var mode: String = null

  override def initialize(context: UimaContext): Unit = {
    super.initialize(context)
  }


  /**
    * Casts mode string into proper MinIE.Mode enumerator type.
    * @return
    */
  private def getMinIEMode() : MinIE.Mode = {
    if(mode.equals("agg") || mode.equals("aggressive")) return MinIE.Mode.AGGRESSIVE
    if(mode.equals("dict") || mode.equals("dictionary")) return MinIE.Mode.DICTIONARY
    if(mode.equals("safe")) return MinIE.Mode.SAFE
    if(mode.equals("comp") || mode.equals("complete")) return MinIE.Mode.COMPLETE

    throw new IllegalAccessException("MinIE mode parameter \"%s\" is not valid.".format(mode))
  }


  /**
    * Processes a jCas and extend it with MinIE's open facts.
    * @param jCas
    */
  override def process(jCas: JCas) = {
    val semanticSentences = new SemanticSentences(jCas)
    val sentences = JCasUtil.select(jCas, classOf[Sentence]).asScala

    for(sentence: Sentence <- sentences) {
      val semanticGraph = semanticSentences.getSemanticGraph(sentence)
      if(semanticGraph != null) {

        val minIE = new MinIE(sentence.getCoveredText, semanticGraph, getMinIEMode)

        val validPropositions = minIE.getPropositions.stream().forEach(
          proposition => {

            try {

              // Creation of OpenFact as proposition

              val openFact = new MinIEOpenFact(jCas)

              // subject
              val subject = addConstituent2JCas(jCas, proposition.getSubject, semanticGraph)
              openFact.setSubject(subject)
              openFact.setBegin(subject.getBegin)

              // relation
              val relation = addConstituent2JCas(jCas, proposition.getRelation, semanticGraph)
              openFact.setRelation(relation)

              // object
              val objectFact = addConstituent2JCas(jCas, proposition.getObject, semanticGraph)
              openFact.setObject(objectFact)
              openFact.setEnd(objectFact.getEnd)

              // semantic annotations
              openFact.setPolarity(proposition.getPolarity.toString)
              openFact.setModality(proposition.getModality.toString)
              openFact.setAttribution(proposition.getAttribution.toString)
              openFact.setQuantity(proposition.getAttribution.toString)

              openFact.setText("(%s, %s, %s) [ Attribution: %s, Polarity: %s, Modality: %s, Quantity: %s ]"
                .format(subject.getText, relation.getText, objectFact.getText,
                  openFact.getAttribution, openFact.getPolarity,
                  openFact.getModality, openFact.getQuantity
                )
              )

              openFact.addToIndexes()

            } catch {
              case e: Exception =>
                logger.warn("Error with proposition %s in sentence %s".format(
                                                            proposition, sentence.getCoveredText))
                e.printStackTrace()
            }
        }
      )

      }
    }
  }


  /**
    * Creates and returns a constituent of class clazz given a phrase and the sentence semantic graph.
    *
    * @param jCas
    * @param phrase
    * @param semanticGraph
    * @tparam T
    */
  private def addConstituent2JCas[T](jCas: JCas, phrase: AnnotatedPhrase, semanticGraph: SemanticGraph) = {
    val words = phrase.getWordList
    var begin = Integer.MAX_VALUE
    var end = -1

    // setting constituent tokens

    val tokens = new FSArray(jCas, words.size())
    words.asScala.zipWithIndex.foreach(
      indexedWord => {

        val word = indexedWord._1
        val index = indexedWord._2


        // adding constituent token

        var token = new Token(jCas)
        if(word.get(classOf[TokenKey]) == null) {
          token.setId(word.value)
        } else {
          token = word.get(classOf[TokenKey])
        }
        tokens.set(index, token)


        // constituent begin/end update

        if(token.getBegin < begin) {
          begin = token.getBegin
        }
        if(token.getEnd + 1 > end) {
          end = token.getEnd
        }
      }
    )
    tokens.addToIndexes()
    val constituent = new Constituent(jCas, begin, end)
    constituent.setTokens(tokens)


    // setting text

    constituent.setText(phrase.toString)


    // setting head by properly managing erroneous cases

    val head = phrase.getHead(semanticGraph)
    var tokenHead = new Token(jCas)
    if(head != null && head.get(classOf[TokenKey]) == null) {
      tokenHead.setId(head.value())
      tokenHead.setBegin(head.beginPosition())
      tokenHead.setEnd(head.endPosition())
    } else {
      tokenHead = if (head != null) { head.get(classOf[TokenKey]) } else { null }
    }
    constituent.setHead(tokenHead)

    jCas.addFsToIndexes(constituent)
    constituent
  }


//  protected static void addNegativeWord(AnnotatedProposition ap) {
//    // to be tested
//    ObjectArrayList<IndexedWord> words = ap.getPolarity().getNegativeWords();
//    words.addAll( ap.getRelation().getWordList() );
//
//    words.sort(new Comparator<IndexedWord>() {
//      @Override
//      public int compare(IndexedWord o1, IndexedWord o2) {
//        return Integer.compare(o1.beginPosition(), o2.beginPosition());
//      }
//    });
//
//    ap.getRelation().setWordList( words );
//  }
//
//
//
//  protected boolean validConstituent(AnnotatedPhrase ap) {
//
//    // logger.info(ap.getWordList().stream().map(x -> x.toString()).collect(Collectors.joining(" ")));
//
//    if(ap.toString().contains("QUANT_")) return false;  // we don't wont fact in this form
//
//    return ap.getBegin() >= 0 && ap.getEnd() >= 0;
//  }
//
//
}


object MinIEAnalysisEngine {

  var PARAM_MODE: String = "safe"

}
