package jstore.test.rivals.hashset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import jstore.Messages;
import jstore.StringSet;

public class HashTrie implements StringSet {

  private HashSet<String> set;

  public HashTrie(Collection<String> strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);

    if (strings.contains(null))
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);

    set = new HashSet<String>(strings);
  }

  @Override
  public boolean contains(String string) {
    if (string == null) {
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
    }

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
