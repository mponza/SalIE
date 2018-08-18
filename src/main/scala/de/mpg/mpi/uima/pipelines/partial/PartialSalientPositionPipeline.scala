package de.mpg.mpi.uima.pipelines.partial

import de.mpg.mpi.configs.ApplicationConfig
import de.mpg.mpi.uima.engines.SalientPositionAnalysisEngine
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.support.{MinIEFacts2SalIEFactsAnalysisEngine, SalIEOpenFactHeadCorefAnalysisEngine, SalIEOpenFactPruningAnalysisEngine}
import de.mpg.mpi.uima.pipelines.Pipeline
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


class PartialSalientPositionPipeline(config: ApplicationConfig) extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val minie = AnalysisEngineFactory.createEngineDescription(classOf[MinIEAnalysisEngine], "mode", config.miniemode())

    val minieFacts2salieFacts =
      AnalysisEngineFactory.createEngineDescription(classOf[MinIEFacts2SalIEFactsAnalysisEngine])
    val salieHeadCoref = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactHeadCorefAnalysisEngine])
    val pruning = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactPruningAnalysisEngine])

    val salPosition = AnalysisEngineFactory.createEngineDescription(classOf[SalientPositionAnalysisEngine])

    List(minie, minieFacts2salieFacts, salieHeadCoref, pruning, salPosition)
  }

}