package sorting;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class provides an implementation of insertion sort and merge sort 
 * algorithms. The array is sorted according to the order induced by the 
 * specified comparator. The array can contain generics objects but cannot 
 * be null. The insertion sort alorithm has O(n*n) time complexy while the 
 * merge sort has O(nlogn) time complexity.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */
public class Sorting {

  /**
   * Sort the ArrayList with Insertion Sort
   * @param array the array to sort
   * @param c object which specifies the sorting order
   */
  public static < T > void insertionSort(ArrayList < T > array, Comparator < T > c) {
    for (int i = 1; i < array.size(); i++) {
      //Inv. array[1...array.size()-1] is ordered
      T elem = array.get(i);
      int j = i - 1;
      for(; j >= 0 && c.compare(elem, array.get(j)) < 0; j--)
        //Inv. for each key € array[j+1...array.size()];
        //Inv. array[j] <= key
        array.set(j + 1, array.get(j));
      array.set(j + 1, elem);
    }
  }

  /**
   * Sorts the elements in array according to the order induced by the specified
   * comparator.
   * @param array the array to sort
   * @param c object which specifies the sorting order
   */
  public static < T > void mergeSort(ArrayList < T > array, Comparator < T > c) {
    int high = array.size()-1;
    sort(array, c, 0, high, new ArrayList < T > (high/2));
  }  

  /**
   * Sorts the specified range of elements according to the order induced by the 
   * specified comparator. All elements in the range must be mutually comparable 
   * by the specified comparator. 
   * @param array the array to sort
   * @param c object which specifies the sorting order
   * @param low the first index of the left part of array
   * @param high the first element of the right part of array
   * @param tmp array of support
   */
  protected static < T > void sort(ArrayList < T > array, Comparator < T > c, int low, int high, ArrayList < T > tmp) {
    /*Precondition: A is an array and p, q, and r are indices into the array such 
    *        that p <= q < r. The subarrays A[p.. q] and A[q +1.. r] are sorted
    */
    if (low < high) {
      int mid = low + (high - low) / 2;
      sort(array, c, low, mid, tmp);
      sort(array, c, mid + 1, high, tmp);
      merge(array, c, low, mid, high, tmp);
    }
    //Postcondition: The subarray A[p..r] is sorted
  } 

  /**
   * Transforms two consecutive sorted ranges into a single sorted range. 
   * The initial ranges are [p, mid) and [middle, q), and the resulting range is 
   * [p, q). Elements in the first input range will precede equal elements in the second.
   * @param array array whose elements are going to be merged
   * @param c object which specifies the sorting order
   * @param p the lowest index of the array
   * @param mid mid position of the array
   * @param q the index of the last element of the array
   * @param tmp array of support
   */
  protected static < T > void merge(ArrayList < T > array, Comparator < T > c, int p, int mid, int q, ArrayList < T > tmp) {
    tmp.clear();
    int i = p;
    int j = mid + 1;
    int k = 0;
    for (; i <= mid && j <= q; k++) {
      if (c.compare(array.get(i), array.get(j)) < 0)
        tmp.add(k, array.get(i++));
      else
        tmp.add(k, array.get(j++));
    }
    if (i <= mid && j > q) {
      while (i <= mid)
        tmp.add(k++, array.get(i++));
    } else {
      while (j <= q)
        tmp.add(k++, array.get(j++));
    }
    for (k = 0; k < tmp.size(); k++)
      array.set(k + p, tmp.get(k));
    
  }
}