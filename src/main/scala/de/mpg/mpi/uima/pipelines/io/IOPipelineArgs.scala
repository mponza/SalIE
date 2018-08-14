package de.mpg.mpi.uima.pipelines.io

import de.mpg.mpi.uima.pipelines.Pipeline

case class IOPipelineArgs(pipeline: Pipeline,
                          inputDir: String, inputFormat: String,
                          outputDir: String, outputFormat: String,
                          batch: Int
                         )
