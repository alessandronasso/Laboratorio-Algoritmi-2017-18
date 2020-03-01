package priorityqueue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class QueueTests {

  private PriorityQueue<Integer, Integer> queue;

  @Before
  public void setUp() {
    queue = new PriorityQueue<>(new IntegerComparator());
  }

  @Test
  public void testPriorityQueue_Size() throws Exception {
    queue.insert(10, 100);
    assertEquals(1, queue.getSize());
  }
  
  @Test
  public void testPriorityQueue_isEmpty() throws Exception{
    assertTrue(queue.isEmpty());
  }

  @Test
  public void testPriorityQueue_Insert() throws Exception {
    Integer[] arrayExp = {16, 9, 14};
    queue.insert(14, 100);
    queue.insert(9, 60);
    queue.insert(16, 110);
    assertArrayEquals(arrayExp, queue.getArray().toArray());
  }

  @Test(expected = PriorityQueueException.class)
  public void testPriorityQueue_InsertElementExist() throws Exception {
    queue.insert(14, 100);
    queue.insert(14, 100);
  }

  @Test(expected = PriorityQueueException.class)
  public void testPriorityQueue_ExtractMaxEmpty() throws Exception{
    queue.extractMax();
  }

  @Test
  public void testPriorityQueue_ExtractMax() throws Exception {
    Integer[] arrayExp = {9, 14};
    queue.insert(118, 110);
    queue.insert(9, 20);
    queue.insert(14, 10);
    queue.extractMax();
    assertArrayEquals(arrayExp, queue.getArray().toArray());
  }

  @Test
  public void testPriorityQueue_getRoot() throws Exception {
    queue.insert(16, 110); //Root
    queue.insert(9, 20);
    queue.insert(14, 11);
    assertEquals((Integer)16, queue.extractMax());
  }

  @Test
  public void testPriorityQueue_UpdatePriorityUp() throws Exception {
    Integer[] arrayExp = {9, 118};
    queue.insert(118, 10);
    queue.insert(9, 5);
    queue.updatePriority(9, 15);
    assertArrayEquals(arrayExp, queue.getArray().toArray());
  }

  @Test
  public void testPriorityQueue_UpdatePriorityDown() throws Exception {
    Integer[] arrayExp = {12, 9};
    queue.insert(12, 5);
    queue.insert(9, 10);
    queue.updatePriority(9, 2);
    assertArrayEquals(arrayExp, queue.getArray().toArray());
  }

  @Test(expected = PriorityQueueException.class)
  public void testPriorityQueue_UpdatePriorityNoElement() throws Exception{
    queue.insert(118, 10);
    queue.insert(9, 5);
    queue.updatePriority(5, 3);
  }
}
