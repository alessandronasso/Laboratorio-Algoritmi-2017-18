package priorityqueue;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double>{
  @Override
  public int compare(Double l1, Double l2) {
    int result = l2.compareTo(l1);
    if(result != 0)
      return result;
    return -1;
   }
}
