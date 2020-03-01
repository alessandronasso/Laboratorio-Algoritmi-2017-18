package graph;

import java.util.ArrayList;


/**
 * A vertex in a graph, with methods to access the properties.
 * Each vertex have represents an element of the graph. A vertex
 * can have a parent and a key (for the Prim's algorithm). 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class Vertex <E> {

	private E elem;
  private ArrayList<Edge<E>> adjList = new ArrayList<>();
  private double key;
  private E parent;

	public Vertex(E elem) {
		this.elem = elem;
	}

  /**
   * Returns the value of the vertex.
   * @return the value of this vertex
   */
	public E getElem() {
		return this.elem;
	}
  
  /**
   * Returns the key of the vertex.
   * @return the key of this vertex
   */
  public double getKey() {
    return this.key;
  }

  /**
   * Sets the key of the vertex.
   * @param key the new key of this vertex
   */
  public void setKey(double key) {
    this.key = key;
  }

  /**
   * Returns the list of edges that start from this vertex (adjacent list).
   * @return the list of edges that start from this vertex
   */
  public ArrayList<Edge<E>> getAdjList() {
    return new ArrayList<Edge<E>>(this.adjList);
  }

  /**
   * Add an edge to the adjacent list of the vertex.
   * @param edge the edge to add.
   */
  public void addAdjList(Edge<E> edge) {
    this.adjList.add(edge);
  }

  /**
   * Returns the parent of the vertex.
   * @return the parent of this vertex
   */
  public E getParent() {
    return this.parent;
  }

  /**
   * Sets the parent of the vertex.
   * @param parent the new parent of this vertex
   */
  public void setParent(E parent) {
    this.parent = parent;
  }

  /**
   * Returns a string representation of the vertex.
   * @return a string representation of the vertex
   */
  public String toString() {
    return elem + "";
  }
}