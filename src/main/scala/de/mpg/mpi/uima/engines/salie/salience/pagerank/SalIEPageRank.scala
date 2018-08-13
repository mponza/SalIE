package de.mpg.mpi.uima.engines.salie.salience.pagerank

import de.mpg.mpi.uima.engines.salie.salience.pagerank.config.SalIEPageRankConfig
import de.mpg.mpi.uima.engines.salie.salience.pagerank.edges.structure.GraphStructureFactory
import de.mpg.mpi.uima.engines.salie.salience.pagerank.edges.weighting.{WeightingFactory, WeightingFunction}
import de.mpg.mpi.uima.engines.salie.salience.pagerank.graph.SalIEGraph
import de.mpg.mpi.uima.engines.salie.salience.pagerank.prior.{RankingPriorFactory, RankingPriorFunction}
import edu.uci.ics.jung.algorithms.scoring.PageRankWithPriors
import org.apache.uima.jcas.JCas
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._


class SalIEPageRank(val jCas: JCas, val config: SalIEPageRankConfig) {

  private val logger = LoggerFactory.getLogger(classOf[SalIEPageRank])

  val salieGraph = new SalIEGraph(jCas)   // creates graph of open facts, no edge drawing yet
  val weightingFunction = createWeightedEdges(salieGraph, config) // drawns edges and precompute their weights
  val rankingPrior = createRankingPrior(salieGraph, config)       // computes prior probablities


  /**
    * Runs PageRank and assigns its scores as salience to each open fact.
    */
  def computePageRank() : Unit = {

    //
    // Computes PageRank scores

    val pageRankWithPriors = new PageRankWithPriors[Int, Long](
      salieGraph.getDirectedSparseGraph,
      weightingFunction, rankingPrior, config.alpha
    )

    Range(0, config.iterations).foreach(iteration => pageRankWithPriors.step())


    //
    // Assigns the PageRank scores as salience score to the corresponding open facts.

    salieGraph.getDirectedSparseGraph.getVertices.asScala
      .foreach(nodeID => {

        val salieOpenFact = salieGraph.getSalIEOpenFact(nodeID)
        salieOpenFact.setSalience(pageRankWithPriors.getVertexScore(nodeID).toFloat)

      })
  }


  /**
    * Computes the prior probability for each node in the graph.
    *
    * @param salieGraph
    * @param config
    * @return
    */
  private def createRankingPrior(salieGraph: SalIEGraph, config: SalIEPageRankConfig) : RankingPriorFunction = {
    val rankingPrior = RankingPriorFactory(config.rankingPrior)
    rankingPrior.weightNodes(salieGraph)
    rankingPrior
  }


  /**
    * Connects nodes and weights their edges.
    *
    * @param salieGraph
    * @param config
    * @return
    */
  private def createWeightedEdges(salieGraph: SalIEGraph, config: SalIEPageRankConfig) : WeightingFunction = {
    // Drawn edges
    val edgeCreation = GraphStructureFactory(config.graphStructure)
    edgeCreation.createEdges(salieGraph)

    // Weight edges
    val weighting = WeightingFactory(config.weighting, config.weightingModel)
    weighting.weightEdges(salieGraph)

    weighting
  }

}
