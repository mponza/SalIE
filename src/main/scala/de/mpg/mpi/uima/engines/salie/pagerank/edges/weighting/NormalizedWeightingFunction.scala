package de.mpg.mpi.uima.engines.salie.pagerank.edges.weighting

import java.lang.Double

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.mpg.mpi.uima.engines.salie.pagerank.graph.SalIEGraph
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


abstract class NormalizedWeightingFunction extends WeightingFunction {

  private val logger = LoggerFactory.getLogger(classOf[NormalizedWeightingFunction])
  private val weights = new Long2DoubleOpenHashMap()


  /**
    * Preprocessing, if needed.
    *
    * @param salIEGraph
    */
  protected def initialize(salIEGraph: SalIEGraph) : Unit = {}


  override def weightEdges(salIEGraph: SalIEGraph): Unit = {
    initialize(salIEGraph)
    computeEdgeWeights(salIEGraph, weights)
  }


  /**
    * Fills weights with doubles (edges' weights).
    *
    * @param salIEGraph
    * @param weights
    */
  def computeEdgeWeights(salIEGraph: SalIEGraph, weights: Long2DoubleOpenHashMap) : Unit = {

    val normFactors = new Int2DoubleOpenHashMap()  // will contain the normalization factor for each node


    //
    // Computes weights of nodes' edges.

    salIEGraph.getDirectedSparseGraph.getEdges.asScala
      .foreach(
        edgeID => {

          if(!weights.containsKey(edgeID)) {

            val srcNodeID = salIEGraph.getSourceNodeID(edgeID)
            val dstNodeID = salIEGraph.getDestinationNodeID(edgeID)

            val srcSalieOpenFact = salIEGraph.getSalIEOpenFact(srcNodeID)
            val dstSalieOpenFact = salIEGraph.getSalIEOpenFact(dstNodeID)

            val weight = computeWeight(srcSalieOpenFact, dstSalieOpenFact)

            //
            // (un-normalized) weights are supposed symmetric so they are added to both
            // edge directions (i.e. src -> dst and dst -> src)

            // src -- [edgeID, weight] --> dst
            weights.put(edgeID, weight)

            // dst -- [dstSrcEdgeID, weight] --> src
            val dstSrcEdgeID = salIEGraph.getEdgeID(dstNodeID, srcNodeID)
            weights.put(dstSrcEdgeID, weight)


            //
            // saves normalization factor for both src and dst nodes

            normFactors.put(srcNodeID, normFactors.getOrDefault(srcNodeID, 0.0) + weight)
            normFactors.put(dstNodeID, normFactors.getOrDefault(dstNodeID, 0.0) + weight)
          }

        }
      )


    //
    // Normalizes weights with respect to sum of out-weights.

    weights.keySet().asScala
        .foreach(edgeID => {

          val srcNodeID = salIEGraph.getSourceNodeID(edgeID)
          var srcNormFactor = normFactors.get(srcNodeID)

          if(srcNormFactor > 0) {
            weights.put(edgeID, weights.get(edgeID) / srcNormFactor)
          }

        })

  }

  def apply(edge: Long) : Double = weights.get(edge)

  def computeWeight(src: SalIEOpenFact, dst: SalIEOpenFact) : Double

}
