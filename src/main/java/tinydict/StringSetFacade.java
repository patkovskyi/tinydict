package tinydict;

import java.io.IOException;
import java.util.List;

import tinydict.implementations.BinaryMafsaSet;

public class StringSetFacade {

  StringSet instance;

  private StringSetFacade(StringSet instance){
    this.instance = instance;
  }

  public static StringSetFacade create(String[] strings) {
    return new StringSetFacade(BinaryMafsaSet.create(strings));
  }

  public static StringSetFacade create(List<String> strings) {
    return new StringSetFacade(BinaryMafsaSet.create(strings));
  }

  public static StringSetFacade deserialize(byte[] serialized)
      throws IOException, ClassNotFoundException {
    return new StringSetFacade(Serializer.deserialize(serialized));
  }

  public byte[] serialize() throws IOException {
    return Serializer.serialize(instance);
  }

  public boolean contains(String string){
    return instance.contains(string);
  }

  public List<String> getAll() {
    return instance.getAll();
  }

  public List<String> getByPrefix(String prefix){
    return instance.getByPrefix(prefix);
  }

  public Iterable<String> iterateAll() {
    return instance.iterateAll();
  }

  public Iterable<String> iterateByPrefix(String prefix) {
    return instance.iterateByPrefix(prefix);
  }
}
