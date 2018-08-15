package de.mpg.mpi.uima.io.writers.json.support

import com.jsoniter.annotation.JsonProperty
import de.mpg.mpi.uima.`type`.SalIEOpenFact

class JsonOpenFact(salieOpenFact: SalIEOpenFact) {

  @JsonProperty
  var text: String = salieOpenFact.getText

  @JsonProperty
  var salience: Float = salieOpenFact.getSalience

}
