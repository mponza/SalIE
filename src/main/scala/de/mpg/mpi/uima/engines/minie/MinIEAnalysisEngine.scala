package de.mpg.mpi.uima.engines.minie

import de.mpg.mpi.uima.`type`._
import de.mpg.mpi.uima.utils.SemanticSentences
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.{Sentence, Token}
import de.tudarmstadt.ukp.dkpro.core.corenlp.internal.TokenKey
import de.uni_mannheim.minie.MinIE
import de.uni_mannheim.minie.annotation.AnnotatedPhrase
import edu.stanford.nlp.ling.IndexedWord
import edu.stanford.nlp.semgraph.SemanticGraph
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import org.apache.uima.UimaContext
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.descriptor.ConfigurationParameter
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Adds MinIE open facts and its semantic annotations to the processed JCas.
  *
  * Warning: polarity, modality and attribution are added for sake of completeness but
  * they are actually not used within SalIE framework. However, from a SalIEOpenFact you
  * can easily access to them.
  */
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
  private def getMinIEMode : MinIE.Mode = {
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

        minIE.getPropositions.stream().forEach(
          proposition => {

            try {

              val minieOpenFact = new MinIEOpenFact(jCas)


              // subject

              val subject = addMinIEConstituent2JCas(jCas, proposition.getSubject, semanticGraph)
              minieOpenFact.setSubject(subject)
              minieOpenFact.setBegin(subject.getBegin)


              // relation

              val relation = addMinIEConstituent2JCas(jCas, proposition.getRelation, semanticGraph)
              minieOpenFact.setRelation(relation)


              // object

              val objectFact = addMinIEConstituent2JCas(jCas, proposition.getObject, semanticGraph)
              minieOpenFact.setObject(objectFact)
              minieOpenFact.setEnd(objectFact.getEnd)


              // polarity

              val miniePolarity = new MinIEPolarity(jCas)
              val (polTokens, polBegin, polEnd) = indexedWords2FSArrayBeginEnd(
                jCas, proposition.getPolarity.getNegativeWords)
              miniePolarity.setNegativeTokens(polTokens)
              miniePolarity.setBegin(polBegin)
              miniePolarity.setEnd(polEnd)
              miniePolarity.setPolarityType(proposition.getPolarity.getType.toString)

              miniePolarity.addToIndexes()
              minieOpenFact.setPolarity(miniePolarity)


              // modality

              val minieModality = new MinIEModality(jCas)

              val (certTokens, certBegin, certEnd) = indexedWords2FSArrayBeginEnd(
                jCas, new ObjectArrayList[IndexedWord](proposition.getModality.getCertaintyWords))
              minieModality.setCertaintyTokens(certTokens)

              val (possTokens, possBegin, possEnd) = indexedWords2FSArrayBeginEnd(
                jCas, new ObjectArrayList[IndexedWord](proposition.getModality.getPossibilityWords)
              )
              minieModality.setPossibilityTokens(possTokens)

              minieModality.setBegin(certBegin min possBegin)
              minieModality.setEnd(certEnd max possEnd)

              minieModality.addToIndexes()
              minieOpenFact.setModality(minieModality)


              // attribution (to be tested)
              val minieAttribution = new MinIEAttribution(jCas)

              try {
                if (proposition.getAttribution.isValid) {

                  val attribConstituent = addMinIEConstituent2JCas(
                    jCas, proposition.getAttribution.getAttributionPhrase,
                    semanticGraph)

                  minieAttribution.setAttributionConstituent(attribConstituent)
                  minieAttribution.setModalityType(proposition.getModality.toString)
                  minieAttribution.setPolarityType(proposition.getPolarity.toString)

                } else {
                  // no attribution for this proposition
                  minieAttribution.setAttributionConstituent(null)
                }

              } catch {
                case e: Exception =>
                  logger.warn("Error with attribution in proposition %s in sentence %s".format(
                    proposition, sentence.getCoveredText
                  ))
                  minieAttribution.setAttributionConstituent(null)
              }
              minieAttribution.addToIndexes()
              minieOpenFact.setAttribution(minieAttribution)


              // finalizing fact creation and indexing

              minieOpenFact.setText(proposition.toString)
              minieOpenFact.addToIndexes()

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
    * Creates and returns a MinIEConstituent by properly setting its token, quantities, head and text field.
    *
    * @param jCas
    * @param phrase
    * @param semanticGraph
    * @tparam T
    */
  private def addMinIEConstituent2JCas[T](jCas: JCas, phrase: AnnotatedPhrase, semanticGraph: SemanticGraph) = {

    val constituent = new MinIEConstituent(jCas)


    // setting constituent tokens, begin and end positions

    val (constTokens, constBegin, constEnd) = indexedWords2FSArrayBeginEnd(jCas, phrase.getWordList)

    constituent.setTokens(constTokens)
    constituent.setBegin(constBegin)
    constituent.setEnd(constEnd)


    // setting constituent quantities

    val quantities = new FSArray(jCas, phrase.getQuantities.size())

    phrase.getQuantities.asScala.zipWithIndex.foreach(
      indexedPhraseQuantity => {
        val phraseQuantity = indexedPhraseQuantity._1
        val index = indexedPhraseQuantity._2

        val minieQuantity = new MinIEQuantity(jCas)

        val (quantTokens, quantBegin, quantEnd) = indexedWords2FSArrayBeginEnd(jCas, phraseQuantity.getQuantityWords)
        minieQuantity.setQuantityTokens(quantTokens)
        minieQuantity.setBegin(quantBegin)
        minieQuantity.setEnd(quantEnd)
        minieQuantity.setText(phraseQuantity.toString)

        quantities.set(index, minieQuantity)
      }
    )

    constituent.setQuantities(quantities)


    // setting text with respect to MinIE toString

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


  /**
    * Given a list of IndexedWords returns the corresponding FSArray of tokens (added to the JCas), plus
    * its begin and end indicies.
    *
    * @param jCas
    * @param indexedWords
    * @return
    */
  private def indexedWords2FSArrayBeginEnd(jCas: JCas, indexedWords: ObjectArrayList[IndexedWord]) : (FSArray, Int, Int) = {
    var begin = Integer.MAX_VALUE
    var end = -1

    val tokens = new FSArray(jCas, indexedWords.size())
    indexedWords.asScala.zipWithIndex.foreach(
      indexedWord => {

        val word = indexedWord._1
        val index = indexedWord._2


        // adding constituent token

        var token: Token = null
        val t = word.get(classOf[TokenKey])
        val b = word.keySet() //.containsKey(classOf[TokenKey])
        if(word.get(classOf[TokenKey]) == null) {
          token = new Token(jCas)
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

    (tokens, begin, end)
  }


}


object MinIEAnalysisEngine {

  var PARAM_MODE: String = "safe"

}
