package tinydict.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import tinydict.Messages;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.rivals.StringSetFactory;

import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class Contains extends BaseTest {

  public void abcPositive(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"abc", "aa", "a", "ab"});
    assertTrue(target.contains("a"));
    assertTrue(target.contains("aa"));
    assertTrue(target.contains("ab"));
    assertTrue(target.contains("abc"));
  }

  public void abcNegative(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"abc", "aa", "a", "ab"});
    assertFalse(target.contains("aaa"));
    assertFalse(target.contains("abd"));
    assertFalse(target.contains("abcc"));
  }

  public void emptyStringPositive(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"abc", "aa", "a", "ab", ""});
    assertTrue(target.contains(""));
  }

  public void emptyStringNegative(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"abc", "aa", "a", "ab"});
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
