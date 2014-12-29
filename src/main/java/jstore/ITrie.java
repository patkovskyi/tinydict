package jstore;

import java.util.Collection;


public interface ITrie {
  public boolean contains(String string);

  public Collection<String> getAll();

  public Collection<String> getByPrefix(String prefix);
}
