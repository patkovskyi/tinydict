package jstore.testhelpers.rivals.arrayset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import jstore.StringSet;
import jstore.testhelpers.TestHelper;

public class ArrayStringSet implements StringSet, Serializable {

  private static final long serialVersionUID = 1L;

  private String[] array;

  private final static Comparator<String> comparator = new CaseSensitiveComparator();

  public ArrayStringSet(Collection<String> strings) {
    TestHelper.verifyStringCollection(strings);
    array = strings.toArray(new String[0]);
    Arrays.sort(array);
  }

  @Override
  public boolean contains(String string) {
    TestHelper.verifyInputString(string);

    int index = Arrays.binarySearch(array, string, comparator);
    return index >= 0;
  }

  @Override
  public Collection<String> getAll() {
    return Arrays.asList(array);
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
    TestHelper.verifyInputString(prefix);

    Collection<String> result = new ArrayList<String>();

    int index = Arrays.binarySearch(array, prefix, comparator);
    if (index < 0) {
      index = -index - 1;
    }

    for (int i = index; i < array.length; i++) {
      if (array[i].startsWith(prefix)) {
        result.add(array[i]);
      } else {
        break;
      }
    }

    return result;
  }

  @Override
  public Iterable<String> iterateAll() {
    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        return new Iterator<String>() {
          private int pos = 0;

          @Override
          public boolean hasNext() {
            return pos < array.length;
          }

          @Override
          public String next() {
            return array[pos++];
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException("Cannot remove an element of an array.");
          }
        };
      }
    };
  }

  @Override
  public Iterable<String> iterateByPrefix(final String prefix) {
    TestHelper.verifyInputString(prefix);

    int binIndex = Arrays.binarySearch(array, prefix, comparator);
    final int index = binIndex < 0 ? -binIndex - 1 : binIndex;

    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        return new Iterator<String>() {
          private int pos = index;

          @Override
          public boolean hasNext() {
            return pos < array.length && array[pos].startsWith(prefix);
          }

          @Override
          public String next() {
            return array[pos++];
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException("Cannot remove an element of an array.");
          }
        };
      }
    };
  }
}
