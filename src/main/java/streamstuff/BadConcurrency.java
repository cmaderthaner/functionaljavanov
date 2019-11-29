package streamstuff;

import java.util.stream.Stream;

public class BadConcurrency {
  public static void main(String[] args) {
    int [] count = {0};
    Stream.iterate(1, x -> x + 1)
        .limit(100_000)
        .parallel()
        .map(x -> {
          count[0]++;
          return x;
        })
        .max((a, b) -> Integer.compare(a, b))
        .ifPresent(x -> System.out.println("Max is " + x));
    System.out.println("count value is " + count[0]);

  }
}
