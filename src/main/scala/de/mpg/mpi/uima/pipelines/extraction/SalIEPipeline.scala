package de.mpg.mpi.uima.pipelines.extraction

import de.mpg.mpi.configs.ApplicationConfig
import de.mpg.mpi.configs.salie.SalIEConfig
import de.mpg.mpi.uima.engines.PrinterAnalysisEngine
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.SalIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.support.{MinIEFacts2SalIEFactsAnalysisEngine, SalIEOpenFactHeadCorefAnalysisEngine, SalIEOpenFactPruningAnalysisEngine}
import de.mpg.mpi.uima.pipelines.Pipeline
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


class SalIEPipeline(salieConfig: SalIEConfig) extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser], "mode", "BASIC")
    val ner = AnalysisEngineFactory.createEngineDescription(classOf[StanfordNamedEntityRecognizer])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolver])

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


    List(tokenizer, posTagger, lemmatizer, depParser, ner, coref, minie, minieFacts2salieFacts, salieHeadCoref,
         pruning, salie)
  }


  def this(config: ApplicationConfig) = this(config.getSalIEConfig())

}
