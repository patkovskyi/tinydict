package jstore.testhelpers.rivals.mafsa;

import java.util.Collection;

import jstore.Serializer;
import jstore.StringSet;
import jstore.implementations.BinaryMafsaSet;
import jstore.implementations.LinearMafsaSet;
import jstore.testhelpers.rivals.StringSetFactory;

public class SerializedLinearMafsaSetFactory extends StringSetFactory {

  @Override
  public StringSet create(Collection<String> strings) {
    StringSet set = LinearMafsaSet.create(strings);
    try {
      byte serialized[] = Serializer.serialize(set);
      return Serializer.deserialize(serialized);
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
