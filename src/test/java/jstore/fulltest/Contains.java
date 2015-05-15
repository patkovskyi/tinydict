package jstore.fulltest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;

import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class Contains extends BaseTest {

  public void baseforms(StringSetFactory factory) throws IOException {
    Collection<String> data =
        new HashSet<String>(TestHelper.readResourceFile(TestHelper.ZALIZNYAK_BASEFORMS,
            TestHelper.ZALIZNYAK_CHARSET));
    StringSet target = factory.create(data);

    // positive test
    for (String s : data) {
      assertTrue(target.contains(s));
    }

    // negative test
    Collection<String> largerData =
        TestHelper.readResourceFile(TestHelper.ZALIZNYAK, TestHelper.ZALIZNYAK_CHARSET);
    for (String s : largerData) {
      assertEquals(data.contains(s), target.contains(s));
    }
  }
}
