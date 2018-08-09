package de.mpg.mpi.uima.engines.minie

import de.mpg.mpi.uima.`type`.{Constituent, MinIEOpenFact}
import de.mpg.mpi.uima.utils.SemanticSentences
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.{Sentence, Token}
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.internal.TokenKey
import de.uni_mannheim.minie.MinIE
import de.uni_mannheim.minie.annotation.{AnnotatedPhrase, AnnotatedProposition}
import edu.stanford.nlp.semgraph.SemanticGraph
import org.apache.uima.UimaContext
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.descriptor.ConfigurationParameter
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer


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
    val sentences = JCasUtil.select(jCas, classOf[Sentence])

    for(sentence: Sentence <- sentences.asScala) {
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
              addMinIESemanticAnnotations(openFact, proposition)

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
    * Add to the open fact a set of MinIE's semantic annotations.
    * @param openFact
    * @param proposition
    */
  private def addMinIESemanticAnnotations(openFact: MinIEOpenFact, proposition: AnnotatedProposition) = {

    // a better structure of these can be done...

    val strSemanticAnnotations = ListBuffer.empty[String]

    if(proposition.getAttribution.isValid) {
      openFact.setAttribution(proposition.getAttribution.toString)
      strSemanticAnnotations.append("Attribution: %s".format(openFact.getAttribution))
    }
    if(proposition.getModality != null) {
      openFact.setModality(proposition.getModality.toString)
      strSemanticAnnotations.append("Modality: %s".format(openFact.getModality))
    }
    if(proposition.getPolarity != null) {
      openFact.setPolarity(proposition.getPolarity.toString)
      strSemanticAnnotations.append("Polarity: %s".format(openFact.getPolarity))
    }
    if(!proposition.getAllQuantities.isEmpty) {
      val strQuantities = proposition.getAllQuantities.asScala.map(x => x.toString) mkString ","
      openFact.setQuantity(strQuantities)
      strSemanticAnnotations.append("Quantities: %s".format(strQuantities))
    }

    openFact.setText("(%s, %s, %s) with [ %s ]"
      .format(openFact.getSubject.getText, openFact.getRelation.getText, openFact.getObject.getText,
        strSemanticAnnotations mkString ", "
      )
    )

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


}


object MinIEAnalysisEngine {

  var PARAM_MODE: String = "safe"

}
