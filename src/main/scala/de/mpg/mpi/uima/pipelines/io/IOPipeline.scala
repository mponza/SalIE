package de.mpg.mpi.uima.pipelines.io

import de.mpg.mpi.configs.IOPipelineConfig
import de.mpg.mpi.uima.io.readers.ReaderFactory
import de.mpg.mpi.uima.io.writers.WriterFactory
import de.tudarmstadt.ukp.dkpro.core.api.metadata.`type`.DocumentMetaData
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.logging.ProgressLogger
import org.apache.uima.analysis_engine.{AnalysisEngine, AnalysisEngineDescription}
import org.apache.uima.fit.factory.{AnalysisEngineFactory, JCasFactory}
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Runs a pipeline (ioArgs.pipeline) over a set of documents read from an input directory (ioArgs.inputDir) and
  * whose processed data is stored into a specified output directory (ioArgs.outputDir).
  *
  * @param ioConfig
  */
class IOPipeline(val ioConfig: IOPipelineConfig) {

  private val logger = LoggerFactory.getLogger(classOf[IOPipeline])


  def parallelProcessing : Unit = {

      val reader = ReaderFactory.apply(ioConfig.inputFormat, ioConfig.inputDir)
      val engines = ioConfig.pipeline.getEngines
      val writer = WriterFactory.apply(ioConfig.outputFormat, ioConfig.outputDir)

      val ae = AnalysisEngineFactory.createEngine(
        AnalysisEngineFactory.createEngineDescription(engines :+ writer:_*)
      )

      var isFirst = true
      val pl = new ProgressLogger(logger)
      pl.start()

      while(reader.hasNext) {

        // reads at most batch documents
        val documents = new ObjectArrayList[JCas]
        while(reader.hasNext && documents.size() < ioConfig.batch) {
          val jCas = JCasFactory.createJCas()
          reader.getNext(jCas.getCas)
          documents.add(jCas)

          pl.lightUpdate()
        }


        if(isFirst) {
          isFirst = false

          // because pipeline need to load resources
          ae.process(documents.get(0))

          // parallel computation
          processParallelDocuments(ae, documents, 1, documents.size())

        } else {
          processParallelDocuments(ae, documents)
        }

        pl.update()
      }
      pl.done

  }

  def processParallelDocuments(ae: AnalysisEngine, documents: ObjectArrayList[JCas], begin: Int, end: Int): Unit = {
    // resources already loaded, only parallel computation
    documents.subList(begin, end).asScala.par.foreach(jCas => {
      try {
        ae.process(jCas)
      } catch {
        case e: Exception =>
          // needs better catching coreference exceptions
          logger.error("Error with document %s".format(
            JCasUtil.selectSingle(jCas, classOf[DocumentMetaData]).getDocumentId
          ))
      }
    })
  }

  def processParallelDocuments(ae: AnalysisEngine, documents: ObjectArrayList[JCas]): Unit = {
    processParallelDocuments(ae, documents, 0, documents.size())
  }
}
