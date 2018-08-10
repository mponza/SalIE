package de.mpg.mpi.uima.utils

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet

import scala.io.Source


object StopWordUtils {

  private lazy val stopWords = loadResourceAsSet("/stopwords/stopwords.txt")
  private lazy val pronouns = loadResourceAsSet("/stopwords/pronouns.txt")


  private def loadResourceAsSet(filename: String) : ObjectOpenHashSet[String] = {
    val words = new ObjectOpenHashSet[String]()

    Source.fromInputStream(
      Source.getClass.getResourceAsStream(filename)
    ).getLines().foreach(
      line =>
        words.add( line.trim )
    )

    words
  }


  def isStopWord(word: String) = {
    word.isEmpty || word.matches("[0-9]+") || stopWords.contains( word.replaceAll("[^a-zA-Z ]", "").toLowerCase() )
  }


  def isPronoun(word: String) = pronouns.contains(word.toLowerCase)
}
