package tinydict.testhelpers;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import java.util.Collection;
import org.testng.Assert;

public class My {

  public static void assertTrue(boolean value) {
    Assert.assertTrue(value);
  }

  public static void assertFalse(boolean value) {
    Assert.assertFalse(value);
  }

  public static void fail() {
    Assert.fail();
  }

  public static void fail(String message) {
    Assert.fail(message);
  }

  public static void assertEquals(Object actual, Object expected) {
    Assert.assertEquals(actual, expected);
  }

  public static void assertArraysEqual(Object[] actual, Object[] expected) {
    Assert.assertEquals(actual, expected);
  }

  public static void assertArraysEquivalent(Object[] actual, Object[] expected) {
    assertCollectionsEquivalent(Arrays.asList(actual), Arrays.asList(expected));
  }

  public static void assertCollectionsEqual(Collection<?> actual, Collection<?> expected) {
    Assert.assertEquals(actual, expected);
  }

  public static void assertCollectionsEquivalent(Collection<?> actual, Collection<?> expected) {
    if (actual.size() != expected.size()) {
      fail("Size mismatch");
    }

    Multiset<?> expectedBag = HashMultiset.create(expected);

    int index = 0;
    for (Object element : actual) {
      if (!expectedBag.remove(element)) {
        Assert.fail(String.format("Element at index %s was not expected: %s", index, element));
      }
    }
  }
}
