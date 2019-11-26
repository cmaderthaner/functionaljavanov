package streamstuff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

interface ExFunction<A, B> {
  B apply(A a) throws Throwable;
}

public class ExceptionSolution {

  public static <A, B> Function<A, Optional<B>> wrap(ExFunction<A, B> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args){
    Stream.of("a.txt", "b.txt", "c.txt")
        .map(wrap(fn -> Files.lines(Paths.get(fn))))
        .peek(opt -> {
          if (!opt.isPresent()) System.out.println("*** ERROR!!!");
        })
        .filter(opt -> opt.isPresent())
        .flatMap(opt -> opt.get())
        .forEach(x -> System.out.println("> " + x));
  }
}
