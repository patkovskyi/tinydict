package jstore.testhelpers.rivals.mafsa;

import java.util.Collection;

import jstore.StringSet;
import jstore.implementations.MafsaSet;
import jstore.testhelpers.rivals.StringSetFactory;

public class MafsaStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return MafsaSet.create(strings);
  }
}
