package de.mpg.mpi.uima.pipelines.analysis

import de.mpg.mpi.uima.pipelines.Pipeline
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory

class StanfordNLPAnalysisPipeline extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser], "mode", "BASIC")
    val ner = AnalysisEngineFactory.createEngineDescription(classOf[StanfordNamedEntityRecognizer])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolver])

    List(tokenizer, posTagger, lemmatizer, depParser, ner, coref)
  }

}
