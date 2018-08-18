package de.mpg.mpi.configs

import de.mpg.mpi.uima.pipelines.Pipeline

/**
  * Parameters needed to run an IOPipeline.
  *
  * @param pipeline
  * @param inputDir
  * @param inputFormat
  * @param outputDir
  * @param outputFormat
  * @param batch
  */
case class IOPipelineConfig(pipeline: Pipeline,
                            inputDir: String, inputFormat: String,
                            outputDir: String, outputFormat: String,
                            batch: Int
                         )
