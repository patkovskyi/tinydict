package jstore.test.rivals.arrayset;

import java.util.Collection;

import jstore.StringSet;
import jstore.test.StringSetFactory;

public class ArraySetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new ArraySet(strings);
  }

}
