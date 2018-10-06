package de.mpg.mpi.uima.io.writers.json.support

import com.jsoniter.annotation.JsonProperty
import de.mpg.mpi.uima.`type`.SalIEOpenFact

class JsonOpenFact(salieOpenFact: SalIEOpenFact) {

  @JsonProperty
  var text: String = salieOpenFact.getText

  // extra fields

//  @JsonProperty
//  var subject: String = salieOpenFact.getSubject.getText
//
//  @JsonProperty
//  var relation: String = salieOpenFact.getRelation.getText
//
//  @JsonProperty
//  var obj: String = salieOpenFact.getObject.getText

  @JsonProperty
  var salience: Float = salieOpenFact.getSalience

}
