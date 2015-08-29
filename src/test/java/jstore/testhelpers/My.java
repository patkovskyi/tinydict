package jstore.testhelpers;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import org.testng.Assert;

import java.util.Arrays;
import java.util.Collection;

public class My {

  static public void assertTrue(boolean value) {
    Assert.assertTrue(value);
  }

  static public void assertFalse(boolean value) {
    Assert.assertFalse(value);
  }

  static public void fail() {
    Assert.fail();
  }

  static public void fail(String message) {
    Assert.fail(message);
  }

  static public void assertEquals(Object actual, Object expected) {
    Assert.assertEquals(actual, expected);
  }

  static public void assertArraysEqual(Object[] actual, Object[] expected) {
    Assert.assertEquals(actual, expected);
  }

  static public void assertArraysEquivalent(Object[] actual, Object[] expected) {
    assertCollectionsEquivalent(Arrays.asList(actual), Arrays.asList(expected));
  }

  static public void assertCollectionsEqual(Collection<?> actual, Collection<?> expected) {
    Assert.assertEquals(actual, expected);
  }

  static public void assertCollectionsEquivalent(Collection<?> actual, Collection<?> expected) {
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
