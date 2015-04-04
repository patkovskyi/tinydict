package jstore.test;

import jstore.test.rivals.arrayset.ArrayStringSetFactory;
import jstore.test.rivals.hashset.HashStringSetFactory;
import jstore.test.rivals.mafsa.MafsaStringSetFactory;

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
