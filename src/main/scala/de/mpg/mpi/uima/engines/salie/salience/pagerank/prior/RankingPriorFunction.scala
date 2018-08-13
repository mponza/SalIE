package de.mpg.mpi.uima.engines.salie.salience.pagerank.prior

import java.lang.Double

import com.google.common.base.Function
import de.mpg.mpi.uima.engines.salie.salience.pagerank.graph.SalIEGraph

trait RankingPriorFunction extends Function[Int, Double] {

  def weightNodes(salieOpenFactGraph: SalIEGraph)
}
