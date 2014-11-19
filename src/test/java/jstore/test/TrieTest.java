package jstore.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import jstore.Trie;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

  @Test
  public void memTest() throws IOException {
    String path = getClass().getClassLoader().getResource("Zaliznyak-1251.txt").getPath();
    List<String> data = Files.readAllLines(Paths.get(path), Charset.forName("Cp1251"));
    Collections.sort(data);

    // Runtime runtime = Runtime.getRuntime();
    // long before = runtime.totalMemory() - runtime.freeMemory();
    Trie trie = Trie.create(data.toArray(new String[data.size()]));
    // long after = runtime.totalMemory() - runtime.freeMemory();
    // System.out.println(after - before);

    String[] actual = trie.getStrings();
    Assert.assertArrayEquals("oops", data.toArray(), actual);
  }

  @Test
  public void testWTF() {
    String[] data = {"a", "aa", "ab", "abc"};
    Trie set = Trie.create(data);
    String[] actual = set.getStrings();

    Assert.assertArrayEquals("oops", data, actual);
  }

}
