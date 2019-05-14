package test;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Steve Brown
 *
 * Test data used with File I/O.
 */
public class TestData {
  private static final String inputFile = "word-list.txt";
  private static List<Integer> listIntegers = Arrays.asList(5,3,1,4);
  
  public static String getInputFile() {
    return inputFile;
  }

  public static List<Integer> getListIntegers() {
    return listIntegers;
  }
  
}
