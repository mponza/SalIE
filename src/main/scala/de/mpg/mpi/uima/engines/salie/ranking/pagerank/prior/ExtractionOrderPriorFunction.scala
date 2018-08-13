package de.mpg.mpi.uima.engines.salie.ranking.pagerank.prior
import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.mpg.mpi.uima.engines.salie.ranking.pagerank.graph.SalIEOpenFactGraph
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap
import org.apache.uima.fit.util.JCasUtil
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class ExtractionOrderPriorFunction extends RankingPriorFunction {

  private val logger = LoggerFactory.getLogger(classOf[ExtractionOrderPriorFunction])
  private val nodeID2Prior = new Int2DoubleOpenHashMap()

  override def weightNodes(salieOpenFactGraph: SalIEOpenFactGraph) = {
    val nVertices = salieOpenFactGraph.getDirectedSparseGraph.getVertexCount

    var i = 1
    var normFactor = 0


    //
    // Computes prior for each node in the graph.

    JCasUtil.select(salieOpenFactGraph.getJCas, classOf[Sentence]).asScala
      .foreach(sentence => {

        JCasUtil.selectCovered(salieOpenFactGraph.getJCas, classOf[SalIEOpenFact], sentence).asScala
          .foreach(salieOpenFact => {

            val prior = nVertices - i
            val nodeID = salieOpenFactGraph.getNodeID(salieOpenFact)
            nodeID2Prior.put(nodeID, prior)

            normFactor += prior
            i += 1

          })

      })

    
    //
    // Normalizes prior

    salieOpenFactGraph.getDirectedSparseGraph.getVertices.asScala
      .foreach(nodeID => nodeID2Prior.put( nodeID, nodeID2Prior.get(nodeID) / normFactor ))

  }

  override def apply(nodeID: Int): Double = nodeID2Prior.get(nodeID)

}