package jstore.fulltest;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;
import jstore.testhelpers.rivals.arrayset.CaseSensitiveComparator;

public class GetAll extends BaseTest {
  public void baseformsOrdered(StringSetFactory factory) throws IOException {
    List<String> expected = TestHelper.readResourceFile(TestHelper.ZALIZNYAK_BASEFORMS);
    StringSet target = factory.create(expected);
    Collection<String> actual = target.getAll();

    Collections.sort(expected, new CaseSensitiveComparator());
    assertEquals(expected, actual);
  }
}
