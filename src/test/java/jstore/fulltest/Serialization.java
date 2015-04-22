package jstore.fulltest;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jstore.Serializer;
import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.TestHelper;
import jstore.testhelpers.rivals.StringSetFactory;
import jstore.testhelpers.rivals.arrayset.CaseSensitiveComparator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class Serialization extends BaseTest {

  public void baseFormsTest(StringSetFactory factory) throws IOException, ClassNotFoundException {
    List<String> expected = TestHelper.readResourceFile(TestHelper.ZALIZNYAK_BASEFORMS);
    StringSet target = factory.create(expected);
    Serializer.serialize(target, factory.getClass().getSimpleName() + ".ser");
    target = Serializer.deserialize(factory.getClass().getSimpleName() + ".ser");
    Collection<String> actual = target.getAll();

    Collections.sort(expected, new CaseSensitiveComparator());
    Assert.assertEquals(actual, expected);
  }

}
