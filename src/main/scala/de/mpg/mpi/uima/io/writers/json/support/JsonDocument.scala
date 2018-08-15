package de.mpg.mpi.uima.io.writers.json.support

import com.jsoniter.annotation.JsonProperty
import de.mpg.mpi.uima.`type`.SalIEOpenFact
import de.tudarmstadt.ukp.dkpro.core.api.metadata.`type`.DocumentMetaData
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas

import scala.collection.JavaConverters._


class JsonDocument(jCas: JCas) {
  @JsonProperty
  var docID: String = JCasUtil.selectSingle(jCas, classOf[DocumentMetaData]).getDocumentId
  // removes extension from ID
  docID = docID.substring(0, docID.lastIndexOf("."))

  @JsonProperty
  var text: String = jCas.getDocumentText

  @JsonProperty
  var openfacts: Array[JsonOpenFact] = JCasUtil.select(jCas, classOf[SalIEOpenFact]).asScala
    .map(salieOpenFact => new JsonOpenFact(salieOpenFact)).toArray

}
