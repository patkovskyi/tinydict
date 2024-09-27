package tinydict.testhelpers.rivals.mafsa;

import java.util.Collection;

import tinydict.StringSet;
import tinydict.implementations.LinearMafsaSet;
import tinydict.testhelpers.rivals.StringSetFactory;

public class LinearMafsaSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return LinearMafsaSet.create(strings);
  }
}
