package jstore.testhelpers.rivals.mafsa;

import java.util.Collection;

import jstore.StringSet;
import jstore.implementations.BinaryMafsaSet;
import jstore.testhelpers.rivals.StringSetFactory;

public class BinaryMafsaStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return BinaryMafsaSet.create(strings);
  }
}
