package sortingsecondusage;

import java.util.ArrayList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SumTests{

  @Test
  public void testSearchSum_True() {
    ArrayList<Long> array = new ArrayList<Long>();
    array.add((long)3);
    array.add((long)6);
    array.add((long)2);
    assertTrue(Sum.searchSum(array, (long)8)); //6+2
  }

  @Test
  public void testSearchSum_False() {
    ArrayList<Long> array = new ArrayList<Long>();
    array.add((long)3);
    array.add((long)6);
    array.add((long)2);
    assertFalse(Sum.searchSum(array, (long)2));
  }

  public void testSearchSum_TwoElTrue() {
    ArrayList<Long> array = new ArrayList<Long>();
    array.add((long)3);
    array.add((long)2);
    assertTrue(Sum.searchSum(array, (long)5));
  }

  @Test
  public void testSearchSum_OneEl() {
    ArrayList<Long> array = new ArrayList<Long>();
    array.add((long)2);
    assertFalse(Sum.searchSum(array, (long)2));
  }

  @Test
  public void testSearchSum_Empty() {
    ArrayList<Long> array = new ArrayList<Long>();
    assertFalse(Sum.searchSum(array, (long)3));
  }
}








