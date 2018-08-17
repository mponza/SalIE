package de.mpg.mpi.uima.engines.salie.pagerank.edges.weighting.embeddings

import java.util

import de.mpg.mpi.uima.utils.StopWordUtils
import it.cnr.isti.hpc.Word2VecCompress
import it.unimi.dsi.fastutil.io.BinIO
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class CompressedEmbeddings(filename: String) {

  private val logger = LoggerFactory.getLogger(classOf[CompressedEmbeddings])

  // just to keep class simple
  CompressedEmbeddings.filename = filename
  private val model = CompressedEmbeddings.lazyModel


  /**
    * Returns the centroid embedding vector of a text (sequence of words).
    *
    * @param text
    */
  def getEmbeddingVector(text: String) = {
    val normWords = StopWordUtils.text2NormalizedTokenSet(text)
    val vectors = getWordEmbeddingVectors(normWords)
    getCentroidEmbeddingVector(vectors)
  }


  /**
    * Computes the centroid vector of vectors.
    *
    * @param vectors
    * @return
    */
  private def getCentroidEmbeddingVector(vectors : util.Collection[Array[Float]]) = {
    val centroid = Array.fill[Float](model.dimensions())(0)
    for(vector <- vectors.asScala) {
      Range(0, model.dimensions())
        .foreach(index => {
        centroid(index) = centroid(index) + (vector(index) / vectors.size())
      })
    }

    centroid
  }


  /**
    * Maps each word into its embedding vector.
    *
    * @param words
    * @return
    */
  private def getWordEmbeddingVectors(words : util.Collection[String]) = {
    words.asScala
      .map(word => getWordEmbeddingVector(word))
      .asJavaCollection
  }


  /**
    * Returns the embedding vector of a word (warning: word has to be normalized has done in getNormalizedWords method)
    *
    * @param word
    * @return
    */
  private def getWordEmbeddingVector(word: String) : Array[Float] = {
    val vector = model.get(word)

    if(vector == null) {
      return Array.fill[Float](model.dimensions())(0)
    }

    vector
  }


  def getDimensions() = model.dimensions()
}


object CompressedEmbeddings {
  private val logger = LoggerFactory.getLogger(classOf[CompressedEmbeddings])

  var filename: String = ""
  lazy val lazyModel: Word2VecCompress = {
    logger.info("Loading embedding model from %s...".format(filename))
    val m = BinIO.loadObject(filename).asInstanceOf[Word2VecCompress]
    logger.info("Done.")
    m
  }
}