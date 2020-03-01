package editdistancedyn;

import java.lang.Math;
import java.util.HashMap;
import javafx.util.Pair;

/**
 * Computes the string edit distance thorugh memoization.
 * If the strings are identical, then the distance is 0. The more that the words 
 * have in common, the lower the distance value. The distance value is based on 
 * how many operations it takes to get from one string to the other. 
 * Possible operations are swapping characters, adding a character, deleting a 
 * character. Using the memorization algorithm is faster because no differences 
 * must be calculated on strings already analyzed. Complexity of put() and get() 
 * methods of Hashtable is O(1). The complexity is O(n*m) where n=s1.length() and m=2.length().
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */

public class EditDistanceDyn {
  
  /**
   * This method compute the edit distance between two strings s1 and s2.
   * @param s1 string to compare with s2
   * @param s2 string to compare with s1
   * @return the edit distance between s1 and s2
   * @see distanceDyn
   */
  public static int distance(String s1, String s2) {
		HashMap <Pair<String, String>, Integer> dictionary = new HashMap < > ();
		return distanceDyn(s1, s2, dictionary);
	}

  /**
   * This method compute the edit distance between two strings s1 and s2 using 
   * memoization. The complexity is O(n*m) where n=s1.length() and m=2.length().
   * @param s1 string to compare with s2
   * @param s2 string to compare with s1
   * @param dictionary hashtable used for memoization
   * @throws IllegalArgumentException if null strings are null
   * @return the edit distance between s1 and s2
   */
	protected static int distanceDyn(String s1, String s2, HashMap <Pair<String, String>, Integer> dictionary) {
		int no_op, min;
		if (dictionary.containsKey(new Pair<String, String>(s1, s2)))
			return dictionary.get(new Pair<String, String>(s1, s2));

		if (s1 == null || s2 == null) throw new IllegalArgumentException();

		if (s1.length() == 0) {
			dictionary.put(new Pair<String, String> (s1, s2), s2.length());
			return s2.length();
		}

		if (s2.length() == 0) {
			dictionary.put(new Pair<String, String> (s1, s2), s1.length());
			return s1.length();
		}

		if (s1.charAt(0)==s2.charAt(0)) no_op = distanceDyn(s1.substring(1), s2.substring(1), dictionary);
		else no_op = -1;

		int del = 1 + distanceDyn(s1, s2.substring(1), dictionary);
		int ins = 1 + distanceDyn(s1.substring(1), s2, dictionary);
		if (no_op == -1) {
			dictionary.put(new Pair<String, String> (s1, s2), Math.min(del, ins));
			return Math.min(del, ins);
		} else 
		min = Math.min(del, Math.min(ins, no_op));
		dictionary.put(new Pair<String, String> (s1, s2), min);
		return min;
	}
}
