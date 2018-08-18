package de.mpg.mpi.configs.salie


/**
  * Parameters needed to run SalIE.
  *
  * @param pageRankConfig
  */
case class SalIEConfig(minieMode: String,
                       pageRankConfig: SalIEPageRankConfig) {}


object SalIEConfig {

  def getDefault() = {
    new SalIEConfig("agg", SalIEPageRankConfig.getDefault())
  }
}