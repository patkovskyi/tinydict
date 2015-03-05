package jstore.test.rivals.arrayset;

import java.util.Collection;

import jstore.ITrie;
import jstore.test.rivals.ITrieFactory;

public class ArraySetFactory extends ITrieFactory {

  @Override
  public ITrie create(Collection<String> strings) {
    return new ArraySet(strings);
  }

}
