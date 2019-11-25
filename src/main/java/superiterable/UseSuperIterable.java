package superiterable;

import students.Student;

import java.util.Arrays;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        Arrays.asList("Fred", "Jim", "Sheila")
    );

    sis.forEach(s -> System.out.println(s));
//    for (String s : sis) {
//      System.out.println("> " + s);
//    }
    System.out.println("----------------------");

    sis
        .filter(s -> s.length() < 6)
        .forEach(s -> System.out.println(s));

    SuperIterable<Student> schoolSuper = new SuperIterable<>(
        Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Art"),
        Student.of("Sheila", 3.7, "Math",
            "Physics", "Astrophysics", "Quantum Mechanics")
    ));

    schoolSuper
        .filter(s -> s.getGpa() > 3)
        .map(s -> s.getName() + " has grade " + s.getGpa())
        .forEach(s -> System.out.println(s));
  }
}
