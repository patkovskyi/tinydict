package tinydict.testhelpers.rivals.hashset;

import java.util.Collection;

import tinydict.StringSet;
import tinydict.testhelpers.rivals.StringSetFactory;

public class HashStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new HashStringSet(strings);
  }

}
