package de.mpg.mpi.uima.io.writers.json

import java.io.{File, FileOutputStream}

import com.jsoniter.extra.GsonCompatibilityMode
import com.jsoniter.output.JsonStream
import de.mpg.mpi.uima.io.writers.json.support.JsonDocument
import de.tudarmstadt.ukp.dkpro.core.api.io.JCasFileWriter_ImplBase
import org.apache.uima.jcas.JCas


class JsonOpenFactWriter extends JCasFileWriter_ImplBase {

  override def process(jCas: JCas) = {
    val jsonDoc = new JsonDocument(jCas)

    val dir = getTargetLocation
    val outputFile = new File(dir, jsonDoc.docID + ".json")

    // no pretty print
    // JsonStream.serialize(jsonDoc, new FileOutputStream(outputFile))

    // with pretty print (default)
    val jsonConfig = new GsonCompatibilityMode.Builder().setPrettyPrinting().build()
    JsonStream.serialize(jsonConfig, jsonDoc, new FileOutputStream(outputFile))

  }
}
