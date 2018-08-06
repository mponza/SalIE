package de.mpg.mpi.engines

import de.mpg.mpi.uima.types.{Constituent, MinIEOpenFact}
import de.mpg.mpi.uima.utils.SemanticSentences
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Token
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.internal.TokenKey
import de.uni_mannheim.minie.MinIE
import de.uni_mannheim.minie.annotation.AnnotatedPhrase
import edu.stanford.nlp.semgraph.SemanticGraph
import org.apache.uima.UimaContext
import org.apache.uima.fit.component.JCasAnnotator_ImplBase
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class MinIEAnalysisEngine(mode: MinIE.Mode) extends JCasAnnotator_ImplBase {

  val logger = LoggerFactory.getLogger(classOf[MinIEAnalysisEngine])


  override def initialize(context: UimaContext): Unit = {
    super.initialize(context)
  }


  override def process(jCas: JCas) = {
    val semanticSentences = new SemanticSentences(jCas)
    val sentences = JCasUtil.select(jCas, classOf[Sentence])

    for(sentence: Sentence <- sentences) {
      val semanticGraph = semanticSentences.getSemanticGraph(sentence)
      if(semanticGraph != null) {

        val minIE = new MinIE(sentence.getCoveredText, semanticGraph, mode)

        val validPropositions = minIE.getPropositions.stream().forEach(
          proposition => {

            val openFact = new MinIEOpenFact(jCas)

            val subject = addConsti
        }
      )


        for(proposition <- minIE.getPropositions)

        // add MinIE annotations
      }
    }
  }


  /**
    * Creates and returns a constituent of class clazz given a phrase and the sentence semantic graph.
    *
    * @param jCas
    * @param clazz
    * @param phrase
    * @param semanticGraph
    * @tparam T
    */
  private def addConstituent2JCas[T](jCas: JCas, clazz: Class[T], phrase: AnnotatedPhrase, semanticGraph: SemanticGraph) = {
    val words = phrase.getWordList
    var begin = Integer.MAX_VALUE
    var end = -1


    // setting constituent tokens

    val tokens = new FSArray(jCas, words.size())
    words.asScala.zipWithIndex.foreach(
      indexedWord => {

        val word = indexedWord._1
        val index = indexedWord._2


        // adding constituent token

        var token = new Token(jCas)
        if(word.get(classOf[TokenKey]) == null) {
          token.setId(word.value)
        } else {
          token = word.get(classOf[TokenKey])
        }
        tokens.set(index, token)


        // constituent begin/end update

        if(token.getBegin < begin) {
          begin = token.getBegin
        }
        if(token.getEnd + 1 > end) {
          end = token.getEnd
        }
      }
    )
    tokens.addToIndexes()
    val constituent = new Constituent(jCas, begin, end)
    constituent.setTokens(tokens)


    // setting text

    constituent.setText(phrase.toString)


    // setting head by properly managing erroneous cases

    val head = phrase.getHead(semanticGraph)
    var tokenHead = new Token(jCas)
    if(head != null && head.get(classOf[TokenKey]) == null) {
      tokenHead.setId(head.value())
      tokenHead.setBegin(head.beginPosition())
      tokenHead.setEnd(head.endPosition())
    } else {
      tokenHead = if (head != null) { head.get(classOf[TokenKey]) } else { null }
    }
    constituent.setHead(tokenHead)

    jCas.addFsToIndexes(constituent)
    constituent
  }


//      List<IndexedWord> words = p.getWordList();
//      FSArray tokens = new FSArray(jCas, words.size());
//      int begin = Integer.MAX_VALUE;
//      int end = -1;
//      for (int i = 0; i < words.size(); i++) {
//        IndexedWord w = words.get(i);
//        Token token;
//        if(w.get(TokenKey.class) == null) {
//          token = new Token(jCas);
//          token.setId(w.value());
//        } else {
//          token = w.get(TokenKey.class);
//        }
//        tokens.set(i, token);
//        if (token.getBegin() < begin) {
//          begin = token.getBegin();
//        }
//        if (token.getEnd() + 1 > end) {
//          end = token.getEnd();
//        }
//      }
//      tokens.addToIndexes();
//      Constituent annotation = getInstancedConstitient(jCas, begin, end, clazz);
//      annotation.setTokens(tokens);
//      annotation.setText(p.toString());
//      IndexedWord head = p.getHead(sentSemGraph);
//
//      Token token;
//      if(head != null && head.get(TokenKey.class) == null) {
//        token = new Token(jCas);
//        token.setId(head.value());
//        token.setBegin(head.beginPosition());
//        token.setEnd(head.endPosition());
//      } else {
//        token = head != null ? head.get(TokenKey.class) : null;
//      }
//      annotation.setHead(token);
//      jCas.addFsToIndexes(clazz.cast(annotation));
//      return clazz.cast(annotation);
//  }


//
//  @Override public void process(JCas jCas) throws AnalysisEngineProcessException {
//
//    // before it was caoscoref
//    JCasSemanticSentences semSent = new JCasSemanticSentences(jCas);
//    int n = 0;
//
//    for(Sentence sentence : JCasUtil.select(jCas, Sentence.class)) {
//      try {
//        SemanticGraph sentSemGraph = semSent.getSemanticGraph(sentence);
//
//        MinIE minIE = new MinIE(sentence.getCoveredText(), sentSemGraph, mode);
//
//        for (AnnotatedProposition ap : minIE.getPropositions()) {
//
//          try {
//
//            if (validConstituent(ap.getSubject()) && validConstituent(ap.getRelation()) && validConstituent(ap.getObject())) {
//
//              addNegativeWord(ap);
//
//              OpenFact of = new OpenFact(jCas);
//
//              Subject subject = addConstituentToJCas(jCas, Subject.class, ap.getSubject(), sentSemGraph);
//              of.setBegin(subject.getBegin());
//              of.setSubject(subject);
//
//              Relation relation = addConstituentToJCas(jCas, Relation.class, ap.getRelation(), sentSemGraph);
//              of.setRelation(relation);
//
//              ObjectF object = addConstituentToJCas(jCas, ObjectF.class, ap.getObject(), sentSemGraph);
//              of.setEnd(object.getEnd());
//              of.setObject(object);
//
//              of.setText(String.format("(%s, %s, %s)",
//                ap.getSubject().toString(),
//                ap.getRelation().toString(),
//                ap.getObject().toString()));
//
//              of.addToIndexes();
//              n++;
//
//            } else {
//              // logger.warn(String.format("%s is not a valid fact", ap.toString()));
//            }
//
//          } catch (Exception e) {
//            logger.error(String.format("Error with fact %s", ap.toString()));
//            e.printStackTrace();
//          }
//
//        }
//
//      } catch (Exception e) {
//        logger.error(String.format("Error with document %s, sentence: %s",
//          JCasUtil.selectSingle(jCas, DocumentMetaData.class).getDocumentId(),
//        sentence.getCoveredText()
//        ));
//      }
//    }
//
//
//    if(n == 0) {
//      logger.info(String.format("No facts for document %s", JCasUtil.selectSingle(jCas, DocumentMetaData.class).getDocumentId()));
//    }
//
//
//  }
//
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
//
//
//  protected boolean validConstituent(AnnotatedPhrase ap) {
//
//    // logger.info(ap.getWordList().stream().map(x -> x.toString()).collect(Collectors.joining(" ")));
//
//    if(ap.toString().contains("QUANT_")) return false;  // we don't wont fact in this form
//
//    return ap.getBegin() >= 0 && ap.getEnd() >= 0;
//  }
//
//
//  private <K extends Constituent> K addConstituentToJCas(JCas jCas, Class<K> clazz, AnnotatedPhrase p, SemanticGraph sentSemGraph) throws IllegalAccessException {
//    List<IndexedWord> words = p.getWordList();
//    FSArray tokens = new FSArray(jCas, words.size());
//    int begin = Integer.MAX_VALUE;
//    int end = -1;
//    for (int i = 0; i < words.size(); i++) {
//      IndexedWord w = words.get(i);
//      Token token;
//      if(w.get(TokenKey.class) == null) {
//        token = new Token(jCas);
//        token.setId(w.value());
//      } else {
//        token = w.get(TokenKey.class);
//      }
//      tokens.set(i, token);
//      if (token.getBegin() < begin) {
//        begin = token.getBegin();
//      }
//      if (token.getEnd() + 1 > end) {
//        end = token.getEnd();
//      }
//    }
//    tokens.addToIndexes();
//    Constituent annotation = getInstancedConstitient(jCas, begin, end, clazz);
//    annotation.setTokens(tokens);
//    annotation.setText(p.toString());
//    IndexedWord head = p.getHead(sentSemGraph);
//
//    Token token;
//    if(head != null && head.get(TokenKey.class) == null) {
//      token = new Token(jCas);
//      token.setId(head.value());
//      token.setBegin(head.beginPosition());
//      token.setEnd(head.endPosition());
//    } else {
//      token = head != null ? head.get(TokenKey.class) : null;
//    }
//    annotation.setHead(token);
//    jCas.addFsToIndexes(clazz.cast(annotation));
//    return clazz.cast(annotation);
//  }
//
//  private<K extends Constituent> K getInstancedConstitient(JCas jCas, int begin, int end, Class<K> clazz) {
//    if(clazz.equals(Subject.class)) {
//      return clazz.cast(new Subject(jCas, begin, end));
//    } else if(clazz.equals(Relation.class)) {
//      return clazz.cast(new Relation(jCas, begin, end));
//    } else if(clazz.equals(ObjectF.class)) {
//      return clazz.cast(new ObjectF(jCas, begin, end));
//    } else {
//      throw new IllegalArgumentException("Open facts should be composed by a subject, a relation and an object");
//    }
//  }

}
