package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Criterion<E> {
  boolean test(Student s);
  default Criterion<E> negate() {
    return s -> !this.test(s);
  }
  default Criterion<E> or(Criterion<E> second) {
    return s -> this.test(s) || second.test(s);
  }
  static <E> Criterion<E> negate(Criterion<E> crit) {
    return s -> !crit.test(s);
  }
  static <E> Criterion<E> or(
      Criterion<E> first, Criterion<E> second) {
    return s -> first.test(s) || second.test(s);
  }
}

interface StudentCriterion {
  boolean test(Student s);
  default StudentCriterion negate() {
    return s -> !this.test(s);
  }
  default StudentCriterion or(StudentCriterion second) {
    return s -> this.test(s) || second.test(s);
  }
  static StudentCriterion negate(StudentCriterion crit) {
    return s -> !crit.test(s);
  }
  static StudentCriterion or(
      StudentCriterion first, StudentCriterion second) {
    return s -> first.test(s) || second.test(s);
  }
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

  public static List<Student> getByPredicate(
      List<Student> ls, Criterion<Student> crit) {
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
//    printThings(getSmart(school, 3));
//    printThings(getEnthusiastic(school, 2));

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

    StudentCriterion smart = s -> s.getGpa() > 3;
    System.out.println("Name of class of smart is "
        + smart.getClass().getName());
    System.out.println("smart instaceof StudentCriterion "
        + ( smart instanceof StudentCriterion));

    // "Normal Cast"
    Object o = "Hello";
    String st = (String)o;
    // cast can take the place of "unambiguous context"
    Object smartObject = (StudentCriterion)(s -> s.getGpa() > 3);
//
//    StudentCriterion notSmart = s -> s.getGpa() <= 3;
//
//
//    StudentCriterion extremesSmart = s -> s.getGpa() > 3.5 || s.getGpa() <= 3;


//    StudentCriterion notSmart =
//        StudentCriterion.negate(Student.getSmartCriterion(2.9));
    StudentCriterion notSmart =
        Student.getSmartCriterion(2.9).negate();
    StudentCriterion fairlySmart = s -> s.getGpa() > 3.5;
//    StudentCriterion fairlySmartOrNotSmart =
//        StudentCriterion.or(fairlySmart, notSmart);
    StudentCriterion fairlySmartOrNotSmart =
        fairlySmart.or(notSmart);
    printStudents(getByCriterion(school, fairlySmartOrNotSmart));
    printStudents(getByCriterion(school,
        fairlySmartOrNotSmart.negate()));

    printStudents(getByPredicate(school,
        (s -> s.getGpa() > 3.5)));
  }
}
