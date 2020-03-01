package graph;

/**
 * A vertex in a graph, with methods to access the properties.
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class Vertex <E> {

	private E elem;

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
   * Returns a string representation of the vertex.
   * @return a string representation of the vertex
   */
  public String toString() {
    return elem + "";
  }
}