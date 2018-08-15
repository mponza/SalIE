package de.mpg.mpi.uima.pipelines

import de.mpg.mpi.SalIEArgs
import de.mpg.mpi.uima.pipelines.analysis.StanfordNLPAnalysisPipeline
import de.mpg.mpi.uima.pipelines.partial.PartialSalientPositionPipeline

object PipelineFactory {

  def apply(pipeline: String, args: SalIEArgs) : Pipeline = pipeline match {
    case "stanford" => new StanfordNLPAnalysisPipeline()
    case "partial-position" => new PartialSalientPositionPipeline(args)
    case _ => throw new IllegalArgumentException("No pipeline with name %s".format(pipeline))
  }

}
