package de.mpg.mpi.uima.pipelines.io

import de.mpg.mpi.uima.io.readers.ReaderFactory
import de.mpg.mpi.uima.io.writers.WriterFactory
import de.tudarmstadt.ukp.dkpro.core.api.metadata.`type`.DocumentMetaData
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.logging.ProgressLogger
import org.apache.uima.fit.factory.{AnalysisEngineFactory, JCasFactory}
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Runs a pipeline (ioArgs.pipeline) over a set of documents read from an input directory (ioArgs.inputDir) and
  * whose processed data is stored into a specified output directory (ioArgs.outputDir).
  *
  * @param ioArgs
  */
class IOPipeline(val ioArgs: IOPipelineArgs) {

  private val logger = LoggerFactory.getLogger(classOf[IOPipeline])


  def parallelProcessing : Unit = {

      val reader = ReaderFactory.apply(ioArgs.inputFormat, ioArgs.inputDir)
      val engines = ioArgs.pipeline.getEngines
      val writer = WriterFactory.apply(ioArgs.outputFormat, ioArgs.outputDir)

      val ae = AnalysisEngineFactory.createEngine(
        AnalysisEngineFactory.createEngineDescription(engines :+ writer:_*)
      )

      var isFirst = true
      val pl = new ProgressLogger(logger)
      pl.start()

      while(reader.hasNext) {

        // reads at most batch documents
        val documents = new ObjectArrayList[JCas]
        while(reader.hasNext && documents.size() < ioArgs.batch) {
          val jCas = JCasFactory.createJCas()
          reader.getNext(jCas.getCas)
          documents.add(jCas)

          pl.lightUpdate()
        }


        if(isFirst) {
          isFirst = false

          // because pipeline need to load resources
          ae.process(documents.get(0))

          return

          // parallel computation
          documents.subList(1, documents.size()).asScala.par.foreach(jCas => ae.process(jCas) )

        } else {

          //resources already loaded, only parallel computation
          documents.asScala.par.foreach(jCas => {
            try {
              ae.process(jCas)
            } catch {
              case e: Exception =>

                logger.error("Error with document %s".format(
                  JCasUtil.selectSingle(jCas, classOf[DocumentMetaData]).getDocumentId
                ))
            }
          } )
        }

        pl.update()
      }
      pl.done

  }

}
