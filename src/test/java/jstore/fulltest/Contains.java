package jstore.fulltest;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;

public class Contains extends BaseTest {
  public void largerTest(StringSetFactory factory) throws IOException {
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
