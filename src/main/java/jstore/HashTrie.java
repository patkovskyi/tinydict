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

  private HashMap<Character, HashTrie> children;
  private boolean isFinal;

  private HashTrie() {
    children = new HashMap<Character, HashTrie>();
    isFinal = false;
  }

  public boolean add(String string) {
    HashTrie cur = this;
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      HashTrie child = cur.children.get(c);
      if (child == null) {
        child = new HashTrie();
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
    recurse(builder, strings);
    return strings.toArray(new String[strings.size()]);
  }

  private void recurse(StringBuilder sb, List<String> strings) {
    if (isFinal) {
      strings.add(sb.toString());
    }

    for (Map.Entry<Character, HashTrie> entry : children.entrySet()) {
      sb.append(entry.getKey());
      entry.getValue().recurse(sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  public ReadOnlyTrie toReadOnlyTrie() {
    List<Character> symbols = new ArrayList<Character>();
    List<HashTrie> trieTransitions = new ArrayList<HashTrie>();
    List<Integer> lower = new ArrayList<Integer>();
    List<Boolean> isFinal = new ArrayList<Boolean>();

    HashMap<HashTrie, Integer> trieToState = new HashMap<HashTrie, Integer>();

    Queue<HashTrie> q = new LinkedBlockingQueue<HashTrie>();
    q.add(this);
    while (!q.isEmpty()) {
      HashTrie cur = q.remove();
      int stateIndex = lower.size();
      trieToState.put(cur, stateIndex);
      lower.add(trieTransitions.size());
      isFinal.add(cur.isFinal);
      for (Map.Entry<Character, HashTrie> entry : cur.children.entrySet()) {
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
