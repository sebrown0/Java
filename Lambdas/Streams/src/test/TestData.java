package test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestData {

  private Double[] testNums = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
  private String[] testStrings = { "Hello", "hi", "one", "Paul", "hola" };
  private static Integer[] ids = { 1, 2, 3, 4, 5 };
  private static List<String> listString = Arrays.asList("one", "two", "three", "four");
  private static final int rndNumSize = 1_000_000;
  private static double[] rndNums = new double[rndNumSize];

  static {
    for (int i = 0; i < rndNumSize; i++)
      rndNums[i] = new Random().nextDouble() * 100;
  }

  public Double[] getTestNums() {
    return testNums;
  }

  public String[] getTestStrings() {
    return testStrings;
  }

  public static Integer[] getIds() {
    return ids;
  }

  public static List<String> getListString() {
    return listString;
  }

  public static double[] getRndNums() {
    return rndNums;
  }

}
