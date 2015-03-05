package jstore.test.rivals.stringset;

import java.util.Collection;

import jstore.ITrie;
import jstore.StringSet;
import jstore.test.rivals.ITrieFactory;

public class StringSetFactory extends ITrieFactory {

  @Override
  public ITrie create(Collection<String> strings) {
    return StringSet.create(strings);
  }
}
