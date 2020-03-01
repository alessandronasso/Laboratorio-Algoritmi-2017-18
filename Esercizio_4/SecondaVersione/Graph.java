package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents a graph as a collection of nodes connected by edges. A graph does 
 * not need to contain any nodes or edges however if there is at least one edge 
 * then there must be at least one node. There can, however, be one or more 
 * nodes with no edges present. This class permits any of the following 
 * common variations of graphs: directed and undirected edges, edges with attributes,
 * loops on the same vertex (only in directed graphs).
 * The graph is rapresented through adjacency lists implemented through ArrayList.
 * The graph doesnt accept vertices with the same element (e.g. addVertex(3);
 * addVertex(3); will result in GraphException).
 * A graph can be weighed or not weighed. It is weighed only if all of its edges
 * are weighed.
 *
 * The following features are supported: add vertices, add edges, num of vertices
 * and num of edges, Prim's algorithm (only for undirected graphs). 
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the element type of the vertex
 */

public abstract class Graph <E> {

  HashMap <E, Vertex<E>>  vertices = new HashMap<>();

  public int numVertices;
  public int numEdges;
  public boolean weighed;

  public Graph() {
    numVertices = 0;
    numEdges = 0;
    weighed = true;
  }

  public abstract void addEdge(E v1, E v2, Double weight) throws GraphException;

  /**
   * Returns true if this graph contains a vertex with the specified element.
   * @param elem element whose presence in this graph is to be tested
   * @return true if this graph contains a vertex with the specified element, false otherwise
   */
  public boolean vertexExists(E elem) {
    if (elem == null) return false;
    return vertices.containsKey(elem);
  }

  /**
   * Returns the vertex of elem.
   * @param elem element whose vertex want to be returned
   * @return the vertex whose has elem as element
   * @throws graph.GraphException if the vertex doesn't exist or elem is invalid
   */
  public Vertex<E> getVertex(E elem) throws GraphException{
    if(!vertexExists(elem)) throw new GraphException("Vertex doesn't exists or elem is invalid");
    return vertices.get(elem);
  }

  /**
   * Add the specified vertex to the graph.
   * @param elem the element of the vertex to add
   * @throws graph.GraphException if the element is already in the graph or elem is invalid
   */
  public void addVertex(E elem) throws GraphException {
    if (vertexExists(elem)) throw new GraphException("Vertex already in the graph or elem is invalid");
    Vertex<E> tmp = new Vertex<>(elem);
    vertices.put(elem, tmp);
    numVertices++;
  }

  /**
   * Returns true if the edge between v1 and v2 exists.
   * @param elem1 the element of the first vertex
   * @param elem2 the element of the first vertex
   * @return true if the edge between v1 and v2 exists, false otherwise
   */
  public boolean edgeExists(E elem1, E elem2) throws GraphException {
    if (!vertexExists(elem1) || !vertexExists(elem2)) return false;
    Vertex<E> v1 = getVertex(elem1);
    Vertex<E> v2 = getVertex(elem2);
    ArrayList<Edge<E>> edgeList = v1.getAdjList(); 
    for (int i = 0; i < edgeList.size(); i++) {
      Edge<E> edge = edgeList.get(i);
      if (edge.getVertex2().equals(v2)) return true;
    }
    return false;
  }

  /**
   * Returns the edge between two vertices.
   * This method always returns immediately, whether or not the 
   * edge exists.
   * @param elem1 the element of the first vertex
   * @param elem2 the element of the second vertex
   * @return the edge between v1 and v2 if it exists, null otherwise
   * @throws graph.GraphException if the vertex doesn't exist or elem is invalid
   */
  public Edge<E> getEdge(E elem1, E elem2) throws GraphException {
    if (!vertexExists(elem1) || !vertexExists(elem2)) 
      throw new GraphException("Vertex doesn't exists or elem is invalid");
    Vertex<E> v1 = getVertex(elem1);
    Vertex<E> v2 = getVertex(elem2);
    ArrayList<Edge<E>> edgeList = v1.getAdjList(); 
    for (int i = 0; i < edgeList.size(); i++) {
      Edge<E> edge = edgeList.get(i);
      if (edge.getVertex2().equals(v2)) return edge;
    }
    return null;
  }

  /**
   * Returns the adjacents vertices of a specified vertex. 
   * @param elem the element of the vertex whose adjacents vertices are to be returned
   * @return the adjacents vertices of the specified vertex
   * @throws graph.GraphException if the vertex doesn't exist or elem is invalid
   */
  public ArrayList<Vertex<E>> getAdj(E elem) throws GraphException {
    if(!vertexExists(elem)) throw new GraphException("Vertex doesn't exists or elem is invalid");
    Vertex<E> vertex = getVertex(elem);
    ArrayList<Edge<E>> vertexEdges = vertex.getAdjList();
    ArrayList<Vertex<E>> verticesList = new ArrayList<>();
    for (Edge<E> edge : vertexEdges)
      verticesList.add(edge.getVertex2());
    return verticesList;
  }

  /**
   * Add an edge in the adjacent list of the vertex.
   * @param vertex the vertex to whose list add the edge
   * @param edge the edge to add 
   */
  public void addAdj(Vertex<E> vertex, Edge<E> edge) {
    vertex.addAdjList(edge);
  }
  
  /**
   * Returns the numbers of vertices in the graph.
   * @return the numbers of vertices in the graph
   */
  public int getNumVertices() {
    return numVertices;
  }

  /**
   * Returns the numbers of edges in the graph.
   * @return the numbers of edges in the graph
   */
  public int getNumEdges() {
    return numEdges;
  }

  /**
   * Returns the weight of the graph.
   * @return the weight of the graph
   * @throws graph.GraphException if the graph is not weighed
   */
  public double getWeight() throws GraphException {
    if (!weighed) throw new GraphException("Graph is not weighed");
    double weight = 0;
    for (Edge<E> edge : getAllEdges()) {
      weight += edge.getWeight();
    }
    return weight;
  }

  /**
   * Returns a list with all the edges in the graph.
   * @return a list with all the edges in the graph.
   */
  public ArrayList<Edge<E>> getAllEdges() {
    ArrayList<Edge<E>> edgesList = new ArrayList<>();
    for(Vertex<E> vertex : vertices.values()) {
      edgesList.addAll(vertex.getAdjList());
    }
    return edgesList;
  }

  /**
   * Returns the weight of the edge between elem1 and elem2.
   * @param elem1 the element of the first vertex
   * @param elem2 the element of the second vertex
   * @return the weight of the edge between vertices elem1 and elem2,
   *         null if the edge is not weighed
   */
  public Double getEdgeWeight(E elem1, E elem2) throws GraphException {
    return getEdge(elem1, elem2).getWeight();
  }
}