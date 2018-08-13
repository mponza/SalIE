package de.mpg.mpi.uima.engines.salie.salience.pagerank.graph

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import edu.uci.ics.jung.graph.DirectedSparseGraph
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Contains the graph of facts and their mapping to indexes (both nodes and edges).
  *
  * @param jCas
  */
class SalIEGraph(jCas: JCas) {
  private val logger = LoggerFactory.getLogger(classOf[SalIEOpenFact])

  // SalIEOpenFact - index mapping
  private val salieOpenFact2nodeID = new Object2IntOpenHashMap[SalIEOpenFact]()
  private val nodeID2salieOpenFact = new Int2ObjectOpenHashMap[SalIEOpenFact]()

  // graph of open facts
  private val graph: DirectedSparseGraph[Int, Long] = createGraph(jCas)


  /**
    * Creates a graph where open facts are mapped to their nodeID.
    * No edges are created.
    *
    * @param jCas
    * @return
    */
  private def createGraph(jCas: JCas) = {
    val directedGraph = new DirectedSparseGraph[Int, Long]

    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala.zipWithIndex
      .foreach(indexedSalieOpenFact => {

        val index = indexedSalieOpenFact._2 + 1     // we want vertices 1-based
        val salieOpenFact = indexedSalieOpenFact._1

        salieOpenFact2nodeID.put(salieOpenFact, index)
        nodeID2salieOpenFact.put(index, salieOpenFact)
        directedGraph.addVertex(index)
      })

    directedGraph
  }

  def getJCas : JCas = jCas
  def getDirectedSparseGraph = graph


  //
  // Mapping functions between open facts to nodeIDs and viceversa.

  def getSalIEOpenFact(nodeID: Int) = nodeID2salieOpenFact.get(nodeID)
  def getNodeID(salIEOpenFact: SalIEOpenFact) = salieOpenFact2nodeID.get(salIEOpenFact)


  //
  // Mapping functions between an edgeID and its source/destination nodes and viceversa.

  def getSourceNodeID(edge: Long) :Int = (edge >> 32).toInt
  def getDestinationNodeID(edge: Long) : Int = edge.toInt
  def getEdgeID(srcNodeID: Int, dstNodeID: Int) = {
    val srcShifted = (srcNodeID << 32).asInstanceOf[Long]
    srcShifted | dstNodeID
  }


}
