/**
 * 
 */
package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Steve Brown
 *
 */
public interface StreamProcessor {
  void processStream(Stream<String> strings);
  
  public static void processFile(String fileName, StreamProcessor processor) {
    try(Stream<String> strings = Files.lines(Paths.get(fileName))){
      processor.processStream(strings);
    }catch (IOException e) {
      // TODO: handle exception
    }
  }
  
  public static void printWordsOfLen(String fileName, int... len) {
    for(int l : len) {
      StreamProcessor.processFile(fileName, 
          s -> s.filter(w -> w.length() == l).forEach(System.out::println));
    }
  }
}
