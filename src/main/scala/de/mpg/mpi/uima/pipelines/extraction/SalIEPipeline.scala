package de.mpg.mpi.uima.pipelines.extraction

import de.mpg.mpi.SalIEArgs
import de.mpg.mpi.uima.engines.PrinterAnalysisEngine
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.engines.salie.MinIEFacts2SalIEFactsAnalysisEngine
import de.mpg.mpi.uima.pipelines.Pipeline
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory

class SalIEPipeline(conf: SalIEArgs) extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser], "mode", "BASIC")
    val ner = AnalysisEngineFactory.createEngineDescription(classOf[StanfordNamedEntityRecognizer])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolver])

    val minie = AnalysisEngineFactory.createEngineDescription(classOf[MinIEAnalysisEngine], "mode", conf.miniemode())

    val minieFacts2salieFacts =
      AnalysisEngineFactory.createEngineDescription(classOf[MinIEFacts2SalIEFactsAnalysisEngine])

    val printer = AnalysisEngineFactory.createEngineDescription(classOf[PrinterAnalysisEngine])


    List(tokenizer, posTagger, lemmatizer, depParser, ner, coref,
         minie, minieFacts2salieFacts, printer)
  }


}
