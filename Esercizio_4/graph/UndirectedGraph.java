package graph;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import java.util.ArrayList;
import priorityqueue.*;

/**
 * Class that ovverrides addEdge() and getWeight() methods of the superclass 
 * Graph in order to represent an undirected graph. The class provides
 * also the Prim's algorithm.
 * This class uses the priorityqeueue package for the Prim's algorithm.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class UndirectedGraph <E> extends Graph <E>{

  /**
   * Add an undirected edge in the graph between the vertex 
   * whose element is elem1 and the vertex whose element is elem2.
   * @param  elem1 the element of the first vertex
   * @param  elem2 the element of the second vertex
   * @param  weight weight of the edge
   * @throws graph.GraphException if elem1 or elem2 are null
   * @throws graph.GraphException if elem1 or elem2 are not in the graph
   * @throws graph.GraphException if elem1 and elem2 are the same element
   * @throws graph.GraphException if the edge already exist in the graph
   */
  @Override
  public void addEdge(E elem1, E elem2, Double weight) throws GraphException {
    if (elem1.equals(elem2)) throw new GraphException("No loops allowed in undirected graph");
    if (edgeExists(elem1, elem2)) throw new GraphException("Edge already in the graph");
    if (weight == null) weighed = false;

    Edge<E> edge = new Edge<>(elem1, weight);
    Edge<E> edge2 = new Edge<>(elem2, weight);
    addAdj(elem1, edge2);
    addAdj(elem2, edge);
    numEdges++;
  }

  /**
   * Compute a minimum spanning tree (or forest) of an undirected edge-weighted graph.
   * @param startElem the element of the vertex from which start the algorithm
   * @return a Minimum Spanning Tree for the graph
   */
  public HashMap<E, Pair<E, Double>> prim(E startElem) throws PriorityQueueException, GraphException {
    PriorityQueue<E, Double> queue = new PriorityQueue<>(new DoubleComparator());
    HashMap<E, Pair<E, Double>> hp = new HashMap <>();

    for (E v : adjList.keySet()) {
      hp.put(v, new Pair<E, Double>(null, Double.MAX_VALUE));
    }

    hp.put(startElem, new Pair<E, Double>(null, 0.0));

    for (E elem : hp.keySet()) {
      queue.insert(elem, hp.get(elem).getValue());
    }
    while (!queue.isEmpty()) {
      E uElem = queue.extractMax();
      for (E v : getAdj(uElem)) {
        if (queue.contains(uElem)) { 
          if (hp.get(v).getValue()>getEdgeWeight(uElem, v)) {
            hp.put(v, new Pair<E, Double>(uElem, getEdgeWeight(uElem, v)));
            queue.updatePriority(v, getEdgeWeight(uElem, v));
          }
        }
      }
    }
    return hp;
  }

  /**
   * Returns the weight of the graph.
   * @return the weight of the graph
   */
  @Override
  public double getWeight() throws GraphException {
    return super.getWeight()/2;
  }
}