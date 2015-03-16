package jstore.test;

import jstore.test.rivals.arrayset.ArraySetFactory;
import jstore.test.rivals.hashset.HashTrieStringSetFactory;
import jstore.test.rivals.mafsa.MafsaStringSetFactory;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(dataProvider = "factories")
public class BaseTest {
  @DataProvider(name = "factories", parallel = true)
  public StringSetFactory[][] getFactories() {
    return new StringSetFactory[][] { {new MafsaStringSetFactory()}, {new ArraySetFactory()},
        {new HashTrieStringSetFactory()}};
  }
}
