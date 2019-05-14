package test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import file_utils.FileUtils;
/**
 * 
 * @author Steve Brown
 * 
 * Exercises from Marty Hall File I/O - Part 1.
 *
 */
class Exercises {

  @Test
  void t1() {
    Stream<String> s = FileUtils.getFileStream(TestData.getInputFile());
    String r = s.filter(w -> w.length() == 8)
        .filter(w -> w.toLowerCase().contains("a"))
        .filter(w -> w.toLowerCase().contains("b"))
        .filter(w -> w.toLowerCase().contains("c"))
        .findAny().orElse("");
    System.out.println(r);
  }
  
  @Test
  void t2() {
    Stream<String> s = FileUtils.getFileStream(TestData.getInputFile());
    Comparator<String> comp = (s1,s2) -> (s1.length() - s2.length());
    
    String r = s
        .map(String::toLowerCase)
        .filter(w -> w.contains("a"))
        .filter(w -> w.contains("e"))
        .max(comp)
        .orElse("Error");
    System.out.println(r);
  }
  
  @Test
  void t3() {
    Stream<String> s = FileUtils.getFileStream(TestData.getInputFile());
        
    String r = s
        .map(String::toLowerCase)
        .filter(w -> w.contains("q"))
        .min(Comparator.comparing(String::length))
        .orElse("Error");
    System.out.println(r);
  }
  
  @Test 
  void t4(){
    List<String> results = new ArrayList<>();
    try {
      results = Files.lines(Paths.get(TestData.getInputFile()))
                  .map(String::toUpperCase)
                  .filter(w -> w.contains("WOW") || w.contains("COOL"))
                  .map(w -> w + "!!!!")
                  .collect(Collectors.toList());
      Files.write(Paths.get("twitter.txt"), results, Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  @Test
  void t5() {
    long res = 0;
    try {
      res = Files.walk(Paths.get(".")).count();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(res);
  }
  
  @Test
  void t6() {
    List<String> results = new ArrayList<>();
    Supplier<Double> f = () -> Math.random() * 1_00;
    try {
      results = Stream.generate(f).map(d -> String.format("%.3f", d)).limit(100).collect(Collectors.toList());
      Files.write(Paths.get("doubles.txt"), results, Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
