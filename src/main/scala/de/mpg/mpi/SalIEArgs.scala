package de.mpg.mpi

import org.rogach.scallop.ScallopConf


class SalIEArgs(arguments: Seq[String]) extends ScallopConf(arguments) {

  //
  // Data arguments

  val inputdir = opt[String](required = false)
  val inputformat = opt[String](required = false)

  val outputdir = opt[String](required = false)
  val outputformat = opt[String](required = false)

  val batch = opt[Int](required = false)


  //
  // Pipeline

  val pipeline = opt[String](required = false) // name of the pipeline


  //
  // MinIE arguments

  val miniemode = opt[String](required = false, default = Some("safe"))


  //
  // SalIE arguments

  val graphstructure = opt[String](required = false, default = Some("clique"))
  val weighting = opt[String](required = false, default = Some("embedding"))
  val weightingmodel = opt[String](required = false, default = Some("clique"))
  val rankingprior = opt[String](required = false, default = Some("extractionOrder"))
  val alpha = opt[Float](required = false, default = Some(0.1f))
  val iterations = opt[Int](required = false, default = Some(2))


  verify()
}
