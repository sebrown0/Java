package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import main.FileUtils;
import main.StreamAnalyser;
import main.StreamProcessor;

/**
 * 
 * @author Steve Brown
 *
 * Test cases for File I/O with streams.
 */
class TestFileUtils {

  @Test
  void filterLines1() {
    List<String> results = new ArrayList<>();
    try {
      results = Files.lines(Paths.get(TestData.getInputFile()))
                  .filter(w -> w.length() == 4)
                  .map(String::toUpperCase)
                  .filter(w -> w.equals("ABBE"))
                  .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("ABBE", results.get(0));
  }
  
  @Test
  void filterLines2() {
    Stream<String> s = FileUtils.getFileStream(TestData.getInputFile());
    List<String> results = s.filter(w -> w.length() == 3).collect(Collectors.toList());
    assertEquals("zoo", results.get(results.size() - 1));    
    FileUtils.closeStream(s);
  }
  
  @Test
  void printAllInPath() {
    FileUtils.printAllInFolder(".");
  }
  
  @Test
  void printSpecifiedInPath() {
    FileUtils.printSpecifiedInFolder(".", p -> p.toString().endsWith(".txt"));
  }

  @Test
  void printAllInTree() {
    FileUtils.printAllInTree(".");
  }
  
  @Test
  void printSpecifiedInTree() {
    FileUtils.printSpecifiedInTree(".", p -> p.toString().endsWith(".java"));
  }
  
  @Test
  void printSpecifiedWithAttributes1() {
    FileUtils.printSpecifiedInTree(".", (path, attr) -> path.toString().endsWith(".java"));
  }
  
  @Test
  void printSpecifiedWithAttributes2() {
    FileUtils.printSpecifiedInTree(".", (path, attr) -> attr.isDirectory());
  }
  
  @Test
  void fileProcessor() {
    StreamProcessor.printWordsOfLen(TestData.getInputFile(), 12,3);
  }

  @Test
  void fileAnalyser1() {
    System.out.println(StreamAnalyser.getFirstWordOfLen(TestData.getInputFile(), 8));
  }
  
  @Test
  void fileAnalyser2() {
    List<String> results = StreamAnalyser.getAllWordsOfLen(TestData.getInputFile(), 18);
    for(String s : results)
      System.out.println(s);
  }
  
  @Test
  void combinePredicates1() {
    Predicate<Integer> p1 = p -> p > 3;
    Predicate<Integer> p2 = p -> p > 4;
    
    Integer result = TestData.getListIntegers().stream()
        .filter(FileUtils.combinePredicates(p1,p2)).findFirst().orElse(0);
    assertEquals(5, (int)result);
  }

  @Test
  void combinePredicates2() {
    Predicate<String> p1 = p -> p.toLowerCase().contains("x");
    Predicate<String> p2 = p -> p.toLowerCase().contains("a");

    List<String> results = StreamAnalyser.getAllWordsForPredicates(
        TestData.getInputFile(), p1,p2);
    for(String s : results)
      System.out.println(s);
  }

}
