name := "SalIE"
version := "0.1"
scalaVersion := "2.12.6"

scalacOptions += "-target:jvm-1.8"
//scalacOptions ++= Seq("-feature", "-deprecation", "-encoding", "utf8")
scalacOptions ++= Seq("-encoding", "utf8")

fork in run := true

javaOptions in run += "-Xmx20G"
javaOptions += "-Dfile.encoding=UTF8"

//
//resourceDirectory in (Compile, runMain) := baseDirectory.value / "src" / "main" / "resources"
//logLevel := Level.Error

libraryDependencies ++= Seq(

  "it.unimi.dsi" % "fastutil" % "8.1.1",
  "it.unimi.dsi" % "dsiutils" % "2.4.2",
  "it.unimi.dsi" % "sux4j" % "4.2.0",

  "net.sf.jopt-simple" % "jopt-simple" % "6.0-alpha-1",

  "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models",

  "org.dkpro.core" % "dkpro-core-lancaster-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "dkpro-core-io-nyt-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.metadata-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.io.bincas-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.ner-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.io.text-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.segmentation-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-gpl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.corenlp-gpl" % "1.9.3",

  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.coref-asl" % "1.9.3",
//  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-model-coref-en-default" % "20170609.1",
//  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-model-upstream-coref-en-default" % "20170609",

  "org.rogach" %% "scallop" % "3.1.3",

  "com.jsoniter" % "jsoniter" % "0.9.23",
  "com.google.code.gson" % "gson" % "2.8.5",

  "net.sf.jung" % "jung2" % "2.0.1",
  "net.sf.jung" % "jung-graph-impl" % "2.1.1",
  "net.sf.jung" % "jung-algorithms" % "2.1.1",
  "net.sf.jung" % "jung-api" % "2.1.1"
)