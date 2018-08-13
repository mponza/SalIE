package de.mpg.mpi.uima.engines.salie.ranking.pagerank.prior

import com.google.common.base.Function
import de.mpg.mpi.uima.engines.salie.ranking.pagerank.graph.SalIEOpenFactGraph

trait RankingPriorFunction extends Function[Int, Double] {

  def weightNodes(salieOpenFactGraph: SalIEOpenFactGraph)
}
