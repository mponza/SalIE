package de.mpg.mpi.uima.pipelines.extraction

import de.mpg.mpi.SalIEArgs
import de.mpg.mpi.uima.engines.PrinterAnalysisEngine
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.salience.SalIESalienceAnalysisEngine
import de.mpg.mpi.uima.engines.salie.support.{MinIEFacts2SalIEFactsAnalysisEngine, SalIEOpenFactHeadCorefAnalysisEngine}
import de.mpg.mpi.uima.pipelines.Pipeline
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


class SalIEPipeline(config: SalIEArgs) extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser], "mode", "BASIC")
    val ner = AnalysisEngineFactory.createEngineDescription(classOf[StanfordNamedEntityRecognizer])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolver])

    val minie = AnalysisEngineFactory.createEngineDescription(classOf[MinIEAnalysisEngine], "mode", config.miniemode())

    val minieFacts2salieFacts =
      AnalysisEngineFactory.createEngineDescription(classOf[MinIEFacts2SalIEFactsAnalysisEngine])

    val salieHeadCoref = AnalysisEngineFactory.createEngineDescription(classOf[SalIEOpenFactHeadCorefAnalysisEngine])

    val salieSalience = AnalysisEngineFactory.createEngineDescription(
      classOf[SalIESalienceAnalysisEngine],
      "graphStructure", config.graphstructure(),
      "weighting", config.weighting(),
      "rankingPrior", config.rankingprior(),
      "alpha", config.alpha().asInstanceOf[java.lang.Float],
      "iterations", config.iterations().asInstanceOf[java.lang.Integer]
    )

    val printer = AnalysisEngineFactory.createEngineDescription(classOf[PrinterAnalysisEngine])


    List(tokenizer, posTagger, lemmatizer, depParser, ner, coref,
         minie, minieFacts2salieFacts, salieHeadCoref, printer)
  }


}
