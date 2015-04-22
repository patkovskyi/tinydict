package jstore.fasttest;

import java.io.IOException;
import java.util.Arrays;

import jstore.Serializer;
import jstore.StringSet;
import jstore.testhelpers.BaseTest;
import jstore.testhelpers.rivals.StringSetFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Serialization extends BaseTest {

  public void abcTestBytes(StringSetFactory factory) throws ClassNotFoundException, IOException {
    String[] expected = new String[] {"ab", "aa", "a", "abc"};
    StringSet target = factory.create(expected);
    byte[] data = Serializer.serialize(target);
    target = Serializer.deserialize(data);

    String[] actual = target.getAll().toArray(new String[0]);
    Arrays.sort(expected);

    System.out.println(data.length);

    Assert.assertEquals(actual, expected);
  }

  public void abcTestFile(StringSetFactory factory) throws ClassNotFoundException, IOException {
    String[] expected = new String[] {"ab", "aa", "a", "abc"};
    StringSet target = factory.create(expected);
    Serializer.serialize(target, "test.ser");
    target = Serializer.deserialize("test.ser");

    String[] actual = target.getAll().toArray(new String[0]);
    Arrays.sort(expected);

    Assert.assertEquals(actual, expected);
  }

}
