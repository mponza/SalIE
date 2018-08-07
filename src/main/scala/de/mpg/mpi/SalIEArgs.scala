package de.mpg.mpi

import org.rogach.scallop.ScallopConf

class SalIEArgs(arguments: Seq[String]) extends ScallopConf(arguments) {

  // MinIE arguments
  val minieMode = opt[String](required = true, default = Some("safe"))

  // SalIE arguments
//  val embeddingFilename = opt[String](required = true)
//  val iterations = opt[Int](required = true)
//  val alpha = opt[Int](required = true)
//  val coreference = opt[Boolean](required = false, default = Some(true))
//  val clustering = opt[Boolean](required = false, default = Some(true))

  val dataDir = opt[String](required = false)

}
