package tinydict.testhelpers;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import java.util.Collection;
import org.testng.Assert;

public class MyAssert {

  public static void assertArraysEquivalent(Object[] actual, Object[] expected) {
    assertCollectionsEquivalent(Arrays.asList(actual), Arrays.asList(expected));
  }

  public static void assertCollectionsEquivalent(Collection<?> actual, Collection<?> expected) {
    if (actual.size() != expected.size()) {
      Assert.fail("Size mismatch");
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
