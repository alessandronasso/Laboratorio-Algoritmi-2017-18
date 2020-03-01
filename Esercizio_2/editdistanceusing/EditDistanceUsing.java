package editdistanceusing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import editdistancedyn.EditDistanceDyn;
import java.util.ArrayList;

public class EditDistanceUsing {
    
  private static int countElements(String filepath) {
    File file = new File(filepath);
    int count=0;
    try {
      Scanner sc = new Scanner (file);
        for (; sc.hasNext(); count++)
          sc.next();
        sc.close();
      } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return count;
  }

  private static String[] loadStrings(String filepath) throws IOException {
    System.out.println("Loading data from file "+filepath+"\n");
    String[] tmp=new String[countElements(filepath)];
    File file = new File(filepath);
    try {
      Scanner sc = new Scanner (file);
      for (int i=0; sc.hasNext(); i++) 
        tmp[i] = (sc.next()).replaceAll("\\W","");
      sc.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("Data loaded");
    return tmp;
  }
  
  private static void testWithComparisonFunction(String filepath1, String filepath2) throws IOException {
    String[] dictionary;
    String[] correct;

    dictionary=loadStrings(filepath1);
    correct=loadStrings(filepath2);

    long startTime = System.currentTimeMillis();
    int dist_min=Integer.MAX_VALUE;
    ArrayList<String> minStringDistance = new ArrayList<String>();

    for (String correctString : correct) {
      for (String dictionaryString : dictionary) {
        int tmp = EditDistanceDyn.distance(correctString, dictionaryString);
        if (tmp < dist_min) {
          dist_min = tmp;
          minStringDistance.clear();
          minStringDistance.add(dictionaryString);
        } else if(tmp == dist_min) minStringDistance.add(dictionaryString);
      }
      System.out.println("\n\nWord: '"+correctString+"' (minimum edit distance: "+dist_min+"): ");
      for(String edit: minStringDistance){
        System.out.print(edit+", ");
      }

      minStringDistance.clear();
      dist_min = Integer.MAX_VALUE;
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Ended in: " + ((endTime - startTime)/1000) + " seconds\n");
    System.out.println("\n-----------------");
  }
  
  public static void main(String[] args) throws IOException, Exception {
    if(args.length == 2)
      testWithComparisonFunction(args[0], args[1]);
    else throw new Exception("ERROR! Missing input!");
  }  
    
}
