package de.mpg.mpi.uima.readers

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader
import org.apache.uima.collection.CollectionReader
import org.apache.uima.fit.factory.CollectionReaderFactory


object ReaderFactory {

  def getTextReader(dir: String) : CollectionReader = {

    CollectionReaderFactory.createReader(
      classOf[TextReader],
      ResourceCollectionReaderBase.PARAM_LANGUAGE, "en",
      ResourceCollectionReaderBase.PARAM_SOURCE_LOCATION, dir,
      ResourceCollectionReaderBase.PARAM_PATTERNS, "*.txt", //"1815742.txt",
    )

  }

}
