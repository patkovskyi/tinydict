package jstore;

import java.util.Collection;

public interface StringSet {
  public boolean contains(String string);

  public Collection<String> getAll();

  public Collection<String> getByPrefix(String prefix);
}
