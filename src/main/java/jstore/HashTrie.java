package jstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.ArrayUtils;

public class HashTrie {

  public static HashTrie create(String[] strings) {
    HashTrie trie = new HashTrie();
    for (String s : strings) {
      trie.add(s);
    }

    return trie;
  }

  HashTrieState root;

  private HashTrie() {
    root = new HashTrieState();
  }

  public boolean add(String string) {
    HashTrieState cur = this.root;
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      HashTrieState child = cur.children.get(c);
      if (child == null) {
        child = new HashTrieState();
        cur.children.put(c, child);
      }

      cur = child;
    }

    boolean result = cur.isFinal;
    cur.isFinal = true;
    return result;
  }

  public String[] getStrings() {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder();
    this.root.iterateRecursive(builder, strings);
    return strings.toArray(new String[strings.size()]);
  }

  public ReadOnlyTrie toReadOnlyTrie() {
    List<Character> symbols = new ArrayList<Character>();
    List<HashTrieState> trieTransitions = new ArrayList<HashTrieState>();
    List<Integer> lower = new ArrayList<Integer>();
    List<Boolean> isFinal = new ArrayList<Boolean>();

    HashMap<HashTrieState, Integer> trieToState = new HashMap<HashTrieState, Integer>();

    Queue<HashTrieState> q = new LinkedBlockingQueue<HashTrieState>();
    q.add(this.root);
    while (!q.isEmpty()) {
      HashTrieState cur = q.remove();
      int stateIndex = lower.size();
      trieToState.put(cur, stateIndex);
      lower.add(trieTransitions.size());
      isFinal.add(cur.isFinal);
      for (Map.Entry<Character, HashTrieState> entry : cur.children.entrySet()) {
        symbols.add(entry.getKey());
        q.add(entry.getValue());
        trieTransitions.add(entry.getValue());
      }
    }

    int[] transitions = new int[trieTransitions.size()];
    for (int i = 0; i < transitions.length; i++) {
      transitions[i] = trieToState.get(trieTransitions.get(i));
    }

    ReadOnlyTrie readOnlyTrie = new ReadOnlyTrie();
    readOnlyTrie.lower = ArrayUtils.toPrimitive(lower.toArray(new Integer[0]));
    readOnlyTrie.symbols = ArrayUtils.toPrimitive(symbols.toArray(new Character[0]));
    readOnlyTrie.isFinal = ArrayUtils.toPrimitive(isFinal.toArray(new Boolean[0]));
    readOnlyTrie.transitions = transitions;
    return readOnlyTrie;
  }
}
