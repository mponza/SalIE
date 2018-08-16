package de.mpg.mpi.uima.engines.salie.pagerank.prior

object RankingPriorFactory {

  def apply(rankingPrior : String) : RankingPriorFunction = rankingPrior match {
    case "extractionOrder" => new ExtractionOrderPriorFunction()
    case _ => throw new IllegalArgumentException("No ranking prior with name %s".format(rankingPrior))
  }

}
