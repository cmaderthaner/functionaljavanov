package streamstuff;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseOptional {
  public static void main(String[] args) {
    Optional<String> os = Optional.empty();
    os
        .map(s -> "Dear " + s.toUpperCase())
        .ifPresent(s -> System.out.println(s));

    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");

    String firstName = "Fred";

    String last = names.get(firstName);
    if (last != null) {
      String msg = last.toUpperCase();
      msg = "Dear " + msg;
      System.out.println(msg);
    }

    Optional.of(names)
        .map(m -> m.get(firstName))
        .map(s -> s.toUpperCase())
        .map(s -> "Dear " + s)
        .ifPresent(s -> System.out.println(s));

  }
}
