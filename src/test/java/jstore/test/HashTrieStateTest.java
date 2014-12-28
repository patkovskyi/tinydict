package jstore.test;

import jstore.HashTrieState;

import org.junit.Assert;
import org.junit.Test;

public class HashTrieStateTest {
  @Test
  public void abcTest() {
    HashTrieState x = new HashTrieState();
    HashTrieState y = new HashTrieState();
    HashTrieState z = new HashTrieState();

    x.children.put('a', z);
    x.children.put('b', y);
    y.children.put('b', y);
    y.children.put('a', z);

    Assert.assertEquals(x.hashCode(), y.hashCode());
    Assert.assertEquals(x, y);
  }
}
