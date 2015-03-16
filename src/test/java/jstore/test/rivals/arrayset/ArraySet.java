package jstore.test.rivals.arrayset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import jstore.Messages;
import jstore.StringSet;

public class ArraySet implements StringSet {

  private String[] array;

  private final static Comparator<String> comparator = new StringSensitiveComparator();

  public ArraySet(Collection<String> strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);

    if (strings.contains(null))
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);

    array = strings.toArray(new String[0]);
  }

  @Override
  public boolean contains(String string) {
    if (string == null) {
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
    }

    int index = Arrays.binarySearch(array, string, comparator);
    return index >= 0;
  }

  @Override
  public Collection<String> getAll() {
    return Arrays.asList(array);
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
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
}
