package jstore;

import java.util.ArrayList;
import java.util.List;

public class ReadOnlyTrie {

  public static ReadOnlyTrie create(String[] strings) {
    HashTrie hashTrie = HashTrie.create(strings);
    return hashTrie.toReadOnlyTrie();
  }

  char[] symbols;
  int[] transitions;
  int[] lower;

  boolean[] isFinal;

  ReadOnlyTrie() {}

  public String[] getStrings() {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder();
    recurse(0, builder, strings);
    return strings.toArray(new String[0]);
  }

  private int getUpper(int state) {
    return state + 1 < lower.length ? lower[state + 1] : transitions.length;
  }

  private void recurse(int state, StringBuilder sb, List<String> strings) {
    if (isFinal[state]) {
      strings.add(sb.toString());
    }

    int upper = getUpper(state);

    for (int i = lower[state]; i < upper; i++) {
      sb.append(symbols[i]);
      recurse(transitions[i], sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
