package sortingsecondusage;

import java.util.ArrayList;
import java.util.Comparator;
import sorting.Sorting;

/**
 * Provides a method which search if in an array there are two elements that
 * gives a determinated number as their sum. Works only with Long numbers.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */
public class Sum {
  /**
   * This function search if in 'array' there are two elements that give 'elem' as their sum
   * @param array the array to check
   * @param elem number to search as result of the sum of two elements of 'array'
   * @return true if in 'array' there are two elements whose sum is 'elem', false otherwise
   */
  public static boolean searchSum(ArrayList<Long> array, long elem) {
    Sorting.mergeSort(array, new LongComparator());
    int left=0, right=array.size()-1;
    
    while (left<right) {
      long n1=Long.valueOf(array.get(left));
      long n2=Long.valueOf(array.get(right));
      if ((n1+n2)==elem) return true;
      else if ((n1+n2)<elem) left++;
      else right--;
    }
    return false;
  }
}