package tinydict.fulltest;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import tinydict.Serializer;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.TestFile;
import tinydict.testhelpers.TestHelper;
import tinydict.testhelpers.rivals.StringSetFactory;
import tinydict.testhelpers.rivals.arrayset.CaseSensitiveComparator;

@Test(dataProvider = "factories")
public class Serialization extends BaseTest {

  public void baseFormsTest(StringSetFactory factory) throws IOException, ClassNotFoundException {
    List<String> expected = TestFile.ZALIZNYAK_BASE.readLines();
    StringSet target = factory.create(expected);
    String filePath = TestHelper.getRandomFilePath();

    Serializer.serialize(target, filePath);
    target = Serializer.deserialize(filePath);
    Collection<String> actual = target.getAll();

    Collections.sort(expected, new CaseSensitiveComparator());
    Assert.assertEquals(actual, expected);
  }
}
