package jstore;

import java.util.List;

public class StringSetFacade {

  public static StringSet create(String[] strings) {
    throw new UnsupportedOperationException();
  }

  public static StringSet create(List<String> strings) {
    throw new UnsupportedOperationException();
  }

  public static StringSet deserialize(byte[] serialized) {
    throw new UnsupportedOperationException();
  }

  public byte[] serialize(){
    throw new UnsupportedOperationException();
  }

  public boolean contains(String string){
    throw new UnsupportedOperationException();
  }

  public List<String> getAll() {
    throw new UnsupportedOperationException();
  }

  public List<String> getByPrefix(String prefix){
    throw new UnsupportedOperationException();
  }

  public Iterable<String> iterateAll() {
    throw new UnsupportedOperationException();
  }

  public Iterable<String> iterateByPrefix(String prefix) {
    throw new UnsupportedOperationException();
  }
}