package jstore.rivals.arrayset;

import java.util.Collection;

import jstore.StringSet;
import jstore.test.StringSetFactory;

public class ArrayStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new ArrayStringSet(strings);
  }

}
