package streamstuff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class ExceptionProblem {
//  public static Stream<String> getLines(String fn) {
//    try {
//      return Files.lines(Paths.get(fn));
//    } catch (IOException ioe) {
//      throw new RuntimeException(ioe);
//    }
//  }
  public static Optional<Stream<String>> getLines(String fn) {
    try {
      return Optional.of(Files.lines(Paths.get(fn)));
    } catch (IOException ioe) {
      return Optional.empty(); // NO info about nature of failure
      // "Either" is often used to represent "either this success value, or
      // this failure description"
    }
  }
  public static void main(String[] args){
    Stream.of("a.txt", "b.txt", "c.txt")
//        .flatMap(fn -> getLines(fn))
        .map(fn -> getLines(fn))
        .peek(opt -> {
          if (!opt.isPresent()) System.out.println("*** ERROR!!!");
        })
        .filter(opt -> opt.isPresent())
        .flatMap(opt -> opt.get())
        .forEach(x -> System.out.println("> " + x));
  }
}
