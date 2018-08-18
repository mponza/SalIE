package de.mpg.mpi.runners

import de.mpg.mpi.configs.{ApplicationConfig, IOPipelineConfig}
import de.mpg.mpi.uima.pipelines.PipelineFactory
import de.mpg.mpi.uima.pipelines.io.IOPipeline

object RunIOPipeline {

  def main(args: Array[String]): Unit = {

    val config = new ApplicationConfig(args)

    val pipeline = PipelineFactory(config.pipeline(), config)
    val ioConfig = IOPipelineConfig(
      pipeline,
      config.inputdir(), config.inputformat(),
      config.outputdir(), config.outputformat(),
      config.batch()
    )

    val ioPipeline = new IOPipeline(ioConfig)
    ioPipeline.parallelProcessing
  }

}
