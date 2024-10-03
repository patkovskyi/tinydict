package tinydict.testhelpers;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import tinydict.testhelpers.rivals.StringSetFactory;
import tinydict.testhelpers.rivals.arrayset.ArrayStringSetFactory;
import tinydict.testhelpers.rivals.hashset.HashStringSetFactory;
import tinydict.testhelpers.rivals.mafsa.BinaryMafsaSetFactory;
import tinydict.testhelpers.rivals.mafsa.LinearMafsaSetFactory;
import tinydict.testhelpers.rivals.mafsa.SerializedBinaryMafsaSetFactory;
import tinydict.testhelpers.rivals.mafsa.SerializedLinearMafsaSetFactory;

public class BaseTest {
  @DataProvider(name = "factories", parallel = true)
  public StringSetFactory[][] getFactories(ITestContext context) {
    return new StringSetFactory[][] {
      {new LinearMafsaSetFactory()},
      {new BinaryMafsaSetFactory()},
      {new SerializedLinearMafsaSetFactory()},
      {new SerializedBinaryMafsaSetFactory()},
      {new ArrayStringSetFactory()},
      {new HashStringSetFactory()}
    };
  }
}
