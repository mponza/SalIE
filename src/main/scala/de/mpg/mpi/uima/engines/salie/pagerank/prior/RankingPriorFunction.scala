package de.mpg.mpi.uima.engines.salie.pagerank.prior

import java.lang.Double

import com.google.common.base.Function
import de.mpg.mpi.uima.engines.salie.pagerank.graph.SalIEGraph

trait RankingPriorFunction extends Function[Int, Double] {

  def weightNodes(salieOpenFactGraph: SalIEGraph)
}
