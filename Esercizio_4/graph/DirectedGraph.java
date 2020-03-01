package graph;

/**
 * Class that ovverrides the addEdge method of the superclass Graph
 * in order to represent a directed graph.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class DirectedGraph <E> extends Graph <E> {

  /**
   * Add a directed edge in the graph between the vertex 
   * whose element is elem1 and the vertex whose element is elem2.
   * @param  elem1 the element of the first vertex
   * @param  elem2 the element of the second vertex
   * @param  weight weight of the edge
   * @throws graph.GraphException if elem1 or elem2 are null
   * @throws graph.GraphException if elem1 or elem2 are not in the graph
   * @throws graph.GraphException if the edge already exist in the graph
   */
  @Override
  public void addEdge(E elem1, E elem2, Double weight) throws GraphException {
    if (edgeExists(elem1, elem2)) throw new GraphException("Edge already in the graph");
    if (weight == null) weighed = false;

    Edge<E> edge = new Edge<>(getVertex(elem1), getVertex(elem2), weight);
    addAdj(getVertex(elem1), edge);
    numEdges++;
  }
}

