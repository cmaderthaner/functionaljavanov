package streamstuff;

import java.util.stream.Stream;

public class Infinite {
  public static void main(String[] args) {
    Stream.iterate(1, x -> x + 1)
        .limit(100)
        .forEach(x -> System.out.println(x));
  }
}
