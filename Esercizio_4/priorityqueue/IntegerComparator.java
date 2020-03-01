package priorityqueue;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>{
  @Override
  public int compare(Integer l1, Integer l2) {
    int result = l1.compareTo(l2);
    if(result != 0)
      return result;
    return -1;
   }
}
