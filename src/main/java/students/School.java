package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

class SmartStudentCriterion implements StudentCriterion {
  @Override
  public boolean test(Student s) {
    return s.getGpa() > 3;
  }
}

class EnthusiasticStudentCriterion implements StudentCriterion {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

public class School {

  public static void printStudents(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("------------------------");
  }

  // "Command" pattern
  public static List<Student> getByCriterion(
      List<Student> ls, StudentCriterion crit) {
    List<Student> rv = new ArrayList<>();
    for (Student s : ls) {
      if (crit.test(s)) { // NOT crit(s) !!!! Lambdas create object instances
        rv.add(s);
      }
    }
    return rv;
  }

  //  public static List<Student> getSmart(List<Student> ls, double threshold) {
//    List<Student> rv = new ArrayList<>();
//    for(Student s : ls) {
//      if (s.getGpa() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
//  public static List<Student> getEnthusiastic(List<Student> ls, int threshold) {
//    List<Student> rv = new ArrayList<>();
//    for(Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
  public static void main(String[] args) {
    List<Student> school = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.8, "Art"),
        Student.of("Sheila", 3.7, "Math",
            "Physics", "Astrophysics", "Quantum Mechanics")
    );
    printStudents(school);
//    printStudents(getSmart(school, 3));
//    printStudents(getEnthusiastic(school, 2));

    printStudents(getByCriterion(school, new SmartStudentCriterion()));
    printStudents(getByCriterion(school, new EnthusiasticStudentCriterion()));


    // anonymous inner class (Since Java 1.1)
    printStudents(getByCriterion(school, new
        /*class SmartStudentCriterion implements */
        StudentCriterion() {
          @Override
          public boolean test(Student s) {
            return s.getGpa() < 3;
          }
        }
    ));

    // Lambda expression IS AN OBJECT
    printStudents(getByCriterion(school,
        (Student s) -> {
          return s.getGpa() < 3;
        }
    ));

    // if argument types are unambigous, can leave them out (all or nothing)
    printStudents(getByCriterion(school,
        (s) -> {
          return s.getGpa() < 3;
        }
    ));

    // In UNIQUE situation of one, untyped, argument, parens are not needed
    // NOT allowed "headless" -> {}
    printStudents(getByCriterion(school,
        s -> {
          return s.getGpa() < 3;
        }
    ));

    // If the body simply says "return <expression>" can leave out "body"
    // simply provide that expression
    printStudents(getByCriterion(school,
        s -> /*{
          return */s.getGpa() < 3/*;
        }*/
    ));

    printStudents(getByCriterion(school, s -> s.getGpa() < 3));

  }
}
