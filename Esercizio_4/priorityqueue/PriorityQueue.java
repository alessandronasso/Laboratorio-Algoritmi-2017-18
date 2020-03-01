package priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * An unbounded priority queue based on a heap. 
 * The elements of the priority queue are ordered according by a Comparator 
 * at queue construction time. A priority queue does not permit null elements or
 * elements with null priority. The prioty queue does not permit elements with 
 * the same value also if the priority is different (e.g. insert(3, 5);
 * insert(3, 10) will result in PriorityQueueException).
 *
 * Priority queue represented as a balanced binary heap:  the two
 * children of queue[n] are queue[2*n+1] and queue[2*(n+1)]
 * 
 * buildHeap() run in linear time. (O(n))
 * heapify(), updatePriority(), insert() runs in O(log(n)) time.
 * getRoot(), extractMax(), swap(), contains(), getSize() runs in costant time (O(1)).
 * 
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of elements held in the priority queue
 * @param <T> the type of the priority of the elements in the priority queue
 */
public class PriorityQueue < E, T > {
  
  private ArrayList <Node<E, T>> queue;

  /**
   * The Comparator to order the elements.
   */
  private Comparator < T > c;

  /**
   * HashMap used to access in costant time to a specified element of the priority queue.
   */
  HashMap <E, Integer> hmap;

  /**
   * Creates a Priority Queue that orders its element according to 
   * the specified comparator.
   * @param c the comparator that will be used to order this priority queue.
   */
  public PriorityQueue(Comparator < T > c) {
    queue = new ArrayList<Node<E, T>>();
    this.c = c;
    hmap = new HashMap<>();
  }

  /**
   * Makes the ArrayList satisfy heap property starting from index till the end of array.
   * @param index the index of the current element
   */
  private void heapify(int index) {

    if (queue.isEmpty()) {
      return;
    }

    int largest = index;
    int leftIndex = getLeft(index);
    int rightIndex = getRight(index);

    if (leftIndex < getSize() && c.compare(queue.get(index).getPriority(), (queue.get(leftIndex).getPriority())) < 0)
      largest = leftIndex;

    if (rightIndex < getSize() && c.compare(queue.get(largest).getPriority(), queue.get(rightIndex).getPriority()) < 0)
      largest = rightIndex;

    if (largest != index) {
      swap(index, largest);
      heapify(largest);
    }
  }

  /**
   * Converts arrayList into a heap.
   */
  private void buildHeap() {
    for (int i = getSize() / 2 - 1; i >= 0; i--) {
      heapify(i);
    }
  }

  /**
   * Insert a new element into the heap satisfying the heap property.
   * @param elem the element to insert in the queue
   * @param priority the priority of the element to insert in the queue
   * @throws priorityqueue.PriorityQueueException if elem or priority is null
   * @throws priorityqueue.PriorityQueueException if the element is already in the queue
   */
  public void insert(E elem, T priority) throws PriorityQueueException {
    if (elem == null || priority == null) throw new PriorityQueueException("Value or priority cannot be null");
    if (contains(elem)) throw new PriorityQueueException("Duplicated elem not allowed");
    Node<E, T> tmp = new Node<>(elem, priority);
    queue.add(tmp);
    int i = getSize() - 1;
    hmap.put(elem, i);
    while (i > 0 && c.compare(tmp.getPriority(), queue.get(getParent(i)).getPriority()) > 0) {
      swap(i, getParent(i));
      i = getParent(i);
    }
  }

  /**
   * Update an element the queue with value and priority of newElem.
   * @param elem the element whose priority is to be changed
   * @param newElem the new priority to be set
   * @throws priorityqueue.PriorityQueueException if elem is not in the queue
   * @throws priorityqueue.PriorityQueueException if newElem is already in the queue
   */
  public void updatePriority(E elem, T priority) throws PriorityQueueException {
    if (priority == null) throw new PriorityQueueException("Priority cannot be null");
    if (!(contains(elem))) throw new PriorityQueueException("Element doesn't exist");
    int i = hmap.get(elem);
    if (c.compare(priority, queue.get(i).getPriority()) > 0) {
      queue.get(i).setPriority(priority);
      while (i > 0 && c.compare(queue.get(i).getPriority(), queue.get(getParent(i)).getPriority()) > 0) {
        swap(i, getParent(i));
        i = getParent(i);
      } 
    } else {
        queue.get(i).setPriority(priority);
        heapify(i);
    }
  }

  /**
   * Method to get the root of the queue without remove it.
   * @return the element with the highest priority
   * @throws priorityqueue.PriorityQueueException if the queue is empty
   */
  public E getRoot() throws PriorityQueueException {
    if (getSize() == 0) throw new PriorityQueueException("Cannot read from an empty queue");
    return queue.get(0).getValue();
  }

  /**
   * Extract the element with the highest priority in the queue.
   * @return the element with the highest priority
   * @throws priorityqueue.PriorityQueueException if the queue is empty
   */
  public E extractMax() throws PriorityQueueException {
    if (getSize() == 0) throw new PriorityQueueException("Cannot read from an empty queue");
    Node<E, T> max = queue.get(0);
    swap(0, getSize()-1);
    queue.remove(getSize()-1);
    hmap.remove(max.getValue());
    heapify(0);
    return max.getValue();
  }


  /**
   * Place the element in firstIndex position in secondIndex and vice versa.
   * @param firstIndex position of the first element
   * @param secondIndex position of the second element
   */
  private void swap(int firstIndex, int secondIndex) {
    Node<E, T> tmp = queue.get(firstIndex);
    hmap.put(queue.get(firstIndex).getValue(), secondIndex);
    hmap.put(queue.get(secondIndex).getValue(), firstIndex);
    queue.set(firstIndex, queue.get(secondIndex));
    queue.set(secondIndex, tmp);
  }

  /**
   * Generates an arrayList containing only the values of the elements in the heap.
   * @return arrayList with only the values of the elements in the heap
   */
  public ArrayList<E> getArray() {
    ArrayList < E > onlyValues = new ArrayList< E >();
    for (int i = 0; i < getSize(); i++) {
      E tmp = queue.get(i).getValue();
      onlyValues.add(tmp);
    }
    return onlyValues;
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   * @param key The key whose presence in this map is to be tested
   * @return true if this map contains a mapping for the specified key
   */
  public boolean contains(E key) {
    if (key == null) return false;
    return hmap.containsKey(key);
  }

  /**
   * Compute the size of the queue.
   * @return the size of the queue
   */
  public int getSize() {
    return queue.size();
  }
 
  /**
   * @return true if the queue has no elements, false otherwise.
   */
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  /**
   * Compute the parent of element.
   * @param i the index of the element
   * @return index of the parent element
   */
  private int getParent (int i) {
    return (i - 1) / 2;
  }

  /**
   * Compute the index of the right child.
   * @param i the index of the element
   * @return index of right child element
   */
  private int getRight(int i) {
    return (2 * i) + 2;
  }

  /**
   * Compute the index of the left child.
   * @param i the index of the element
   * @return index of left child element
   */
  private int getLeft(int i) {
    return (2 * i) + 1;
  }

  public String toString() {
    return queue.toString();
  }
}