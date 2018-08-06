package de.mpg.mpi.uima.pipelines

import org.apache.uima.analysis_engine.AnalysisEngineDescription

trait Pipeline {

  def getEngines() : List[AnalysisEngineDescription]

}
