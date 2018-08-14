package de.mpg.mpi.uima.pipelines

import de.mpg.mpi.uima.pipelines.analysis.StanfordNLPAnalysisPipeline

object PipelineFactory {

  def apply(pipeline: String) : Pipeline = pipeline match {
    case "stanford" => new StanfordNLPAnalysisPipeline()
    case _ => throw new IllegalArgumentException("No pipeline with name %s".format(pipeline))
  }

}
