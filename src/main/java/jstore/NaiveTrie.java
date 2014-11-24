package jstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveTrie {

  public static NaiveTrie create(String[] strings) {
    NaiveTrie trie = new NaiveTrie();
    trie.init(strings);
    return trie;
  }

  private TreeMap<Character, NaiveTrie> children;
  private boolean isFinal;

  private NaiveTrie() {
    children = new TreeMap<Character, NaiveTrie>();
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
      NaiveTrie cur = this;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        NaiveTrie child = cur.children.get(c);
        if (child == null) {
          child = new NaiveTrie();
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

    for (Map.Entry<Character, NaiveTrie> entry : children.entrySet()) {
      sb.append(entry.getKey());
      entry.getValue().recurse(sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
