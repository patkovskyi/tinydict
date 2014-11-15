package jstore.test;

import jstore.Trie;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

  @Test
  public void test() {
    String[] data = {"a", "aa", "ab", "abc"};
    Trie set = Trie.create(data);
    String[] actual = set.getStrings();

    Assert.assertArrayEquals("oops", data, actual);
  }

}
