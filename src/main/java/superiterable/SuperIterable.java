package superiterable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  // this is already in Iterable, as "forEach"
//  public void forEvery(Consumer<E> op) {
//    for (E e : self) {
//      op.accept(e);
//    }
//  }

  public SuperIterable<E> filter(Predicate<E> pred) {
    List<E> res = new ArrayList<>();
    for (E e : self) {
      if (pred.test(e)) {
        res.add(e);
      }
    }
    return new SuperIterable<>(res);
  }

  public <F> SuperIterable<F>  map(Function<E, F> op) {
    List<F> results = new ArrayList<>();
    for (E e : self) {
      results.add(op.apply(e));
    }
    return new SuperIterable(results);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
