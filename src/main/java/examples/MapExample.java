package examples;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
  public static void main(String[] args) {
    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");
    System.out.println(names);
    System.out.println("-----------------------");
    names.compute("Fred", (k, v) -> v.toUpperCase());
    System.out.println(names);
    System.out.println("-----------------------");
    names.compute("Jim", (k, v) -> "Smith");
    System.out.println(names);
    System.out.println("-----------------------");
    names.compute("Jim", (k, v) -> null);
    System.out.println(names);
    System.out.println("-----------------------");
  }
}
