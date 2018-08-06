name := "SalIE"
version := "0.1"
scalaVersion := "2.12.6"
scalacOptions += "-target:jvm-1.8"
scalacOptions ++= Seq("-feature", "-deprecation")
fork in run := true


libraryDependencies ++= Seq(
  "it.unimi.dsi" % "fastutil" % "8.1.1",
  "it.unimi.dsi" % "dsiutils" % "2.4.2",

  "net.sf.jopt-simple" % "jopt-simple" % "6.0-alpha-1",

  "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models",

  "org.dkpro.core" % "dkpro-core-lancaster-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "dkpro-core-io-nyt-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.metadata-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.io.bincas-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.ner-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.io.text-asl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-gpl" % "1.9.3",
  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.corenlp-gpl" % "1.9.3",


  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.api.coref-asl" % "1.9.3",
//  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-model-coref-en-default" % "20170609.1",
//  "de.tudarmstadt.ukp.dkpro.core" % "de.tudarmstadt.ukp.dkpro.core.stanfordnlp-model-upstream-coref-en-default" % "20170609",

  "com.typesafe" % "config" % "1.3.3"
)