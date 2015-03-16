package jstore.test.rivals.hashset;

import java.util.Collection;

import jstore.StringSet;
import jstore.test.StringSetFactory;

public class HashTrieStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new HashTrie(strings);
  }

}
