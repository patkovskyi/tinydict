package jstore.performancetest;

import org.testng.annotations.Test;

import jstore.testhelpers.PerformanceTest;

import static org.testng.Assert.assertFalse;

public class StringSetPerformanceTest extends PerformanceTest {

  @Test
  public void HashSetLookups() {
    boolean b = true;
    for (int i = 0; i < array.length; i++) {
      b = b ^ hashSet.contains(array[i]);
    }

    // flag is used to avoid optimization
    assertFalse(b);
  }

  @Test
  public void StringSetLookups() {
    boolean b = true;
    for (int i = 0; i < array.length; i++) {
      b = b ^ stringSet.contains(array[i]);
    }

    // flag is used to avoid optimization
    assertFalse(b);
  }
}
