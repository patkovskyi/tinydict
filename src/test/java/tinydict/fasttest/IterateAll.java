package tinydict.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import org.testng.annotations.Test;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.rivals.StringSetFactory;
import tinydict.testhelpers.rivals.arrayset.CaseSensitiveComparator;

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
