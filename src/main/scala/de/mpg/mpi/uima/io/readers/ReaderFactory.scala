package de.mpg.mpi.uima.io.readers

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasReader
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader
import org.apache.uima.collection.CollectionReader
import org.apache.uima.fit.factory.CollectionReaderFactory


object ReaderFactory {


  def apply(format: String, dir: String): CollectionReader = format match  {
    case "txt" => getTextReader(dir)
    case "bin" => getBinCasReader(dir)
    case _ => throw new IllegalArgumentException("Cannnot instantiate %s reader".format(format))
  }


  def getTextReader(dir: String) : CollectionReader = {
    CollectionReaderFactory.createReader(
      classOf[TextReader],
      ResourceCollectionReaderBase.PARAM_LANGUAGE, "en",
      ResourceCollectionReaderBase.PARAM_SOURCE_LOCATION, dir,
      ResourceCollectionReaderBase.PARAM_PATTERNS, "*.txt" //"1815742.txt",
    )
  }


  /**
    * Get reader compatible with BinaryCasWriter.
    *
    * @param dir
    * @return
    */
  def getBinCasReader(dir: String) : CollectionReader = {
    CollectionReaderFactory.createReader(
      classOf[BinaryCasReader],
      ResourceCollectionReaderBase.PARAM_SOURCE_LOCATION, dir,
      ResourceCollectionReaderBase.PARAM_PATTERNS, "*bcas" //"1815742.txt",
    )
  }

}
