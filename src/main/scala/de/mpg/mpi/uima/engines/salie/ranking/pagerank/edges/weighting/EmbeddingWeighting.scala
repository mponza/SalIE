package de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.weighting

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.weighting.embeddings.CompressedEmbeddings
import org.slf4j.LoggerFactory

class EmbeddingWeighting(filename : String) extends NormalizedWeighting {

  private val logger = LoggerFactory.getLogger(classOf[EmbeddingWeighting])
  private lazy val embeddings = new CompressedEmbeddings(filename)
  
}
