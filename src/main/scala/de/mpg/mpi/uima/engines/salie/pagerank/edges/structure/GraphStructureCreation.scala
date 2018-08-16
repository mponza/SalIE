package de.mpg.mpi.uima.engines.salie.pagerank.edges.structure

import de.mpg.mpi.uima.engines.salie.pagerank.graph.SalIEGraph

trait GraphStructureCreation {


  /**
    * Creates edges in salIEGraph.graph.
    *
    * @param salIEGraph
    */
  def createEdges(salIEGraph: SalIEGraph)

}
