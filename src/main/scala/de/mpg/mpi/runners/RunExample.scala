package de.mpg.mpi.runners

import de.mpg.mpi.configs.salie.SalIEConfig
import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.mpg.mpi.uima.pipelines.extraction.SalIEPipeline
import org.apache.uima.fit.factory.{AnalysisEngineFactory, JCasFactory}
import org.apache.uima.fit.util.JCasUtil


import scala.collection.JavaConverters._


/**
  * Shows how to run SalIE inside a custom code on an input text.
  *
  */
object RunExample {

  def main(args: Array[String]): Unit = {
    val inputText = "Write here your input document, just for testing."

    // Configurate SalIE

    val salieConfig = SalIEConfig.getDefault()
    val saliePipeline = new SalIEPipeline(salieConfig)


    // Input has to be JCas object

    val jCas = JCasFactory.createJCas()
    jCas.setDocumentLanguage("en")
    jCas.setDocumentText(inputText)


    // Instantiate engine from SalIE pipeline
    val ae = AnalysisEngineFactory.createEngine(
      AnalysisEngineFactory.createEngineDescription(saliePipeline.getEngines():_*)
    )


    // Run SalIE pipeline and extract salient open facts
    // (for a parallel application please check IOPipeline class)
    ae.process(jCas)


    // Access to open facts and print them in descending salience order

    val salientOpenFacts = JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala.toList
      .sortBy(salieOpenFact => - salieOpenFact.getSalience)

    for(salieOpenFact <- salientOpenFacts) {
      println("Open Fact: %s with Salience: %1.5f".format(salieOpenFact.getText, salieOpenFact.getSalience))
    }
  }

}
