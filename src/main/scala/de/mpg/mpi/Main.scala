package de.mpg.mpi

import de.mpg.mpi.uima.pipelines.StanfordNLPAnalysisPipeline
import de.mpg.mpi.uima.readers.ReaderFactory
import org.apache.uima.fit.factory.JCasFactory
import org.apache.uima.fit.pipeline.SimplePipeline
import org.apache.uima.jcas.JCas

import scala.collection.mutable.ListBuffer

object RunPipeline {

  def main(args: Array[String]): Unit = {

    val reader = ReaderFactory.getTextReader(args(0))
    val pipeline = new StanfordNLPAnalysisPipeline().getEngines()

    val batch = 100
    while(reader.hasNext) {

      var n = 0
      val jCasList = ListBuffer.empty[JCas]
      while(reader.hasNext && n < batch) {

        val jcas = JCasFactory.createJCas()
        reader.getNext( jcas.getCas )
        jCasList.append( jcas )

        n += 1
      }

      println(jCasList.size)
      jCasList.foreach(x => SimplePipeline.runPipeline(x, pipeline:_*) )
      println("Processed " + n + " documents...")

    }

    println("Done.")

  }

}
