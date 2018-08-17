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


  def text2CleanedTokenSet(text : String) : ObjectOpenHashSet[String] = {
    // stopwords are removed!
    val cleanedTokens = new ObjectOpenHashSet[String]()

    text.split(" ")
      .filter(token => !token.matches("[^a-zA-Z0-9 ]"))
      .map(token => token.replaceAll("[^a-zA-Z ]", "").toLowerCase)
      .filter(token => !isStopWord(token))
      .foreach(token => cleanedTokens.add(token))

    cleanedTokens
  }


  def text2NormalizedTokenSet(text : String) : ObjectOpenHashSet[String] = {
    // stopwords are not removed!
    val cleanedTokens = new ObjectOpenHashSet[String]()

    text.split(" ")
      .filter(token => !token.matches("[^a-zA-Z0-9 ]"))
      .map(token => token.replaceAll("[^a-zA-Z ]", "").toLowerCase)
      .foreach(token => cleanedTokens.add(token))

    cleanedTokens
  }
}
