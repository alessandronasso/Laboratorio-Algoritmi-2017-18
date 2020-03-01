package editdistance;

import java.lang.Math;

/**
 * Computes the string edit distance using a recurisve method.
 * If the strings are identical, then the distance is 0. The more that the words 
 * have in common, the lower the distance value. The distance value is based on 
 * how many operations it takes to get from one string to the other. 
 * Possible operations are swapping characters, adding a character, deleting a character.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */

public class EditDistance {

	/**
   * This method compute the edit distance between two strings s1 and s2.
   * The complexity is O(n*m) where n=s1.length() and m=2.length()
   * @param s1 string to compare with s2
   * @param s2 string to compare with s1
   * @return the edit distance between s1 and s2
   */
	public static int distance(String s1, String s2) {
    return distance(s1, s2, 0, 0);
  }

  /**
   * This method compute the edit distance between two strings s1 and s2.
   * The complexity is O(n*m) where n=s1.length() and m=2.length().
   * @param s1 string to compare with s2
   * @param s2 string to compare with s1
   * @param i index of the string s1
   * @param j index of the string s2
   * @return the edit distance between s1 and s2
   */
  private static int distance(String s1, String s2, int i, int j) {
    int no_op;
    if (j == s2.length()) return s1.length() - i;
    else if (i == s1.length()) return s2.length() - j;

    if (Character.toLowerCase(s1.charAt(i)) == Character.toLowerCase(s2.charAt(j)))
      no_op=distance(s1, s2, i + 1, j + 1);
    else no_op=-1;
    int del = distance(s1, s2, i, j + 1) + 1;
    int ins = distance(s1, s2, i + 1, j) + 1;
    if (no_op == -1) return Math.min(del, ins);
    else return Math.min(del, Math.min(ins, no_op));
  }
}