package tinydict.testhelpers.rivals.mafsa;

import java.util.Collection;
import tinydict.StringSet;
import tinydict.implementations.BinaryMafsaSet;
import tinydict.testhelpers.rivals.StringSetFactory;

public class BinaryMafsaSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    return BinaryMafsaSet.create(strings);
  }
}
