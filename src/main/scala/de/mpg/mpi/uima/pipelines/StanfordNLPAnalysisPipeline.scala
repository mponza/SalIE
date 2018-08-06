package de.mpg.mpi.uima.pipelines

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory

class StanfordNLPAnalysisPipeline extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolver]) // fix when single sentence broken then it brokes all document

    List(tokenizer, posTagger, lemmatizer, depParser, coref)
  }

}
