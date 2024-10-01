package tinydict.fulltest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import org.testng.annotations.Test;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.TestFile;
import tinydict.testhelpers.rivals.StringSetFactory;

@Test(dataProvider = "factories")
public class Contains extends BaseTest {

  public void baseForms(StringSetFactory factory) throws IOException {
    Collection<String> data = new HashSet<String>(TestFile.ZALIZNYAK_BASE.readLines());
    StringSet target = factory.create(data);

    // positive test
    for (String s : data) {
      assertTrue(target.contains(s));
    }

    // negative test
    Collection<String> largerData = TestFile.ZALIZNYAK_FULL.readLines();
    for (String s : largerData) {
      assertEquals(data.contains(s), target.contains(s));
    }
  }
}
