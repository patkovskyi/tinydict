package jmh;

import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@State(Scope.Benchmark)
public class StringSetBenchmark extends AbstractBenchmark {

  @Setup
  public static void setUp() throws Exception {
    AbstractBenchmark.setUp();
  }

  @Benchmark
  @Warmup(iterations = 5, time = 500, timeUnit = MILLISECONDS)
  @Measurement(iterations = 1, time = 5, timeUnit = SECONDS)
  @Fork(1)
  public void hashSetLookups() {
    boolean b = true;
    for (int i = 0; i < array.length; i++) {
      b = b ^ hashSet.contains(array[i]);
    }
  }

  @Benchmark
  @Warmup(iterations = 5, time = 500, timeUnit = MILLISECONDS)
  @Measurement(iterations = 1, time = 5, timeUnit = SECONDS)
  @Fork(1)
  public void stringSetLookups() {
    boolean b = true;
    for (int i = 0; i < array.length; i++) {
      b = b ^ stringSet.contains(array[i]);
    }
  }
}
