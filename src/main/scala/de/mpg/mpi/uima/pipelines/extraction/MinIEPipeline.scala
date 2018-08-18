package de.mpg.mpi.uima.pipelines.extraction

import de.mpg.mpi.configs.ApplicationConfig
import de.mpg.mpi.uima.engines.minie.MinIEAnalysisEngine
import de.mpg.mpi.uima.pipelines.Pipeline
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp._
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory

class MinIEPipeline(mode : String) extends Pipeline {


  override def getEngines() : List[AnalysisEngineDescription] = {
    val tokenizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordSegmenter])
    val posTagger = AnalysisEngineFactory.createEngineDescription(classOf[StanfordPosTagger])
    val lemmatizer = AnalysisEngineFactory.createEngineDescription(classOf[StanfordLemmatizer])
    val depParser = AnalysisEngineFactory.createEngineDescription(classOf[StanfordParser], "mode", "BASIC")
    val ner = AnalysisEngineFactory.createEngineDescription(classOf[StanfordNamedEntityRecognizer])
    val coref = AnalysisEngineFactory.createEngineDescription(classOf[StanfordCoreferenceResolver])

    val minie = AnalysisEngineFactory.createEngineDescription(classOf[MinIEAnalysisEngine], "mode", mode)

    List(tokenizer, posTagger, lemmatizer, depParser, ner, coref, minie)
  }


  def this(conf: ApplicationConfig) = this(conf.miniemode())

}
