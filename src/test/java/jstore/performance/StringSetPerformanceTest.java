package jstore.performance;

import static org.junit.Assert.assertEquals;
import jstore.testhelpers.PerformanceTest;

import org.junit.Test;

public class StringSetPerformanceTest extends PerformanceTest {
  @Test
  public void HashSetLookups() {
    boolean b = true;
    for (int i = 0; i < array.length; i++) {
      b = b ^ hashSet.contains(array[i]);
    }

    // flag is used to avoid optimization
    assertEquals(false, b);
  }

  @Test
  public void StringSetLookups() {
    boolean b = true;
    for (int i = 0; i < array.length; i++) {
      b = b ^ stringSet.contains(array[i]);
    }

    // flag is used to avoid optimization
    assertEquals(false, b);
  }
}
