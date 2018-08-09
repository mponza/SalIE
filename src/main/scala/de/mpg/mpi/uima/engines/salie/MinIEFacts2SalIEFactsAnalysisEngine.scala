package de.mpg.mpi.uima.engines.salie

import de.mpg.mpi.uima.`type`.MinIEOpenFact
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


/**
  * For each MinIEOpenFact it generates a proper SalIEOpenFact that is used for fact salience computation.
  * A SalIEOpenFact embeds negative words (MinIE's semantic annotations are not used), has a salient score and
  * contains the respective MinIEOpenFact from which it has been generated.
  *
  */
class MinIEFacts2SalIEFactsAnalysisEngine extends JCasAnnotator_ImplBase {
  private val logger = LoggerFactory.getLogger(classOf[MinIEFacts2SalIEFactsAnalysisEngine])

  override def process(jCas: JCas) = {
    //    val minieFacts = JCasUtil.select(jCas, classOf[MinIEOpenFact]).asScala
    //    val saliieFacts = minieFacts
    //      .map(x => {
    //      new SalIEOpenFact(jCas) // to be created
    //    })
    //  }
  }
}




//  protected static void addNegativeWord(AnnotatedProposition ap) {
//    // to be tested
//    ObjectArrayList<IndexedWord> words = ap.getPolarity().getNegativeWords();
//    words.addAll( ap.getRelation().getWordList() );
//
//    words.sort(new Comparator<IndexedWord>() {
//      @Override
//      public int compare(IndexedWord o1, IndexedWord o2) {
//        return Integer.compare(o1.beginPosition(), o2.beginPosition());
//      }
//    });
//
//    ap.getRelation().setWordList( words );
//  }
//