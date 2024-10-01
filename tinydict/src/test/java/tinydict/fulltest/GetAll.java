package tinydict.fulltest;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.Test;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.TestFile;
import tinydict.testhelpers.rivals.StringSetFactory;
import tinydict.testhelpers.rivals.arrayset.CaseSensitiveComparator;

import static org.testng.Assert.assertEquals;

@Test(dataProvider = "factories")
public class GetAll extends BaseTest {

  public void baseFormsOrdered(StringSetFactory factory) throws IOException {
    List<String> expected = TestFile.ZALIZNYAK_BASE.readLines();
    StringSet target = factory.create(expected);
    Collection<String> actual = target.getAll();

    Collections.sort(expected, new CaseSensitiveComparator());
    assertEquals(expected, actual);
  }
}
