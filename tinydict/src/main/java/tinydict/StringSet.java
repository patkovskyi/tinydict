package tinydict;

import java.io.Serializable;
import java.util.List;

public interface StringSet extends Serializable {
  boolean contains(String string);

  List<String> getAll();

  List<String> getByPrefix(String prefix);

  Iterable<String> iterateAll();

  Iterable<String> iterateByPrefix(String prefix);
}
