package jstore.fasttest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jstore.Serializer;
import jstore.StringSet;
import jstore.implementations.LinearMafsaSet;
import jstore.testhelpers.TestHelper;

import org.junit.Assert;
import org.junit.Test;

public class Serialization {

  @Test
  public void abcTest() {
    System.out.println(TestHelper.getStringPath("Zal.ser"));

    String[] data = {"a", "aa", "ab", "abc"};
    LinearMafsaSet trie = LinearMafsaSet.create(data);
    String[] actual = trie.getAll().toArray(new String[0]);

    Arrays.sort(actual);
    Assert.assertArrayEquals("oops", data, actual);
  }

  @Test
  public void javaSerializationTest() throws IOException, ClassNotFoundException {
    String path = getClass().getClassLoader().getResource("Zaliznyak-1251.txt").getPath();
    List<String> data = Files.readAllLines(Paths.get(path), Charset.forName("Cp1251"));
    Collections.sort(data);

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long before = runtime.totalMemory() - runtime.freeMemory();
    StringSet trie = LinearMafsaSet.create(data.toArray(new String[data.size()]));
    runtime.gc();
    long after = runtime.totalMemory() - runtime.freeMemory();
    System.out.println((after - before) / 1024 + " kb");

    long start = System.nanoTime();
    byte[] serialized = Serializer.serialize(trie);
    long serializationTime = System.nanoTime() - start;
    System.out.println("Java serialized: " + serialized.length + " bytes in " + serializationTime
        / 1000000 + " ms.");
    Path filepath = Paths.get("serialized.ser");
    Files.write(filepath, serialized, StandardOpenOption.CREATE);
    Serializer.serialize(trie, filepath.toString());
    start = System.nanoTime();
    trie = Serializer.deserialize(Files.readAllBytes(filepath));
    long deserializationTime = System.nanoTime() - start;
    System.out.println("Java deserialized in " + deserializationTime / 1000000 + " ms.");
    String[] actual = trie.getAll().toArray(new String[0]);
    Arrays.sort(actual);
    Assert.assertArrayEquals("oops", data.toArray(), actual);
  }

  @Test
  public void memoryTest() throws IOException {
    String path = getClass().getClassLoader().getResource("Zaliznyak-1251.txt").getPath();
    List<String> data = Files.readAllLines(Paths.get(path), Charset.forName("Cp1251"));
    Collections.sort(data);

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long before = runtime.totalMemory() - runtime.freeMemory();
    LinearMafsaSet trie = LinearMafsaSet.create(data.toArray(new String[data.size()]));
    runtime.gc();
    long after = runtime.totalMemory() - runtime.freeMemory();
    System.out.println((after - before) / 1024 + " kb");

    String[] actual = trie.getAll().toArray(new String[0]);
    Arrays.sort(actual);
    Assert.assertArrayEquals("oops", data.toArray(), actual);
  }
}
