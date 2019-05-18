/**
 * 
 */
package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Steve Brown
 *
 */
public interface StreamAnalyser <T>{
  T analyseStream(Stream<String> stream);
  
  public static <T> T analyseFile(String fileName, StreamAnalyser<T> analyiser) {
    try(Stream<String> strings = Files.lines(Paths.get(fileName))){
      return analyiser.analyseStream(strings);
    }catch (IOException e) {
      return null;
    }
  }
  
  @SafeVarargs
  public static List<String> analyseFile(String fileName, Predicate<String>... tests) {
    try(Stream<String> strings = Files.lines(Paths.get(fileName))){
      return strings.filter(FileUtils.combinePredicates(tests)).collect(Collectors.toList());
    }catch (IOException e) {
      return null;
    }
  }

  public static String getFirstWordOfLen(String fileName, int len) {
    return StreamAnalyser.analyseFile(fileName, 
        s -> s.filter(w -> w.length() == len).findFirst().orElse("None"));
  }
  
  public static List<String> getAllWordsOfLen(String fileName, int len) {
    return StreamAnalyser.analyseFile(fileName, 
        s -> s.filter(w -> w.length() == len).collect(Collectors.toList()));
  }
  
  @SafeVarargs
  public static <T> List<String> getAllWordsForPredicates(String fileName, Predicate<String>... tests) {
      return StreamAnalyser.analyseFile(fileName, tests);
  }
}
