package jstore.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import jstore.Messages;
import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.rivals.StringSetFactory;

public class GetByPrefix extends BaseTest {
  private void testByPrefix(StringSetFactory factory, String[] strings, String prefix,
      String[] expected) {
    StringSet target = factory.create(strings);
    String[] actual = target.getByPrefix(prefix).toArray(new String[0]);
    assertEquals(expected, actual);
  }

  public void abcPositive(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"abc", "aa", "a", "ab"}, "ab", new String[] {"ab", "abc"});
  }

  public void abcNegative(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"abc", "aa", "a", "ab"}, "b", new String[0]);
  }

  public void emptyStringPositive(StringSetFactory factory) {
    testByPrefix(factory, new String[] {"a", "ab", ""}, "", new String[] {"a", "ab", ""});
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
