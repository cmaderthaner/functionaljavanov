package streamstuff;

import java.util.stream.Stream;

public class Lazy {
  public static void main(String[] args) {
    Stream.iterate(1, x -> x + 1)
        .limit(10)
        .peek(x -> System.out.println("Peeking: " + x))
        .allMatch(x -> x % 7 != 0)
//        .forEach(x -> System.out.println("forEach " + x))
        ;
  }
}
