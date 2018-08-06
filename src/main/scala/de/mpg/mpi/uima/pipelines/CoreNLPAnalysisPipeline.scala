package de.mpg.mpi.uima.pipelines

import de.tudarmstadt.ukp.dkpro.core.corenlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory

class CoreNLPAnalysisPipeline extends Pipeline {

  override def getEngines() : List[AnalysisEngineDescription] = {

    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpDependencyParser])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[CoreNlpCoreferenceResolver]) // fix when single sentence broken then it brokes all document

    List(tokenizer, posTagger, lemmatizer, depParser, coref)
  }

}
