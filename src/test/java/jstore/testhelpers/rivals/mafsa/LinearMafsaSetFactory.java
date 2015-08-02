package jstore.testhelpers.rivals.mafsa;

import java.util.Collection;

import jstore.StringSet;
import jstore.implementations.LinearMafsaSet;
import jstore.testhelpers.rivals.StringSetFactory;

public class LinearMafsaSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return LinearMafsaSet.create(strings);
  }
}
