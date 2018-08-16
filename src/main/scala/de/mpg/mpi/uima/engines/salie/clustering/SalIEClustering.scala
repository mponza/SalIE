package de.mpg.mpi.uima.engines.salie.clustering

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Clusterizes open facts and then, for each of them, keep only the most salient open fact.
  */
class SalIEClustering {

  private val logger = LoggerFactory.getLogger(classOf[SalIEClustering])


  def diversify(jCas: JCas) : Unit = {

    val clusterID2SalieOpenFact = new Object2ObjectOpenHashMap[String, SalIEOpenFact]()

    //
    // Computes open facts clusters with respect to open facts' heads

    val salieOpenFacts = JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala.toList
    salieOpenFacts
      .foreach(salieOpenFact => {

        val head = getClusterID(salieOpenFact)

        val currentSalieOpenFact = clusterID2SalieOpenFact.putIfAbsent(head, salieOpenFact)
        if( currentSalieOpenFact != null && !currentSalieOpenFact.equals(salieOpenFact) &&
            currentSalieOpenFact.getSalience < salieOpenFact.getSalience
          ) {
          clusterID2SalieOpenFact.put(head, salieOpenFact)
        }
      })


    //
    // Removes that open facts which are not the most salient within their clusters

    salieOpenFacts.filter(
      salieOpenFact => {  // in its cluster there is another open fact with higher salience score
        !salieOpenFact.equals( clusterID2SalieOpenFact.get( getClusterID(salieOpenFact) ) )
      })
      .foreach(salieOpenFact => salieOpenFact.removeFromIndexes())
  }


  private def getClusterID(salieOpenFact: SalIEOpenFact) = {
    salieOpenFact.getSubject.getHead.getCoveredText.toLowerCase
  }


}
