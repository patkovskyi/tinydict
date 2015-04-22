package jstore.testhelpers.rivals.hashset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import jstore.StringSet;
import jstore.testhelpers.TestHelper;

public class HashStringSet implements StringSet, Serializable {

  private static final long serialVersionUID = 1L;

  private HashSet<String> set;

  public HashStringSet(Collection<String> strings) {
    TestHelper.verifyStringCollection(strings);

    set = new HashSet<String>(strings);
  }

  @Override
  public boolean contains(String string) {
    TestHelper.verifyInputString(string);

    return set.contains(string);
  }

  @Override
  public Collection<String> getAll() {
    String[] array = set.toArray(new String[0]);
    Arrays.sort(array);
    return Arrays.asList(array);
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
    TestHelper.verifyInputString(prefix);

    Collection<String> result = new ArrayList<String>();
    for (String s : getAll()) {
      if (s.startsWith(prefix))
        result.add(s);
    }

    return result;
  }

  @Override
  public Iterable<String> iterateAll() {
    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        return getAll().iterator();
      }
    };
  }

  @Override
  public Iterable<String> iterateByPrefix(final String prefix) {
    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        return getByPrefix(prefix).iterator();
      }
    };
  }

}
