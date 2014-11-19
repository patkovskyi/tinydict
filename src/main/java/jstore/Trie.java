package jstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Trie {

  public static Trie create(String[] strings) {
    Trie trie = new Trie();
    trie.init(strings);
    return trie;
  }

  private TreeMap<Character, Trie> children;
  private boolean isFinal;

  private Trie() {
    children = new TreeMap<Character, Trie>();
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
      Trie cur = this;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        Trie child = cur.children.get(c);
        if (child == null) {
          child = new Trie();
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

    for (Map.Entry<Character, Trie> entry : children.entrySet()) {
      sb.append(entry.getKey());
      entry.getValue().recurse(sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
