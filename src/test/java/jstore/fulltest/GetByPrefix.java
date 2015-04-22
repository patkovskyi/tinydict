package jstore.fulltest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;

import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class GetByPrefix extends BaseTest {

  private void testByPrefix(StringSet target, Collection<String> strings, String prefix) {
    Collection<String> actual = target.getByPrefix(prefix);
    Collection<String> expected = new TreeSet<String>();
    for (String s : strings) {
      if (s.startsWith(prefix)) {
        expected.add(s);
      }
    }

    assertEquals(actual, expected);
  }

  public void baseforms(StringSetFactory factory) throws IOException {
    List<String> data = TestHelper.readResourceFile(TestHelper.ZALIZNYAK_BASEFORMS);
    StringSet target = factory.create(data);

    // positive test
    int i = 0;
    for (String s : data) {
      if (s.length() >= 6) {
        if (i % 1000 == 0) {
          testByPrefix(target, data, s.substring(0, s.length() - 1));
        }

        ++i;
      }
    }

    // negative test
    i = 0;
    for (String s : data) {
      if (s.length() >= 6) {
        if (i % 1000 == 0) {
          testByPrefix(target, data, s + "и");
        }

        ++i;
      }
    }
  }
}
