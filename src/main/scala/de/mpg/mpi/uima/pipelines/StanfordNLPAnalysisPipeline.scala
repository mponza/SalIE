package de.mpg.mpi.uima.pipelines

import de.mpg.mpi.uima.engines.StanfordCoreferenceResolverEngine
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.{StanfordLemmatizer, StanfordParser, StanfordPosTagger, StanfordSegmenter}
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory

class StanfordNLPAnalysisPipeline extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolverEngine])

    List(tokenizer, posTagger, lemmatizer, depParser, coref)
  }

}
