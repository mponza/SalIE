package de.mpg.mpi.uima.io.writers.json

import java.io.{File, FileOutputStream}

import com.jsoniter.output.JsonStream
import de.mpg.mpi.uima.io.writers.json.support.JsonDocument
import de.tudarmstadt.ukp.dkpro.core.api.io.JCasFileWriter_ImplBase
import org.apache.uima.jcas.JCas


class JsonOpenFactWriter extends JCasFileWriter_ImplBase {

  override def process(jCas: JCas) = {
    val jsonDoc = new JsonDocument(jCas)

    val dir = getTargetLocation
    val outputFile = new File(dir, jsonDoc.docID + ".json")

    JsonStream.serialize(jsonDoc, new FileOutputStream(outputFile))
  }
}
