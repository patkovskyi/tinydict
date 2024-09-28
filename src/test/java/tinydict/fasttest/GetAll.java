package tinydict.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.testng.annotations.Test;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.rivals.StringSetFactory;
import tinydict.testhelpers.rivals.arrayset.CaseSensitiveComparator;

@Test(dataProvider = "factories")
public class GetAll extends BaseTest {

  public void abcUnordered(StringSetFactory factory) {
    String[] expected = new String[] {"a", "aa", "ab", "abc"};
    StringSet target = factory.create(expected);
    String[] actual = target.getAll().toArray(new String[0]);

    assertEqualsNoOrder(actual, expected);
  }

  public void abcOrdered(StringSetFactory factory) {
    String[] expected = new String[] {"abc", "aa", "a", "ab"};
    StringSet target = factory.create(expected);
    String[] actual = target.getAll().toArray(new String[0]);

    Arrays.sort(expected, new CaseSensitiveComparator());
    assertEquals(actual, expected);
  }

  public void emptyString(StringSetFactory factory) {
    String[] expected = new String[] {""};
    StringSet target = factory.create(expected);
    String[] actual = target.getAll().toArray(new String[0]);

    assertEquals(actual, expected);
  }

  public void emptyList(StringSetFactory factory) {
    Collection<String> expected = new ArrayList<String>();
    StringSet target = factory.create(expected);
    Collection<String> actual = target.getAll();

    assertEquals(actual, expected);
  }
}
