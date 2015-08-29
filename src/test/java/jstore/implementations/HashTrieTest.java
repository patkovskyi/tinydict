package jstore.implementations;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jstore.testhelpers.TestFile;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class HashTrieTest {

  @Test
  public void abcMinimalTest() {
    String[] data = {"a", "aa", "ab", "abc"};
    HashTrieSet trie = HashTrieSet.create(data);
    System.out.println(trie.countStates() + " states before minimization");
    trie.minimize();
    System.out.println(trie.countStates() + " states after minimization");
    String[] actual = trie.getAll().toArray(new String[0]);

    Arrays.sort(actual);
    assertArrayEquals("oops", data, actual);
  }

  @Test
  public void abcTest() {
    String[] data = {"a", "aa", "ab", "abc"};
    HashTrieSet trie = HashTrieSet.create(data);
    String[] actual = trie.getAll().toArray(new String[0]);

    Arrays.sort(actual);
    assertArrayEquals("oops", data, actual);
  }

  @Test
  public void memMinimalTest() throws IOException {
    List<String> data = TestFile.ZALIZNYAK_FULL.readLines();
    Collections.sort(data);

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long before = runtime.totalMemory() - runtime.freeMemory();
    long startTime = System.nanoTime();
    HashTrieSet trie = HashTrieSet.create(data.toArray(new String[data.size()]));
    System.out.println("Time without minimization: " + (System.nanoTime() - startTime) / 1000000000.0);
    System.out.println(trie.countStates() + " states before minimization");
    System.out.println();

    startTime = System.nanoTime();
    trie.minimize();
    System.out.println("Minimization time: " + (System.nanoTime() - startTime) / 1000000000.0);
    System.out.println(trie.countStates() + " states after minimization");
    runtime.gc();
    long after = runtime.totalMemory() - runtime.freeMemory();
    System.out.println((after - before) / (1024 * 1024) + " mb");

    String[] actual = trie.getAll().toArray(new String[0]);
    Arrays.sort(actual);
    assertArrayEquals("oops", data.toArray(), actual);
  }

  @Test
  public void memTest() throws IOException {
    List<String> data = TestFile.ZALIZNYAK_FULL.readLines();
    Collections.sort(data);

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long before = runtime.totalMemory() - runtime.freeMemory();
    HashTrieSet trie = HashTrieSet.create(data.toArray(new String[data.size()]));
    runtime.gc();
    long after = runtime.totalMemory() - runtime.freeMemory();
    System.out.println((after - before) / (1024 * 1024) + " mb");

    String[] actual = trie.getAll().toArray(new String[0]);
    Arrays.sort(actual);
    assertArrayEquals("oops", data.toArray(), actual);
  }

}
