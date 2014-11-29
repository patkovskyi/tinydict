package jstore.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import jstore.ReadOnlyTrie;

import org.junit.Assert;
import org.junit.Test;

public class ReadOnlyTrieTest {
  @Test
  public void abcTest() {
    String[] data = {"a", "aa", "ab", "abc"};
    ReadOnlyTrie trie = ReadOnlyTrie.create(data);
    String[] actual = trie.getStrings();

    Assert.assertArrayEquals("oops", data, actual);
  }

  @Test
  public void memTest() throws IOException {
    String path = getClass().getClassLoader().getResource("Zaliznyak-1251.txt").getPath();
    List<String> data = Files.readAllLines(Paths.get(path), Charset.forName("Cp1251"));
    Collections.sort(data);

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long before = runtime.totalMemory() - runtime.freeMemory();
    ReadOnlyTrie trie = ReadOnlyTrie.create(data.toArray(new String[data.size()]));
    runtime.gc();
    long after = runtime.totalMemory() - runtime.freeMemory();
    System.out.println((after - before) / (1024 * 1024) + " mb");

    String[] actual = trie.getStrings();
    Assert.assertArrayEquals("oops", data.toArray(), actual);
  }
}
