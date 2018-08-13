package de.mpg.mpi.uima.engines.salie

import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.mpg.mpi.uima.utils.StopWordUtils
import it.unimi.dsi.fastutil.objects.{ObjectArrayList, ObjectOpenHashSet}
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
    pruneDuplicatedFacts(jCas)
    pruneThreeTokensFacts(jCas)
  }


  //
  // (1) Null/Empty string constituent

  private def pruneNullEmptyStringConstituentFacts(jCas: JCas) = {
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
    if(salieOpenFact.getSubject.getText == "" ||
      salieOpenFact.getRelation.getText == "" ||
      salieOpenFact.getObject.getText == "") {
      return true
    }

    false
  }


  //
  // (2) Facts whose tokens are included in other facts

  /**
    * Removes facts whose (cleaned) tokens are already present within other facts.
    *
    * @param jCas
    */
  private def pruneDuplicatedFacts(jCas: JCas) = {
    val keptFacts = new ObjectOpenHashSet[ObjectOpenHashSet[String]]()  // an element is a set of tokens of a fact
    val toPruneFacts = new ObjectArrayList[SalIEOpenFact]()

    for(sentence <- JCasUtil.select(jCas, classOf[SalIEOpenFact])) {

      JCasUtil.selectCovered(jCas, classOf[SalIEOpenFact], sentence).asScala
        .foreach(salieOpenFact => {

          val factTokens = StopWordUtils.text2CleanedTokenSet(salieOpenFact.getText)

          if(keptFacts.contains(factTokens)) {
            toPruneFacts.add(salieOpenFact)
          } else {
            keptFacts.add(factTokens)
          }

        }
      )

    }

    toPruneFacts.asScala.foreach(fact => fact.removeFromIndexes())
  }


  //
  // (3) Facts which are too short, i.e. one token for constitutent.

  private def pruneThreeTokensFacts(jCas: JCas) = {
    JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala
      .filter(salieOpenFact => hasOneTokenForConstituent(salieOpenFact))
      .foreach(salieOpenFact => salieOpenFact.removeFromIndexes())
  }

  private def hasOneTokenForConstituent(salIEOpenFact: SalIEOpenFact) = {
    salIEOpenFact.getSubject.getTokens().size() == 1 &&
    salIEOpenFact.getRelation.getTokens.size() == 1 &&
    salIEOpenFact.getObject.getTokens.size() == 1
  }
}
