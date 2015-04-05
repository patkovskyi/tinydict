package jstore.rivals.hashset;

import java.util.Collection;

import jstore.StringSet;
import jstore.test.StringSetFactory;

public class HashStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new HashStringSet(strings);
  }

}
