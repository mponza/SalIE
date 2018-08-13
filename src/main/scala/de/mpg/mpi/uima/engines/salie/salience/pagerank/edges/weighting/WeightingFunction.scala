package de.mpg.mpi.uima.engines.salie.salience.pagerank.edges.weighting

import java.lang.Double

import com.google.common.base.Function
import de.mpg.mpi.uima.engines.salie.salience.pagerank.graph.SalIEGraph

trait WeightingFunction extends Function[Long, Double] {

  def weightEdges(salIEGraph: SalIEGraph): Unit
}