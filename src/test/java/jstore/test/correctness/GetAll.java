package jstore.test.correctness;

import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import java.util.Arrays;

import jstore.StringSet;
import jstore.rivals.arrayset.CaseSensitiveComparator;
import jstore.test.StringSetFactory;
import jstore.testhelpers.BaseTest;

public class GetAll extends BaseTest {
  public void abcUnordered(StringSetFactory factory) {
    String[] expected = new String[] {"a", "aa", "ab", "abc"};
    StringSet target = factory.create(expected);
    String[] actual = target.getAll().toArray(new String[0]);

    assertEqualsNoOrder(expected, actual);
  }

  public void abcOrdered(StringSetFactory factory) {
    String[] expected = new String[] {"abc", "aa", "a", "ab"};
    StringSet target = factory.create(expected);
    String[] actual = target.getAll().toArray(new String[0]);

    Arrays.sort(expected, new CaseSensitiveComparator());
    assertArrayEquals(expected, actual);
  }
}
