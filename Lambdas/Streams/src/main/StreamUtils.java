package main;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import test.Employee;
import test.Employee.EmployeeTestData;
import test.TestData;

public class StreamUtils {

  @FunctionalInterface
  public interface StringFunction {
    String applyFunc(String s);
  }
  
  @FunctionalInterface
  public interface PredicateFunction {
   <T> boolean applyFunc(Predicate<T> p);
  }
  
  public static <T> void prntList(List<T> l) {
    l.stream().forEach(e -> System.out.println(e));
  }
  
  public static <T> boolean testFunc(T arg, Predicate<T> p){
    return p.test(arg);
  }
  
  public static Predicate<Object> notNull = o -> o != null;
  
  public static Predicate<Employee> salLessThan31K = 
      e -> {
        System.out.println("Find emps with salary less than 31k");
        return (e.getSalary() < 31000);
      };
   
  public static Predicate<Employee> empNameIsLinda = 
      e -> {
        System.out.println("Find emps called Linda");
        return (e.getName().equals("Linda"));
      };
       
  public static Function<Integer, Employee> findEmpWithId = 
      i -> {
        System.out.println("Finding employee with id: " + i);
        return EmployeeTestData.getEmpMap().get(i);
      };
      
  public static String transformString(String s, StringFunction f) {
    return f.applyFunc(s);
  }
  
  public static Employee empById(int id) {
    System.out.println("Finding employee with id: " + id);
    return EmployeeTestData.getEmpMap().get(id);
  }
  
  public static void prntStreamAsList(Stream<?> stream, String message) {
    System.out.printf("%s -> %s.%n", message, stream.collect(Collectors.toList()));
  }
  
  public static Consumer<Employee> giveRaiseAndPrint = 
    e -> {  System.out.printf("%s earned %.2f before raise.\n", e.getName(), e.getSalary());
            EmployeeTestData.giveRaise(e, 10);
            System.out.printf("%s earned %.2f after raise.\n", e.getName(), e.getSalary());
    };

  public static Function<Integer, Predicate<Employee>> empsWithIDHigherThanArg =
      arg -> (e -> e.getId() > arg);
      
  public static Function<Double, Predicate<Employee>> empsWithSalaryHigherThanArg = 
      arg -> (e -> e.getSalary() > arg); 
      
  public static double operateOnDoublesSerialy(double [] d, DoubleUnaryOperator op) {
    return DoubleStream.of(TestData.getRndNums()).map(op).sum();
  }
  
  public static double operateOnDoublesInParallel(double [] d, DoubleUnaryOperator op) {
    return DoubleStream.of(TestData.getRndNums()).parallel().map(op).sum();
  }
  
  public static List<Double> generateListOfDoubles(int size){
    return Stream.generate(Math::random).limit(size).collect(Collectors.toList());
  }
  
  public static double averageOfList(List<Double> list) {
    return (list.stream().mapToDouble(n -> n.doubleValue()).average().orElse(Double.NaN));
  }
  
  public static <T> List<T> ignorePartOfList(long ignoreUpTo, List<T> list){
    return list.stream().skip(ignoreUpTo).collect(Collectors.toList());
  }
  
  public static List<Double> ignoreNumsLessThan(Double arg, List<Double> list){
    return list.stream().filter(n -> n >= 0.5).collect(Collectors.toList());
  }
  
  public static List<Double> doubleNumsInList(List<Double> list){
    return list.stream().map(n -> n * 2).collect(Collectors.toList());
  }
}
