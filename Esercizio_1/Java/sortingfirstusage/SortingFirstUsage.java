package sortingfirstusage;

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

public class SortingFirstUsage {
  
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
  
  private static void runUsage(String filepath) throws IOException {
    ArrayList<Long> array = new ArrayList<Long>();
    long startTime = System.currentTimeMillis();
    loadArray(filepath, array);
    long endTime = System.currentTimeMillis();
    System.out.println("Data loaded in: " + ((endTime - startTime)/1000) + " seconds\n");

    //Calculating the execution time of the algorithm 
    startTime = System.currentTimeMillis();
    Sorting.mergeSort(array, new LongComparator());
    endTime = System.currentTimeMillis();
    System.out.println("Execution time of merge sort: " + ((endTime - startTime)/1000) + " seconds\n");
    System.out.println("\n-----");
  }
  
  public static void main(String[] args) throws IOException, Exception {
    if(args.length < 1)
      throw new Exception("ERROR! Missing input!");
    
    runUsage(args[0]);
  } 
}
