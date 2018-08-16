package de.mpg.mpi.uima.pipelines.partial

import de.mpg.mpi.SalIEArgs
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.SalIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.support.{MinIEFacts2SalIEFactsAnalysisEngine, SalIEOpenFactHeadCorefAnalysisEngine, SalIEOpenFactPruningAnalysisEngine}
import de.mpg.mpi.uima.pipelines.Pipeline
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


class PartialSalIEPipeline(config: SalIEArgs) extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val minie = AnalysisEngineFactory.createEngineDescription(classOf[MinIEAnalysisEngine], "mode", config.miniemode())

    val minieFacts2salieFacts =
      AnalysisEngineFactory.createEngineDescription(classOf[MinIEFacts2SalIEFactsAnalysisEngine])
    val salieHeadCoref = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactHeadCorefAnalysisEngine])
    val pruning = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactPruningAnalysisEngine])

    val salie = AnalysisEngineFactory.createEngineDescription(
     classOf[SalIEAnalysisEngine],
      "graphStructure", config.graphstructure(),
      "weighting", config.weighting(),
      "weightingModel", config.weightingmodel(),
      "rankingPrior", config.rankingprior(),
      "alpha", config.alpha().asInstanceOf[java.lang.Float],
      "iterations", config.iterations().asInstanceOf[java.lang.Integer]
    )

    List(minie, minieFacts2salieFacts, salieHeadCoref, pruning, salie)
  }

}