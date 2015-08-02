package jstore.testhelpers.rivals.mafsa;

import java.util.Collection;

import jstore.Serializer;
import jstore.StringSet;
import jstore.implementations.BinaryMafsaSet;
import jstore.testhelpers.rivals.StringSetFactory;

public class SerializedBinaryMafsaSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    StringSet set = BinaryMafsaSet.create(strings);
    try {
      byte serialized[] = Serializer.serialize(set);
      return Serializer.deserialize(serialized);
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
