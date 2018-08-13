package de.mpg.mpi.uima.engines.salie.salience

import de.mpg.mpi.uima.engines.salie.salience.pagerank.SalIEPageRank
import de.mpg.mpi.uima.engines.salie.salience.pagerank.config.SalIEPageRankConfig
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.descriptor.ConfigurationParameter
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory


/**
  * Computes salience scores for each SalIEOpenFact via PageRank.
  */
class SalIESalienceAnalysisEngine extends JCasAnnotator_ImplBase {

  private val logger = LoggerFactory.getLogger(classOf[SalIESalienceAnalysisEngine])


  @ConfigurationParameter
  var graphStructure: String = "clique"

  @ConfigurationParameter
  var weighting: String = "embedding"

  @ConfigurationParameter
  var weightingModel: String = "data/embeddings/safe-wikipedia.bin"

  @ConfigurationParameter
  var rankingPrior: String = "extractionOrder"

  @ConfigurationParameter
  var alpha: Float = 0.1f

  @ConfigurationParameter
  var iterations: Int = 3


  private def getSalIEPageRankconfig = {
    new SalIEPageRankConfig(graphStructure, weighting, weightingModel, rankingPrior, alpha, iterations)
  }


  override def process(jCas: JCas) = {
    val saliePageRank = new SalIEPageRank(jCas, getSalIEPageRankconfig)
    saliePageRank.computePageRank()
  }

}
