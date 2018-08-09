package de.mpg.mpi.uima.engines.minie

import de.mpg.mpi.uima.`type`.MinIEOpenFact
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Removes from a JCas that open facts which are not valid with respect to
  * several few heuristics (see isValidMinIEOpenFact method).
  */
class ValidateMinIEOpenFactsAnalysisEngine extends JCasAnnotator_ImplBase {
  private val logger = LoggerFactory.getLogger(classOf[ValidateMinIEOpenFactsAnalysisEngine])


  override def process(jCas: JCas) = {
    JCasUtil.select(jCas, classOf[MinIEOpenFact]).asScala
      .filter(x => !isValidMinIEOpenFact(x))
      .foreach(x => x.removeFromIndexes())
  }


  private def isValidMinIEOpenFact(minIEOpenFact: MinIEOpenFact) : Boolean = {
    if(minIEOpenFact.getQuantity != null) return false        // for salience we do not want quantity facts
    minIEOpenFact.getBegin >= 0 && minIEOpenFact.getEnd >= 0  // check all positions make sense (no implicit facts)
  }
}
