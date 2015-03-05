package jstore.test.rivals.hashset;

import java.util.Collection;

import jstore.ITrie;
import jstore.test.rivals.ITrieFactory;

public class HashSetTrieFactory extends ITrieFactory {

  @Override
  public ITrie create(Collection<String> strings) {
    return new HashSetTrie(strings);
  }

}
