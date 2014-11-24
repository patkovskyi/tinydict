package jstore;

import java.util.LinkedList;
import java.util.ListIterator;

@SuppressWarnings("serial")
class OrderedList<T extends Comparable<T>> extends LinkedList<T> {
  public int orderedAdd(T element) {
    ListIterator<T> itr = listIterator();
    for (int index = 0;; ++index) {
      if (itr.hasNext() == false) {
        itr.add(element);
        return ~index;
      }

      T elementInList = itr.next();
      int comp = elementInList.compareTo(element);
      if (comp == 0)
        return index;

      if (comp > 0) {
        itr.previous();
        itr.add(element);
        return ~index;
      }
    }
  }
}
