package jstore.fasttest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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

  public void toBytes(StringSetFactory factory) throws ClassNotFoundException, IOException {
    List<String> expected = Arrays.asList("ab", "aa", "a", "abc");
    StringSet target = factory.create(expected);

    byte[] data = Serializer.serialize(target);
    target = Serializer.deserialize(data);

    Collection<String> actual = target.getAll();
    Collections.sort(expected);

    Assert.assertEquals(actual, expected);
  }

  public void toFile(StringSetFactory factory) throws ClassNotFoundException, IOException {
    List<String> expected = Arrays.asList("ab", "aa", "a", "abc");
    StringSet target = factory.create(expected);
    String filePath = TestHelper.getRandomFilePath();

    Serializer.serialize(target, filePath);
    target = Serializer.deserialize(filePath);

    Collection<String> actual = target.getAll();
    Collections.sort(expected);

    Assert.assertEquals(actual, expected);
  }

}
