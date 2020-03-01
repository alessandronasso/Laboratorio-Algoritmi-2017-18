package sorting;

import java.util.Comparator;
import java.util.ArrayList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SortingTests{

  class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }
  }

  private ArrayList<Integer> array;

  @Before
  public void setUp() {
    array = new ArrayList<Integer>();
  }
  
  @Test
  public void testInsertionSort_zeroEl() {
    Integer[] arrayExp = new Integer[0];
    Sorting.insertionSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testInsertionSort_oneEl() {
    array.add(5);
    Integer[] arrayExp = {5};
    Sorting.insertionSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testInsertionSort_twoEl() {
    array.add(5);
    array.add(1);
    Integer[] arrayExp = {1, 5};
    Sorting.insertionSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testInsertionSort_threeEl() {
    Integer[] arrayExp = {3, 5, 12};
    Integer[] array2 = new Integer[3];
    array.add(12);
    array.add(3);
    array.add(5);
    Sorting.insertionSort(array, (new IntegerComparator()));
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testInsertionSort_threeEqualEl() {
    Integer[] arrayExp = {3, 3, 3};
    Integer[] array2 = new Integer[3];
    array.add(3);
    array.add(3);
    array.add(3);
    Sorting.insertionSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testInsertionSort_Descending() {
    Integer[] arrayExp={12, 5, 3};
    Integer[] array2= new Integer[3];
    array.add(3);
    array.add(12);
    array.add(5);
    Sorting.insertionSort(array, Comparator.reverseOrder());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testMergeSort_zeroEl() {
    Integer[] arrayExp = new Integer[0];
    Sorting.mergeSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testMergeSort_oneEl() {
    Integer[] arrayExp = {5};
    array.add(5);
    Sorting.mergeSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testMergeSort_twoEl() {
    Integer[] arrayExp = {1, 5};
    array.add(5);
    array.add(1);
    Sorting.mergeSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testMergeSort_threeEl() {
    Integer[] arrayExp = {3, 5, 12};
    array.add(12);
    array.add(3);
    array.add(5);
    Sorting.mergeSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  }

  @Test
  public void testMergeSort_threeEqualEl() {
    Integer[] arrayExp = {3, 3, 3};
    array.add(3);
    array.add(3);
    array.add(3);
    Sorting.mergeSort(array, new IntegerComparator());
    assertArrayEquals(arrayExp, array.toArray());
  } 
}








