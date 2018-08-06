package de.mpg.mpi.engines

import de.mpg.mpi.uima.utils.SemanticSentences
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import de.uni_mannheim.minie.MinIE
import org.apache.uima.UimaContext
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory


class MinIEAnalysisEngine(mode: MinIE.Mode) extends JCasAnnotator_ImplBase {

  val logger = LoggerFactory.getLogger(classOf[MinIEAnalysisEngine])


  override def initialize(context: UimaContext): Unit = {
    super.initialize(context)
  }


  override def process(jCas: JCas) = {
    val semanticSentences = new SemanticSentences(jCas)
    val sentences = JCasUtil.select(jCas, classOf[Sentence])



    for(sentence: Sentence <- sentences) {
      val semanticGraph = semanticSentences.getSemanticGraph(sentence)
      if(semanticGraph != null) {
        val minIE = new MinIE(sentence.getCoveredText, semanticGraph, mode)

        // add MinIE annotations
      }
    }
  }


}
