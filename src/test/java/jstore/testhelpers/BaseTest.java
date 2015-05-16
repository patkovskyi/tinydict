package jstore.testhelpers;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import jstore.testhelpers.rivals.StringSetFactory;
import jstore.testhelpers.rivals.arrayset.ArrayStringSetFactory;
import jstore.testhelpers.rivals.hashset.HashStringSetFactory;
import jstore.testhelpers.rivals.mafsa.BinaryMafsaStringSetFactory;
import jstore.testhelpers.rivals.mafsa.LinearMafsaStringSetFactory;

public class BaseTest {
  @DataProvider(name = "factories", parallel = true)
  public StringSetFactory[][] getFactories(ITestContext context) {

    String factoryClassesParam = context.getCurrentXmlTest().getParameter("factoryClasses");
    if (factoryClassesParam == null) {
      return new StringSetFactory[][]{{new LinearMafsaStringSetFactory()},
                                      {new BinaryMafsaStringSetFactory()},
                                      {new ArrayStringSetFactory()},
                                      {new HashStringSetFactory()}};
    }

    String[] factoryClasses = factoryClassesParam.split(",");
    StringSetFactory[][] factories = new StringSetFactory[factoryClasses.length][0];
    for (int i = 0; i < factoryClasses.length; i++) {
      try {
        factories[i] =
            new StringSetFactory[]{
                (StringSetFactory) Class.forName(factoryClasses[i].trim()).newInstance()};
      } catch (Exception e) {
        System.err.println(e.getMessage());
        System.err.println(
            "Make sure that you have fully-specified and correct factoryClasses in testng*.xml");
      }
    }

    return  factories;
  }
}
