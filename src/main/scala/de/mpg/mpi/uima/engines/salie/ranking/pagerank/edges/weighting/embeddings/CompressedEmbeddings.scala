package de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.weighting.embeddings

import java.util

import it.cnr.isti.hpc.Word2VecCompress
import it.unimi.dsi.fastutil.io.BinIO
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class CompressedEmbeddings(filename: String) {

  private val logger = LoggerFactory.getLogger(classOf[CompressedEmbeddings])
  private val model = BinIO.loadObject(filename).asInstanceOf[Word2VecCompress]


  /**
    * Returns the centroid embedding vector of a text (sequence of words).
    *
    * @param text
    */
  def getEmbeddingVector(text: String) = {
    val normWords = getNormalizedWords(text)
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
    * Maps text's words into a set of normalized words.
    *
    * @param text
    * @return
    */
  private def getNormalizedWords(text: String) : ObjectOpenHashSet[String]  = {
    val normWords = new ObjectOpenHashSet[String]()

    text.split(" ")
      .filter(word => !word.matches("[^a-zA-Z0-9 ]"))
      .map(word => word.toLowerCase())
      .foreach(normWord => normWords.add(normWord))

    normWords
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
}
