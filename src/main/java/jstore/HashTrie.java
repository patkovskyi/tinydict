package jstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

public class HashTrie {

  public static HashTrie create(String[] strings) {
    HashTrie trie = new HashTrie();
    trie.init(strings);
    return trie;
  }

  private TreeMap<Character, HashTrie> children;
  private boolean isFinal;

  private HashTrie() {
    children = new TreeMap<Character, HashTrie>();
    isFinal = false;
  }

  public String[] getStrings() {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder();
    recurse(builder, strings);
    return strings.toArray(new String[strings.size()]);
  }

  private void init(String[] strings) {
    for (String s : strings) {
      HashTrie cur = this;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        HashTrie child = cur.children.get(c);
        if (child == null) {
          child = new HashTrie();
          cur.children.put(c, child);
        }

        cur = child;
      }

      cur.isFinal = true;
    }
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

  private boolean[] toBooleanArray(List<Boolean> list) {
    boolean[] ret = new boolean[list.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = list.get(i);
    return ret;
  }

  private char[] toCharArray(List<Character> list) {
    char[] ret = new char[list.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = list.get(i);
    return ret;
  }

  private int[] toIntArray(List<Integer> list) {
    int[] ret = new int[list.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = list.get(i);
    return ret;
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
    readOnlyTrie.lower = toIntArray(lower);
    readOnlyTrie.symbols = toCharArray(symbols);
    readOnlyTrie.isFinal = toBooleanArray(isFinal);
    readOnlyTrie.transitions = transitions;
    return readOnlyTrie;
  }
}
