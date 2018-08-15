package de.mpg.mpi.uima.engines.salie.support

import de.mpg.mpi.uima.`type`.{Constituent, SalIEOpenFact}
import de.mpg.mpi.uima.utils.StopWordUtils
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * Removes that SalIEOpenFacts which are considered uninformative.
  */
class SalIEOpenFactPruningAnalysisEngine extends JCasAnnotator_ImplBase {

  private val logger = LoggerFactory.getLogger(classOf[SalIEOpenFactPruningAnalysisEngine])


  override def process(jCas: JCas) = {
    pruneNullEmptyStringConstituentFacts(jCas)
    pruneThreeTokensFacts(jCas)
    pruneStopWordsFacts(jCas)
    pruneIncludedFacts(jCas)
  }


  //
  // (1) Null/Empty string constituent.

  private def pruneNullEmptyStringConstituentFacts(jCas: JCas) : Unit = {
    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala
      .filter(salieOpenFact => hasEmptyConstituent(salieOpenFact))
      .foreach(salieOpenFact => salieOpenFact.removeFromIndexes())
  }

  private def hasEmptyConstituent(salieOpenFact: SalIEOpenFact) : Boolean = {
    // no null constituent
    if(salieOpenFact.getSubject == null ||
      salieOpenFact.getRelation == null ||
      salieOpenFact.getObject == null) {
      return true
    }

    // no empty string constituent
    if(salieOpenFact.getSubject.getText.equals("") ||
      salieOpenFact.getRelation.getText.equals("") ||
      salieOpenFact.getObject.getText.equals("")) {
      return true
    }

    false
  }


  //
  // (2) Facts which are too short, i.e. one token for constitutent.

  private def pruneThreeTokensFacts(jCas: JCas) : Unit = {
    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala
      .filter(salieOpenFact => hasOneTokenForConstituent(salieOpenFact))
      .foreach(salieOpenFact => salieOpenFact.removeFromIndexes())
  }

  private def hasOneTokenForConstituent(salIEOpenFact: SalIEOpenFact) = {
    salIEOpenFact.getSubject.getTokens().size() == 1 &&
    salIEOpenFact.getRelation.getTokens.size() == 1 &&
    salIEOpenFact.getObject.getTokens.size() == 1
  }


  //
  // (3) Facts whose constituents have only stopwords.

  private def pruneStopWordsFacts(jCas: JCas) : Unit = {
    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala

      .filter(salieOpenFact =>
         hasOnlyStopWords( salieOpenFact.getSubject ) ||
         hasOnlyStopWords( salieOpenFact.getRelation  ) ||
         hasOnlyStopWords( salieOpenFact.getObject )

      ).foreach(salieOpenFact => salieOpenFact.removeFromIndexes())
  }

  private def hasOnlyStopWords(constituent: Constituent) = {
    StopWordUtils.text2CleanedTokenSet( constituent.getText ).isEmpty
  }


  //
  // (4) For each sentence, it removes that facts which are fully included in another fact.

  private def pruneIncludedFacts(jCas: JCas) : Unit = {
    val facts2prune = new ObjectArrayList[SalIEOpenFact]()

    for(sentence <- JCasUtil.select(jCas, classOf[Sentence]).asScala) {
      val sentenceFacts = JCasUtil.selectCovered(jCas, classOf[SalIEOpenFact], sentence).asScala
      for(salieOpenFact <- sentenceFacts) {

        val otherSalieOpenFacts = sentenceFacts.filter(f => !f.equals(salieOpenFact)).toList
        if(otherSalieOpenFacts.nonEmpty) {
          // at least one more fact different from salieOpenFact

          val mainFactWords = StopWordUtils.text2CleanedTokenSet(salieOpenFact.getText)

          for(otherSalieOpenFact <- otherSalieOpenFacts) {
            val otherFactWords = StopWordUtils.text2CleanedTokenSet(otherSalieOpenFact.getText)
            otherFactWords.removeAll(mainFactWords)
            if(otherFactWords.isEmpty) {
              facts2prune.add(otherSalieOpenFact)
            }
          }
        }
      }
    }

    facts2prune.asScala.foreach(salieOpenFact => salieOpenFact.removeFromIndexes())
  }
}
