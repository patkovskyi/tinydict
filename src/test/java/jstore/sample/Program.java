package jstore.sample;

import java.io.IOException;
import java.util.Arrays;

import jstore.Serializer;
import jstore.StringSet;
import jstore.implementations.MafsaSet;

public class Program {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    System.out.println("Hello, world!");
    StringSet set = MafsaSet.create(Arrays.asList("abc", "ab"));
    byte[] data = Serializer.serialize(set);
    set = Serializer.deserialize(data);
    for (String s : set.getAll()) {
      System.out.println(s);
    }
  }
}
