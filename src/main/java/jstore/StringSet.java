package jstore;

import java.util.Collection;

public interface StringSet {
  boolean contains(String string);

  Collection<String> getAll();

  Collection<String> getByPrefix(String prefix);
}
