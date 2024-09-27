package tinydict.fasttest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.util.Collection;

import tinydict.Messages;
import tinydict.StringSet;
import tinydict.testhelpers.BaseTest;
import tinydict.testhelpers.rivals.StringSetFactory;

import org.testng.annotations.Test;

// Because other test classes are testing Create logic through read-methods,
// only input validation will be covered in this file
@Test(dataProvider = "factories")
public class Create extends BaseTest {

  public void nullArrayTest(StringSetFactory factory) {
    try {
      StringSet target = factory.create((String[]) null);
      fail(target.toString());
      assertNull(target);
    } catch (IllegalArgumentException e) {
      assertEquals(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL, e.getMessage());
    }
  }

  public void nullCollectionTest(StringSetFactory factory) {
    try {
      StringSet target = factory.create((Collection<String>) null);
      fail(target.toString());
    } catch (IllegalArgumentException e) {
      assertEquals(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL, e.getMessage());
    }
  }

  public void nullStringTest(StringSetFactory factory) {
    try {
      StringSet target = factory.create(new String[] {null, "a", "ab"});
      fail(target.toString());
    } catch (IllegalArgumentException e) {
      assertEquals(Messages.NULL_STRINGS_ARE_NOT_ALLOWED, e.getMessage());
    }
  }

  public void emptyStringTest(StringSetFactory factory) {
    StringSet target = factory.create(new String[] {"", "a", "ab"});
    assertNotNull(target);
  }
}
