package de.mpg.mpi.uima.engines

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas

import scala.collection.JavaConverters._


class SalientPositionAnalysisEngine extends JCasAnnotator_ImplBase {

  override def process(jCas: JCas) = {

    val n = JCasUtil.select(jCas, classOf[SalIEOpenFact]).size()
    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala.zipWithIndex
      .foreach(indexedSalieOpenFact => {
        val salieOpenFact = indexedSalieOpenFact._1
        val index = indexedSalieOpenFact._2
        salieOpenFact.setSalience( n - index )
      })

  }

}
