package de.mpg.mpi.uima.pipelines

import de.mpg.mpi.configs.ApplicationConfig
import de.mpg.mpi.uima.pipelines.analysis.StanfordNLPAnalysisPipeline
import de.mpg.mpi.uima.pipelines.extraction.SalIEPipeline
import de.mpg.mpi.uima.pipelines.partial.{PartialSalIEPipeline, PartialSalientPositionPipeline}

object PipelineFactory {

  def apply(pipeline: String, config: ApplicationConfig) : Pipeline = pipeline match {
    case "stanford" => new StanfordNLPAnalysisPipeline()

    case "salie" => new SalIEPipeline(config)

    // partial pipeline are useful if you have preprocessed cas files
    case "partial-position" => new PartialSalientPositionPipeline(config)
    case "partial-salie" => new PartialSalIEPipeline(config)

    case _ => throw new IllegalArgumentException("No pipeline with name %s".format(pipeline))
  }

}
