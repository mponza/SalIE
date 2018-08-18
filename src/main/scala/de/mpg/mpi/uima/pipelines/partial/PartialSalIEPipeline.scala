package de.mpg.mpi.uima.pipelines.partial

import de.mpg.mpi.configs.ApplicationConfig
import de.mpg.mpi.configs.salie.SalIEConfig
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.SalIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.support.{MinIEFacts2SalIEFactsAnalysisEngine, SalIEOpenFactHeadCorefAnalysisEngine, SalIEOpenFactPruningAnalysisEngine}
import de.mpg.mpi.uima.pipelines.Pipeline
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


class PartialSalIEPipeline(salieConfig: SalIEConfig) extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val minie = AnalysisEngineFactory.createEngineDescription(classOf[MinIEAnalysisEngine], "mode", salieConfig.minieMode)

    val minieFacts2salieFacts =
      AnalysisEngineFactory.createEngineDescription(classOf[MinIEFacts2SalIEFactsAnalysisEngine])

    val salieHeadCoref = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactHeadCorefAnalysisEngine])

    val pruning = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactPruningAnalysisEngine])

    val salie = AnalysisEngineFactory.createEngineDescription(
      classOf[SalIEAnalysisEngine],
      "graphStructure", salieConfig.pageRankConfig.graphStructure,
      "weighting", salieConfig.pageRankConfig.weighting,
      "weightingModel", salieConfig.pageRankConfig.weightingModel,
      "rankingPrior", salieConfig.pageRankConfig.rankingPrior,
      "alpha", salieConfig.pageRankConfig.alpha.asInstanceOf[java.lang.Float],
      "iterations", salieConfig.pageRankConfig.iterations.asInstanceOf[java.lang.Integer]
    )


    List(minie, minieFacts2salieFacts, salieHeadCoref, pruning, salie)
  }


  def this(config: ApplicationConfig) = this(config.getSalIEConfig())

}