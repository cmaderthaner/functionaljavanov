package students;

import java.util.Arrays;
import java.util.List;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  private Student() {}

  public static Student of(String name, double gpa, String ... courses) {
    Student self = new Student();
    self.name = name;
    self.gpa = gpa;
    self.courses = Arrays.asList(courses);
    return self;
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  // "closure" promotes the method local variable (which short lifetime)
  // effectively into a read-only member of the "object" that is the lambda
  // closure-captured variables must be final, or effectively final
  public static StudentCriterion getSmartCriterion(/*final */double threshold) {
//    threshold++;
    return s -> s.gpa > threshold;
  }
}
