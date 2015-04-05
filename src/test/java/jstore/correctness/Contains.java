package jstore.correctness;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import jstore.Messages;
import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;

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

  public void baseformsTest(StringSetFactory factory) throws IOException {
    Collection<String> data =
        new HashSet<String>(TestHelper.readResourceFile(TestHelper.ZALIZNYAK_BASEFORMS));
    StringSet target = factory.create(data);

    // positive test
    for (String s : data) {
      assertTrue(target.contains(s));
    }

    // negative test
    Collection<String> largerData = TestHelper.readResourceFile(TestHelper.ZALIZNYAK);
    for (String s : largerData) {
      assertEquals(data.contains(s), target.contains(s));
    }
  }
}
