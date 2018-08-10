package de.mpg.mpi.uima.engines

import de.mpg.mpi.uima.`type`.{MinIEOpenFact, OpenFact, SalIEOpenFact}
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

class PrinterAnalysisEngine  extends JCasAnnotator_ImplBase {

  private val logger = LoggerFactory.getLogger(classOf[PrinterAnalysisEngine])

  override def process(aJCas: JCas) = {

    var n = 0
    for(openFact <- JCasUtil.select(aJCas, classOf[SalIEOpenFact]).asScala) {
      logger.info(openFact.getText + " <-- generated from -- " + openFact.getMinieOpenFact.getText)
      n += 1
    }

    logger.info("Total number of facts is " + n)

  }
}
