package de.mpg.mpi.configs.salie

case class SalIEPageRankConfig(graphStructure: String,
                               weighting: String,
                               weightingModel: String,
                               rankingPrior: String,
                               alpha: Float,
                               iterations: Int)

object SalIEPageRankConfig {

  def getDefault() = {
    new SalIEPageRankConfig("clique", "embedding", "data/embeddings/agg-wikipedia.bin", "extractionOrder", 0.1f, 2)
  }

}