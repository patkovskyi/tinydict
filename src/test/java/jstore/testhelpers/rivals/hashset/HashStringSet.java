package jstore.testhelpers.rivals.hashset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import jstore.StringSet;
import jstore.testhelpers.TestHelper;

public class HashStringSet implements StringSet {

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

}
