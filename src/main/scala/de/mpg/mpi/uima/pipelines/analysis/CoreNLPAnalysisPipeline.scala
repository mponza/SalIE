package de.mpg.mpi.uima.pipelines.analysis

import de.mpg.mpi.uima.pipelines.Pipeline
import de.tudarmstadt.ukp.dkpro.core.corenlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


// Not used, to be tested

class CoreNLPAnalysisPipeline extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpDependencyParser], "mode", "BASIC")
    val ner = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpNamedEntityRecognizer])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpCoreferenceResolver])

    List(tokenizer, posTagger, lemmatizer, ner, depParser, coref)
  }

}
