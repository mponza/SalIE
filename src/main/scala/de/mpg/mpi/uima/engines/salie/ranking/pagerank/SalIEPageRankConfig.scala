package de.mpg.mpi.uima.engines.salie.ranking.pagerank


case class SalIEPageRankConfig(graphStructure: String,
                               weighting: String,
                               weightingModel: String,
                               rankingPrior: String,
                               alpha: Float,
                               iterations: Float)

