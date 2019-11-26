package examples;

interface Operation {
  int test(String s);
}

@FunctionalInterface
interface Behavior {
  int doStuff(StringBuilder cs);
//  void doOther();
}

public class MoreStuff {

  public static void confusing(Operation a){
    System.out.println("Operation..");
  }

  public static void confusing(Behavior b) {
    System.out.println("Behavior..");
  }

  public static void main(String[] args) {
//    confusing(String::length);
    confusing((StringBuilder s) -> s.length());
  }
}
