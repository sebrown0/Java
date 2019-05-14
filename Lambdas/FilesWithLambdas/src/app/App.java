package app;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) throws Exception{
    String inputFile = "word-list.txt";
    String outputFile = "results.txt";
    
    List<String> words = 
        Files.lines(Paths.get(inputFile))
          .filter(w -> w.length() == 4)
          .map(String::toUpperCase)
          .filter(w -> w.equals("ABBE"))
          .distinct()
          .sorted()
          .collect(Collectors.toList());
    Files.write(Paths.get(outputFile), words, Charset.defaultCharset());
  }

}
