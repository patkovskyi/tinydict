package tinydict.testhelpers.rivals.mafsa;

import java.util.Collection;

import tinydict.Serializer;
import tinydict.StringSet;
import tinydict.implementations.LinearMafsaSet;
import tinydict.testhelpers.rivals.StringSetFactory;

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
