package de.mpg.mpi.runners

import de.mpg.mpi.SalIEArgs
import de.mpg.mpi.uima.pipelines.PipelineFactory
import de.mpg.mpi.uima.pipelines.io.{IOPipeline, IOPipelineArgs}

object RunIOPipeline {

  def main(args: Array[String]): Unit = {

    val salieArgs = new SalIEArgs(args)

    val pipeline = PipelineFactory(salieArgs.pipeline())
    val ioArgs = IOPipelineArgs(
      pipeline,
      salieArgs.inputdir(), salieArgs.inputformat(),
      salieArgs.outputdir(), salieArgs.outputformat(),
      salieArgs.batch()
    )

    val ioPipeline = new IOPipeline(ioArgs)
    ioPipeline.parallelProcessing
  }

}
