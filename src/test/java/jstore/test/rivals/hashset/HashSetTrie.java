package jstore.test.rivals.hashset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import jstore.ITrie;

public class HashSetTrie implements ITrie {

  private HashSet<String> set;

  public HashSetTrie(Collection<String> array) {
    set = new HashSet<String>(array);
  }

  @Override
  public boolean contains(String string) {
    return set.contains(string);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<String> getAll() {
    return (HashSet<String>) set.clone();
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
    Collection<String> result = new ArrayList<String>();
    for (String s : getAll()) {
      if (s.startsWith(prefix))
        result.add(s);
    }

    return result;
  }

}
