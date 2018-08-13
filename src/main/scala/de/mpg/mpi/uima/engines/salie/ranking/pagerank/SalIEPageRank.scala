package de.mpg.mpi.uima.engines.salie.ranking.pagerank

import de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.structure.GraphStructureFactory
import de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.weighting.{WeightingFactory, WeightingFunction}
import de.mpg.mpi.uima.engines.salie.ranking.pagerank.graph.SalIEOpenFactGraph
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory


class SalIEPageRank(val jCas: JCas, val config: SalIEPageRankConfig) {

  private val logger = LoggerFactory.getLogger(classOf[SalIEPageRank])


  val salieGraph = new SalIEOpenFactGraph(jCas)   // creates graph of open facts, no edge drawing yet
  val weightingFunction = createWeightedEdges(salieGraph, config) // drawns edges and precompute their weights
  val rankingPrior =


  /**
    * Connects nodes and weights their edges.
    *
    * @param salieGraph
    * @param config
    * @return
    */
  private def createWeightedEdges(salieGraph: SalIEOpenFactGraph, config: SalIEPageRankConfig) : WeightingFunction = {
    // Drawn edges
    val edgeCreation = GraphStructureFactory(config.graphStructure)
    edgeCreation.createEdges(salieGraph)

    // Weight edges
    val weighting = WeightingFactory(config.weighting, config.weightingModel)
    weighting.weightEdges(salieGraph)

    weighting
  }

}
