package priorityqueue;

/**
 * A generic node to store the couple <value, priority> 
 * in the Priority Queue.

 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> value type that the priority queue stores
 * @param <T> priority type that the priority queue uses
 */
public class Node < E, T > {

  private E value;
  private T priority;

  public Node(E value, T priority) {
    this.value = value;
    this.priority = priority;
  }

  /**
   * Sets the value of the node.
   * @param value the new value to set
   */
  public void setValue(E value) {
    this.value = value;
  }

  /**
   * Returns the values of the node.
   * @return the value of this node
   */
  public E getValue() {
    return value;
  }

  /**
   * Sets the priority of the node.
   * @param priority the new priority to set
   */
  public void setPriority(T priority) {
    this.priority = priority;
  }

  /**
   * Returns the priority of the node.
   * @return the priority of this node
   */
  public T getPriority() {
    return priority;
  }

  @Override
  public String toString() {
    return value+"";
  }

  /**  
   * Returns a hash code value for the node.
   * @return a hash code value for this node
   */
  @Override
  public int hashCode () {
    return 31 * value.hashCode() + priority.hashCode();
  }
}