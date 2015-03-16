package jstore.test.correctness;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;
import jstore.Messages;
import jstore.StringSet;
import jstore.test.BaseTest;
import jstore.test.StringSetFactory;

public class Contains extends BaseTest {

  public void abcPositiveTests(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"a", "aa", "ab", "abc"});
    assertTrue(target.contains("a"));
    assertTrue(target.contains("aa"));
    assertTrue(target.contains("ab"));
    assertTrue(target.contains("abc"));
  }

  public void abcNegativeTests(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"a", "aa", "ab", "abc"});
    assertFalse(target.contains("aaa"));
    assertFalse(target.contains("abd"));
    assertFalse(target.contains("abcc"));
  }

  public void emptyStringPositiveTest(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"", "a", "aa", "ab", "abc"});
    assertTrue(target.contains(""));
  }

  public void emptyStringNegativeTest(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"a", "aa", "ab", "abc"});
    assertFalse(target.contains(""));
  }

  public void nullTest(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"a"});

    try {
      target.contains(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(Messages.NULL_STRINGS_ARE_NOT_ALLOWED, e.getMessage());
    }
  }
}
