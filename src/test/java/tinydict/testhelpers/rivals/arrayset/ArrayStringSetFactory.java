package tinydict.testhelpers.rivals.arrayset;

import java.util.Collection;
import tinydict.StringSet;
import tinydict.testhelpers.rivals.StringSetFactory;

public class ArrayStringSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return new ArrayStringSet(strings);
  }
}
