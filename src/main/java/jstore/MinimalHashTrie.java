package jstore;

import java.util.ArrayList;
import java.util.TreeMap;


public class MinimalHashTrie {
  public static MinimalHashTrie fromHashTrie(HashTrie trie) {
    MinimalHashTrie minTrie = new MinimalHashTrie();
    minTrie.root = (HashTrieState) trie.root.clone();

    TreeMap<Integer, ArrayList<HashTrieState>> map =
        new TreeMap<Integer, ArrayList<HashTrieState>>();
    minTrie.root.levelRequrse(map);



    return minTrie;
  }

  HashTrieState root;
}
