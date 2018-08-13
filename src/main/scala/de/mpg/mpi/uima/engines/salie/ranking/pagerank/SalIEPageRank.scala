package de.mpg.mpi.uima.engines.salie.ranking.pagerank

import de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.structure.GraphStructureFactory
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory


class SalIEPageRank(val jCas: JCas, val config: SalIEPageRankConfig) {

  private val logger = LoggerFactory.getLogger(classOf[SalIEPageRank])


  val salieGraph = new OpenFactGraph(jCas)   // creates graph of open facts, no edge drawing yet
  createWeightedEdges(salieGraph, config)


  def createWeightedEdges(salieGraph: OpenFactGraph, config: SalIEPageRankConfig) = {
    // Drawn edges
    val edgeCreation = GraphStructureFactory(config.graphStructure)
    edgeCreation.createEdges(salieGraph)

    // Weight edges


  }

}
