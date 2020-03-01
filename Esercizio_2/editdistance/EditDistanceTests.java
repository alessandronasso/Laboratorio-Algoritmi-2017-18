package editdistance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EditDistanceTests{
  
  @Test
  public void testZeroDistance() {
  	String s1 = "pioppo";
  	String s2 = "pioppo";
  	assertEquals(0, EditDistance.distance(s1, s2));
  }

  @Test
  public void testOneDistance() {
  	String s1 = "casa";
  	String s2 = "cassa";
  	assertEquals(1, EditDistance.distance(s1, s2));
  }

  @Test
  public void testTwoDistance() {
  	String s1 = "casa";
  	String s2 = "cara";
  	assertEquals(2, EditDistance.distance(s1, s2));
  }

  @Test
  public void testFourDistance() {
  	String s1 = "tassa";
  	String s2 = "passato";
  	assertEquals(4, EditDistance.distance(s1, s2));
  }

  @Test
  public void testFirstStringEmpty() {
    String s1 = "passato";
    String s2 = "";
    assertEquals(7, EditDistance.distance(s1, s2));
  }

  @Test
  public void testSecondStringEmpty() {
    String s1 = "";
    String s2 = "tassa";
    assertEquals(5, EditDistance.distance(s1, s2));
  }

  @Test
  public void testBothStringEmpty() {
    String s1 = "";
    String s2 = "";
    assertEquals(0, EditDistance.distance(s1, s2));
  }
}








