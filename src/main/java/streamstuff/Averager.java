package streamstuff;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private long count = 0;
  private double sum = 0;

  public void include(double d) {
    sum += d;
    count++;
  }

  public void merge(Average other) {
    this.sum += other.sum;
    this.count += other.count;
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    // UNORDERED
//    ThreadLocalRandom.current()
//        .doubles(5_000_000_000L, -Math.PI, +Math.PI)
// generate is also unordered, but iterate is ORDERED
    DoubleStream.iterate(0.0,
        x -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
        .limit(5_000_000_000L)
        .unordered()
        .parallel() // affects ENTIRE STREAM
        .sequential()
        .parallel() //ONLY THE LAST is effective
        // DO MORE WORK HERE, amortize / dilute the "overhead" of parallelism
        // result is typically greater "proportional" increase of throughput.
        // did your cpus have something else useful to do??? if yes,
        // probably should not run parallel
        .collect( // assumes ordering // See Collectors for "SQL-like" prebuilt mechanisms..
//            () -> new Average(),
            Average::new,
//            (a, d) -> a.include(d),
            Average::include,
//            (a, a2) -> a.merge(a2))
            Average::merge)
        .get()
        .ifPresent(x -> System.out.println("Mean is " + x));
    long time = System.nanoTime() - start;
    System.out.printf("Time is %7.5f\n", (time / 1_000_000_000.0));
  }
}
