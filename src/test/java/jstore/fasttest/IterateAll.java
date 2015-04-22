package jstore.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;

import java.util.Arrays;

import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.rivals.StringSetFactory;
import jstore.testhelpers.rivals.arrayset.CaseSensitiveComparator;

import org.testng.annotations.Test;

import com.google.common.collect.Iterables;

@Test(dataProvider = "factories")
public class IterateAll extends BaseTest {

  public void abcUnordered(StringSetFactory factory) {
    String[] expected = new String[] {"a", "aa", "ab", "abc"};
    StringSet target = factory.create(expected);
    String[] actual = Iterables.toArray(target.iterateAll(), String.class);

    assertEqualsNoOrder(actual, expected);
  }

  public void abcOrdered(StringSetFactory factory) {
    String[] expected = new String[] {"abc", "aa", "a", "ab"};
    StringSet target = factory.create(expected);
    String[] actual = Iterables.toArray(target.iterateAll(), String.class);

    Arrays.sort(expected, new CaseSensitiveComparator());
    assertEquals(actual, expected);
  }

  public void emptyString(StringSetFactory factory) {
    String[] expected = new String[] {""};
    StringSet target = factory.create(expected);
    String[] actual = Iterables.toArray(target.iterateAll(), String.class);

    assertEquals(actual, expected);
  }

  public void emptyList(StringSetFactory factory) {
    String[] expected = new String[0];
    StringSet target = factory.create(expected);
    String[] actual = Iterables.toArray(target.iterateAll(), String.class);

    assertEquals(actual, expected);
  }

}
