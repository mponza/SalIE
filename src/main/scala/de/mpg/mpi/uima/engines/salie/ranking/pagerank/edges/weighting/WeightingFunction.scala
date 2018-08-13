package de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.weighting

import com.google.common.base.Function
import de.mpg.mpi.uima.engines.salie.ranking.pagerank.graph.SalIEOpenFactGraph

trait WeightingFunction extends Function[Long, Double] {

  def weightEdges(salIEGraph: SalIEOpenFactGraph): Unit
}