package de.mpg.mpi.uima.engines.salie.pagerank.edges.weighting

object WeightingFactory {

  def apply(weighting: String, filename: String) : WeightingFunction = weighting match {
    case "embedding" => new EmbeddingWeightingFunction(filename)
    case _ => throw new IllegalArgumentException("Cannot create weighting with %s".format(weighting))
  }
}
