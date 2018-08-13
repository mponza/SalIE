package de.mpg.mpi.uima.engines.salie.salience.pagerank.edges.weighting

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.mpg.mpi.uima.engines.salie.salience.pagerank.edges.weighting.embeddings.CompressedEmbeddings
import de.mpg.mpi.uima.engines.salie.salience.pagerank.graph.SalIEGraph
import it.cnr.isti.hpc.LinearAlgebra
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class EmbeddingWeightingFunction(filename : String) extends NormalizedWeightingFunction {

  private val logger = LoggerFactory.getLogger(classOf[EmbeddingWeightingFunction])
  private lazy val embeddings = new CompressedEmbeddings(filename)

  private val salieOpenFact2Vector = new Object2ObjectOpenHashMap[SalIEOpenFact, Array[Float]]()


  /**
    * Precomputes the centroid embeddings of all SalIEOpenFacts.
    *
    * @param salIEGraph
    */
  override protected def initialize(salIEGraph: SalIEGraph) = {
    salIEGraph.getDirectedSparseGraph.getVertices.asScala
      .map(
        nodeID => {
          val salieOpenFact = salIEGraph.getSalIEOpenFact(nodeID)
          salieOpenFact2Vector.put(salieOpenFact, embeddings.getEmbeddingVector(salieOpenFact.getText))
      })
  }


  override def computeWeight(src: SalIEOpenFact, dst: SalIEOpenFact) : Double = {
    val srcVector = salieOpenFact2Vector.get(src)
    val dstVector = salieOpenFact2Vector.get(dst)

    val size = embeddings.getDimensions()

    val inner = LinearAlgebra.inner(size, srcVector, 0, dstVector, 0)
    if(inner == 0) return 0

    val srcNorm = Math.sqrt( LinearAlgebra.inner(size, srcVector, 0, srcVector, 0) )
    val dstNorm = Math.sqrt( LinearAlgebra.inner(size, dstVector, 0, dstVector, 0) )

    val cosine = inner / (srcNorm * dstNorm)
    if(cosine.isNaN) throw new IllegalArgumentException("Cosine similarity is NaN!")

    Math.max(0, cosine)
  }
}
