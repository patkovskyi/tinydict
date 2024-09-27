package tinydict.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import tinydict.Messages;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.rivals.StringSetFactory;

import org.testng.annotations.Test;

import com.google.common.collect.Iterables;

@Test(dataProvider = "factories")
public class IterateByPrefix extends BaseTest {

  private void testByPrefix(StringSetFactory factory, String[] strings, String prefix,
      String[] expected) {
    StringSet target = factory.create(strings);
    String[] actual = Iterables.toArray(target.iterateByPrefix(prefix), String.class);
    assertEquals(actual, expected);
  }

  public void abcPositive(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"abc", "aa", "a", "ab"}, "ab", new String[] {"ab", "abc"});
  }

  public void abcNegative(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"abc", "aa", "a", "ab"}, "b", new String[0]);
  }

  public void emptyStringPositive(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"a", "ab", ""}, "", new String[] {"", "a", "ab"});
  }

  public void emptyStringNegative(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"a", "abc", ""}, "ab", new String[] {"abc"});
  }

  public void emptyStringNegative2(StringSetFactory factory) {
    testByPrefix(factory, new String[0], "", new String[0]);
  }

  public void nullTest(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"a"});

    try {
      target.getByPrefix(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(Messages.NULL_STRINGS_ARE_NOT_ALLOWED, e.getMessage());
    }
  }
}
