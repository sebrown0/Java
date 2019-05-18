package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 
 * @author Steve Brown
 *
 * File I/O with streams.
 */
public class FileUtils {
  
  @SafeVarargs
  public static <T> Predicate<T> combinePredicates(Predicate<T>... tests){
    Predicate<T> result = t -> true;
    for (Predicate<T> p : tests) 
      result = result.and(p);
    return result;
  }
  
  public static Stream<String> getFileStream(String file){
    Stream<String> fileStream = null;
    try {
      fileStream = Files.lines(Paths.get(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileStream;
  }
  
  public static <T> void closeStream(Stream<T> s) {
    s.close();
  }

  public static void printSpecifiedInFolder(String folder, Predicate<Path> test){
    try (Stream<Path> paths =  Files.list(Paths.get(folder))) {
      printPaths(paths, test);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void printAllInFolder(String folder){
    try (Stream<Path> paths =  Files.list(Paths.get(folder))) {
      printPaths(paths);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void printSpecifiedInTree(String folder, Predicate<Path> test){
    try (Stream<Path> paths =  Files.walk(Paths.get(folder))) {
      printPaths(paths, test);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }  
  
  public static void printSpecifiedInTree(String folder, BiPredicate<Path, BasicFileAttributes> test){
    try (Stream<Path> paths =  Files.find(Paths.get(folder), 10, test)) {
      printPaths(paths);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }  
  
  public static void printAllInTree(String folder){
    try (Stream<Path> paths =  Files.walk(Paths.get(folder))) {
      printPaths(paths);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private static void printPaths(Stream<Path> paths, Predicate<Path> test) {
    paths.filter(test).forEach(System.out::println);
  }
  
  private static void printPaths(Stream<Path> paths) {
    paths.forEach(System.out::println);
  }
}
