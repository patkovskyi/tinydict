package tinydict.performancetest;

import static org.testng.Assert.assertFalse;

import org.junit.Test;
import tinydict.testhelpers.PerformanceTest;

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
