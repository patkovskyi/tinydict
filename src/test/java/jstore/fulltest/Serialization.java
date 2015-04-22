package jstore.fulltest;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import jstore.Serializer;
import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class Serialization extends BaseTest {

  public void baseFormsTest(StringSetFactory factory) throws IOException, ClassNotFoundException {
    List<String> expected = TestHelper.readResourceFile(TestHelper.ZALIZNYAK_BASEFORMS);
    StringSet target = factory.create(expected);
    Serializer.serialize(target, "test.ser");
    target = Serializer.deserialize("test.ser");
    Collection<String> actual = target.getAll();

    Assert.assertEquals(actual, expected);
  }

}
