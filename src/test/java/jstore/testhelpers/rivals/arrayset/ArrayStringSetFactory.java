package jstore.testhelpers.rivals.arrayset;

import java.util.Collection;

import jstore.StringSet;
import jstore.testhelpers.rivals.StringSetFactory;

public class ArrayStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new ArrayStringSet(strings);
  }

}
