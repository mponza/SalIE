package de.mpg.mpi.uima.engines.salie

import de.mpg.mpi.configs.salie.SalIEPageRankConfig
import de.mpg.mpi.uima.engines.salie.clustering.SalIEClustering
import de.mpg.mpi.uima.engines.salie.pagerank.SalIEPageRank
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.descriptor.ConfigurationParameter
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory


/**
  * Computes salience scores for each SalIEOpenFact via PageRank.
  */
class SalIEAnalysisEngine extends JCasAnnotator_ImplBase {

  private val logger = LoggerFactory.getLogger(classOf[SalIEAnalysisEngine])


  @ConfigurationParameter
  var graphStructure: String = "clique"

  @ConfigurationParameter
  var weighting: String = "embedding"

  @ConfigurationParameter
  var weightingModel: String = "path/to/model"

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
    // Prominance Computation
    val saliePageRank = new SalIEPageRank(jCas, getSalIEPageRankconfig)
    saliePageRank.computePageRank()

    // Open Facts Diversification
    val salieClustering = new SalIEClustering()
    salieClustering.diversify(jCas)
  }

}


object SalIEAnalysisEngine {

  val PARAM_GRAPH_STRUCTURE: String = "graphStructure"
  val PARAM_WEIGHTING: String = "weighting"
  val PARAM_WEIGHTING_MODEL: String = "weightingModel"
  val PARAM_RANKING_PRIOR: String = "rankingPrior"
  val PARAM_ALPHA: String = "alpha"
  val PARAM_ITERATIONS: String = "iterations"

}
