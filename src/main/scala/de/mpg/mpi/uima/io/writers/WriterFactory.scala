package de.mpg.mpi.uima.io.writers

import java.io.File

import de.mpg.mpi.uima.io.writers.json.JsonOpenFactWriter
import de.tudarmstadt.ukp.dkpro.core.api.io.JCasFileWriter_ImplBase
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AnalysisEngineFactory


object WriterFactory {


  def apply(format: String, dir: String): AnalysisEngineDescription = format match {
    case "bin" => getBinCasWriter(dir)
    case "json" => getJsonWriter(dir)
    case _ => throw new IllegalArgumentException("Cannnot instantiate %s writer".format(format))
  }


  def getJsonWriter(dir: String) : AnalysisEngineDescription = {
    createDirectory(dir)
    AnalysisEngineFactory.createEngineDescription(
      classOf[JsonOpenFactWriter],
      JCasFileWriter_ImplBase.PARAM_TARGET_LOCATION, dir
    )
  }


  def getBinCasWriter(dir: String) : AnalysisEngineDescription = {
    createDirectory(dir)
    AnalysisEngineFactory.createEngineDescription(
      classOf[BinaryCasWriter],
      JCasFileWriter_ImplBase.PARAM_TARGET_LOCATION, dir,
      JCasFileWriter_ImplBase.PARAM_USE_DOCUMENT_ID, true.asInstanceOf[java.lang.Boolean],
      JCasFileWriter_ImplBase.PARAM_STRIP_EXTENSION, true.asInstanceOf[java.lang.Boolean],
      JCasFileWriter_ImplBase.PARAM_OVERWRITE, true.asInstanceOf[java.lang.Boolean]
    )
  }


  /**
    * Creates full path to directory dir, if it does not exist.
    *
    * @param dir
    */
  private def createDirectory(dir: String) : Unit = {
    val fileDir = new File(dir)
    if (!fileDir.exists()) {
      fileDir.mkdirs()
    }
  }

}
