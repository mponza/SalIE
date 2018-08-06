package de.mpg.mpi.uima.utils

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import de.tudarmstadt.ukp.dkpro.core.corenlp.internal.DKPro2CoreNlp
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.semgraph.{SemanticGraph, SemanticGraphCoreAnnotations}
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import org.apache.uima.jcas.JCas
import edu.stanford.nlp.pipeline.Annotation
import org.apache.uima.fit.util.JCasUtil


/**
  * Class that implements the mapping between DKPro sentences to their CoreNLP semantic graph.
  * @param jcas
  */
class SemanticSentences(jcas: JCas) {

  private val sentence2semGraph = mapSentence2SemanticGraph(jcas)  // DKPro sentence to CoreNLP semantic graph


  /**
    * Map sentences to their semantic graph.
    * @param jCas
    * @return
    */
  private def mapSentence2SemanticGraph(jCas: JCas) : Object2ObjectOpenHashMap[Sentence, SemanticGraph] = {
    val converter = new DKPro2CoreNlp()
    val annotations = converter.convert(jCas, new Annotation())
    val coreSentences = annotations.get(classOf[CoreAnnotations.SentencesAnnotation])

    val sent2semGraph = new Object2ObjectOpenHashMap[Sentence, SemanticGraph]()
    coreSentences.stream().forEach(
      coreSentence => {
        val semanticGraph = coreSentence.get(classOf[SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation])

        val begin = coreSentence.get(classOf[CoreAnnotations.CharacterOffsetBeginAnnotation])
        val end = coreSentence.get(classOf[CoreAnnotations.CharacterOffsetEndAnnotation])
        val dkproSentence = JCasUtil.selectCovered(jCas, classOf[Sentence], begin, end).get(0)

        sent2semGraph.put(dkproSentence, semanticGraph)
    })

    sent2semGraph
  }


  /**
    * Given a DkPro sentence it returns its semantic graph.
    * @param sentence
    * @return
    */
  def getSemanticGraph(sentence: Sentence) : SemanticGraph = { sentence2semGraph.getOrDefault(sentence, null) }

}
