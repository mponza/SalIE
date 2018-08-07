package de.mpg.mpi

import de.mpg.mpi.uima.pipelines.analysis.StanfordNLPAnalysisPipeline
import de.mpg.mpi.uima.pipelines.extraction.MinIEPipeline
import de.mpg.mpi.uima.readers.ReaderFactory
import de.tudarmstadt.ukp.dkpro.core.api.ner.`type`.NamedEntity
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.`type`.Sentence
import org.apache.uima.fit.factory.{AnalysisEngineFactory, JCasFactory}
import org.apache.uima.fit.pipeline.SimplePipeline
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas

import scala.collection.mutable.ListBuffer

object RunPipeline {

  def main(args: Array[String]): Unit = {

    val conf = new SalIEArgs(args)

    val reader = ReaderFactory.getTextReader(conf.dataDir.getOrElse(""))
//    val pipeline = new StanfordNLPAnalysisPipeline().getEngines()
    val pipeline = new MinIEPipeline(conf).getEngines()

    var totnes = 0
    val batch = 5
    while(reader.hasNext) {

      var n = 0
      val jCasList = ListBuffer.empty[JCas]
      while(reader.hasNext && n < batch) {

        val jcas = JCasFactory.createJCas()
        reader.getNext( jcas.getCas )
        jCasList.append( jcas )

        n += 1
      }

      println(jCasList.size)
//      jCasList.foreach(x => SimplePipeline.runPipeline(x, pipeline(0), pipeline(1), pipeline(2)))// pipeline:_*) )

      println("Analysis engine description factory...")
      val aaeDesc = AnalysisEngineFactory.createEngineDescription(pipeline:_*)
      println("Analysis engine creation...")
      val aae = AnalysisEngineFactory.createEngine(aaeDesc)
      aae.process(jCasList(0))
      jCasList.slice(1, jCasList.size).par.foreach(x => aae.process(x))

      val nes = jCasList.map(x => JCasUtil.select(x, classOf[NamedEntity]).size()).sum
      println("Toal number of named entities is: " + nes )

      println( "Total number of sentences is " + jCasList.map(x => JCasUtil.select(x, classOf[Sentence]).size()).sum )

      totnes += nes

      println("Processed " + n + " documents...")

    }

    println("Done. With a total of " + totnes + " named entities.")

  }




//  public static void runPipeline(final CollectionReader reader,
//  final AnalysisEngineDescription... descs) throws UIMAException, IOException {
//    AnalysisEngine aae = null;
//    try {
//      // Create AAE
//      final AnalysisEngineDescription aaeDesc = createEngineDescription(descs);
//
//      // Instantiate AAE
//      aae = createEngine(aaeDesc);
//
//      // Create CAS from merged metadata
//      ResourceManager resMgr = ResourceManagerFactory.newResourceManager();
//      final CAS cas = CasCreationUtils.createCas(asList(reader.getMetaData(), aae.getMetaData()),
//        null, resMgr);
//      reader.typeSystemInit(cas.getTypeSystem());
//
//      // Process
//      while (reader.hasNext()) {
//        reader.getNext(cas);
//        aae.process(cas);
//        cas.reset();
//      }
//
//      // Signal end of processing
//      aae.collectionProcessComplete();
//    } finally {
//      // Destroy
//      LifeCycleUtil.destroy(aae);
//    }
//  }

}
