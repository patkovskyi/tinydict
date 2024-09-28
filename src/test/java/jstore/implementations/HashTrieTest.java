package jstore.implementations;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jstore.testhelpers.My;
import jstore.testhelpers.TestFile;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class HashTrieTest {

  @Test
  public void abcTest() {
    String[] data = {"a", "aa", "ab", "abc"};
    HashTrieSet trie = HashTrieSet.create(data);
    String[] actual = trie.getAll().toArray(new String[0]);

    My.assertArraysEquivalent(actual, data);
    Assert.assertEquals(5, trie.countStates());
  }

  @Test
  public void abcMinimalTest() {
    String[] data = {"a", "aa", "ab", "abc"};
    HashTrieSet trie = HashTrieSet.create(data);
    trie.minimize();
    String[] actual = trie.getAll().toArray(new String[0]);

    My.assertArraysEquivalent(actual, data);
    Assert.assertEquals(4, trie.countStates());
  }

  @Test
  public void largeMemoryTest() throws IOException {
    Runtime env = Runtime.getRuntime();
    System.out.printf("Max heap size: %d bytes.%n", env.maxMemory());

    List<String> data = TestFile.ZALIZNYAK_FULL.readLines();
    HashTrieSet trie = HashTrieSet.create(data.toArray(new String[0]));

    Monitor mon = MonitorFactory.start("trie.getAll()");
    List<String> actual = trie.getAll();
    mon.stop();
    System.out.println(mon);

    mon = MonitorFactory.start("assertCollectionsEquivalent(actual, data)");
    My.assertCollectionsEquivalent(actual, data);
    mon.stop();
    System.out.println(mon);

    mon = MonitorFactory.start("assertEquals(2531993, trie.countStates())");
    My.assertEquals(2531993, trie.countStates());
    mon.stop();
    System.out.println(mon);
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

}
