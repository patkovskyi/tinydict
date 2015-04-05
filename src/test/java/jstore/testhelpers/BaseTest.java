package jstore.testhelpers;

import jstore.rivals.arrayset.ArrayStringSetFactory;
import jstore.rivals.hashset.HashStringSetFactory;
import jstore.rivals.mafsa.MafsaStringSetFactory;
import jstore.test.StringSetFactory;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class BaseTest {
  @DataProvider(name = "factories", parallel = true)
  public StringSetFactory[][] getFactories() {
    return new StringSetFactory[][] { {new MafsaStringSetFactory()}, {new ArrayStringSetFactory()},
        {new HashStringSetFactory()}};
  }
}
