package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class School2 {

  public static void printThings(List<?> ls) {
    for (Object s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("------------------------");
  }

  public static <E> List<E> getByPredicate(
      List<E> ls, Predicate<E> crit) {
    List<E> rv = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }

  public static void main(String[] args) {
    List<Student> school = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Art"),
        Student.of("Sheila", 3.7, "Math",
            "Physics", "Astrophysics", "Quantum Mechanics")
    );
    printThings(school);
    printThings(getByPredicate(school, s -> s.getGpa() > 3.5));

    List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
    printThings(getByPredicate(names, s -> s.length() < 6));
  }
}
