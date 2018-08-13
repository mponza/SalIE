package de.mpg.mpi.uima.engines.salie.ranking.pagerank.edges.structure

object GraphStructureFactory {

  def apply(graphStructure: String) : GraphStructureCreation = graphStructure match {
    case "clique" => new CliqueCreation
    case _ => throw new IllegalArgumentException("A graph with structure %s can not be created.".format(graphStructure))
  }

}
