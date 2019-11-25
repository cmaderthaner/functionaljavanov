package examples;

import students.School;
import students.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Ex1 {
  static <E> Comparator<E> thenComparing(Comparator<E> first, Comparator<E> second) {
    return (e1, e2) -> {
      int rv = first.compare(e1, e2);
      if (rv == 0) rv = second.compare(e1, e2);
      return rv;
    };
  }

  public static void main(String[] args) {
    List<Student> school = Arrays.asList(
        Student.of("William", 2.8, "Math", "Physics"),
        Student.of("Jim", 2.8, "Art"),
        Student.of("Alice", 3.8, "History", "Geography"),
        Student.of("Fred", 2.8, "Math"),
        Student.of("Fred", 2.8, "Math"),
        Student.of("Sheila", 3.7, "Math",
            "Physics", "Astrophysics", "Quantum Mechanics")
    );
    Comparator<Student> byName =
        (s1, s2) -> s1.getName().compareTo(s2.getName());
    Comparator<Student> byGrade =
        (s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa());
//    school.sort(byGrade);
//    School.printStudents(school);

//    school.sort(thenComparing(byGrade, byName));
    school.sort(byGrade.thenComparing(byName));

    School.printStudents(school);

  }
}
