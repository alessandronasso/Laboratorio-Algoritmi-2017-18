package sortingfirstusage;

import java.util.Comparator;

public class LongComparator implements Comparator<Long>{
  @Override
  public int compare(Long l1, Long l2) {
    int result = l1.compareTo(l2);
    if(result != 0)
      return result;
    return -1;
   }
}
