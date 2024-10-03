package tinydict.implementations;

public class Pair<A extends Comparable<A>, B> implements Comparable<Pair<A, B>> {
  private final A first;
  private final B second;

  public static <A extends Comparable<A>, B> Pair<A, B> of(final A first, final B second) {
    return new Pair<>(first, second);
  }

  private Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  public A getFirst() {
    return first;
  }

  public B getSecond() {
    return second;
  }

  @Override
  public int compareTo(Pair<A, B> o) {
    return this.first.compareTo(o.first);
  }
}
