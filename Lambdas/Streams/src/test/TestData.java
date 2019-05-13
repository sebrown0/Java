package test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestData {

  private static Double[] testNums = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
  private String[] testStrings = { "Hello", "hi", "one", "Paul", "hola" };
  private static Integer[] ids = { 1, 2, 3, 4, 5 };
  private static List<String> listString = Arrays.asList("one", "two", "three", "four");
  private static final int rndNumSize = 1_000_000;
  private static double[] rndNums = new double[rndNumSize];

  static {
    for (int i = 0; i < rndNumSize; i++)
      rndNums[i] = new Random().nextDouble() * 100;
  }

  public static Double[] getTestNums() {
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

  public static class FibonacciMaker implements Supplier<Long> {
    private long previous = 0;
    private long current = 1;

    @Override
    public Long get() {
      long next = current + previous;
      previous = current;
      current = next;
      return previous;
    }

    public static Stream<Long> fibonacciMaker(int numberOfFibs) {
      return Stream.generate(new FibonacciMaker()).limit(numberOfFibs);
    }
  }

  public static class PrimeNumber {
    private static Map<Integer, Integer> primeCount = new HashMap<>();
    
    public static BigInteger nextPrime(BigInteger start) {
      start = checkIfEven(start);
      return (start.isProbablePrime(10)) ? start : nextPrime(start);
    }

    private static BigInteger checkIfEven(BigInteger bigInt) {
      return (isEven(bigInt)) ? bigInt.add(one()) : bigInt.add(two());
    }

    public static BigInteger one() {
      return BigInteger.valueOf(1);
    }

    public static BigInteger two() {
      return BigInteger.valueOf(2);
    }

    public static boolean isEven(BigInteger bi) {
      return !bi.testBit(0);
    }

    public static BigInteger randomNum(int numDigits) {
      long max = (long) (Math.pow(10, numDigits) - 1);
      long min = (long) (Math.pow(10, numDigits - 1));
      return BigInteger.valueOf(min + (long) (Math.random() * ((max - min) + 1)));
    }

    public static BigInteger randomNum(int min, int max) {
      return BigInteger.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
    }

    public static BigInteger findRandomPrime(int numDigits) {
      numDigits = (numDigits < 1) ? 1 : numDigits;
      return nextPrime(randomNum(numDigits));
    }

    public static Stream<BigInteger> streamOfPrimes(int numDigits, int numPrimes) {
      return Stream.iterate(findRandomPrime(numDigits), PrimeNumber::nextPrime).limit(numPrimes);
    }

    public static int countPrimes(int upperBound) {
      if (upperBound <= 2) 
        return(1);
      
      if (isPrime(upperBound)) 
        return(1 + countPrimes(upperBound - 1));
      else 
        return(countPrimes(upperBound - 1));
    }
    
    public static int countPrimesMemoized(int upperBound) {
      return 
          primeCount.computeIfAbsent(upperBound, bound -> {
            if (bound <= 2) 
              return(1);
            
            if (isPrime(bound)) 
              return(1 + countPrimes(bound - 1));
            else 
              return(countPrimes(bound - 1));
            });
    }
   
    public static boolean isPrime(long possiblePrime) {
      return BigInteger.valueOf(possiblePrime).isProbablePrime(1);
    }
  }
}
