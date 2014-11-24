package jstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
}
