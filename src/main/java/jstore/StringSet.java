package jstore;

import java.util.List;

public interface StringSet {
  boolean contains(String string);

  List<String> getAll();

  List<String> getByPrefix(String prefix);

  Iterable<String> iterateAll();

  Iterable<String> iterateByPrefix(String prefix);
}
