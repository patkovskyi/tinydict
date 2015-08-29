package jstore.fulltest;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestFile;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;
import jstore.testhelpers.rivals.arrayset.CaseSensitiveComparator;

import org.testng.annotations.Test;

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
