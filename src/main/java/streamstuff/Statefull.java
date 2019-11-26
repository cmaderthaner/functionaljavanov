package streamstuff;

import superiterable.SuperIterable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Statefull {
  public static void main(String[] args) {
    List<String> ls = Arrays.asList("Fred", "Jim");
    SuperIterable<String> sis = new SuperIterable<>(ls);

    System.out.println("super iterable (stateless)");
    sis.forEach(s -> System.out.println(s));
    sis.forEach(s -> System.out.println(s));

    System.out.println("Stream (statefull)");
    Stream<String> ss = ls.stream();
    ss.forEach(s -> System.out.println(s));
    ss.forEach(s -> System.out.println(s));
  }
}
