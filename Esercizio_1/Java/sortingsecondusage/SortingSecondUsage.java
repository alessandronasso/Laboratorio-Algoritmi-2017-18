package sortingsecondusage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import sorting.Sorting;
import java.util.ArrayList;

public class SortingSecondUsage {
  
  private static final Charset ENCODING = StandardCharsets.UTF_8;
  
  private static void loadArray(String filepath, ArrayList<Long> array) throws IOException {
    System.out.println("\nLoading data from file...\n");
    Path inputFilePath = Paths.get(filepath);
    try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)){
      String line = null;
      while((line = fileInputReader.readLine()) != null){  
        String[] lineElements = line.split("\n");       
        Long value = new Long(Long.parseLong(lineElements[0]));
        array.add(value);
      }
    } 
    System.out.println("\nData loaded\n");
  }
  
  private static void runUsage(String filepath1, String filepath2) throws IOException {
    ArrayList<Long> arrayA = new ArrayList<Long>();
    ArrayList<Long> arrayN = new ArrayList<Long>();

    //Integers.txt
    long startTime = System.currentTimeMillis();
    loadArray(filepath1, arrayA);
    long endTime = System.currentTimeMillis();
    System.out.println("Data (Integers.txt) loaded in " + ((endTime - startTime)/1000) + " seconds\n");

    //Sums.txt
    startTime = System.currentTimeMillis();
    loadArray(filepath2, arrayN);
    endTime = System.currentTimeMillis();
    System.out.println("Data (Sums.txt) loaded in:  " + ((endTime - startTime)/1000) + " seconds\n");

    //Sorting.mergeSort(arrayA, new LongComparator()); 

    for (int index=0; index<arrayN.size(); index++) {
      System.out.print("Number "+Long.valueOf(arrayN.get(index))+" has found correspondence: ");
      System.out.println(Sum.searchSum(arrayA, Long.valueOf(arrayN.get(index)))+"\n");
    }
    
    System.out.println("--------");
  }
  
  public static void main(String[] args) throws IOException, Exception {
    if(args.length == 2)
      runUsage(args[0], args[1]);
    else throw new Exception("ERROR! Missing input!");
  }
    
}
