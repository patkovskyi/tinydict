package jstore.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class HashTrieState implements Cloneable {
  HashMap<Character, HashTrieState> children;
  boolean isFinal;

  HashTrieState() {
    children = new HashMap<Character, HashTrieState>();
    isFinal = false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object clone() {
    HashTrieState clone = new HashTrieState();
    clone.isFinal = isFinal;
    clone.children = (HashMap<Character, HashTrieState>) children.clone();
    return clone;
  };

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof HashTrieState) {
      final HashTrieState other = (HashTrieState) obj;
      boolean keyEquals =
          new EqualsBuilder().append(isFinal, other.isFinal)
          .append(children.keySet(), other.children.keySet()).isEquals();
      if (keyEquals) {
        for (Entry<Character, HashTrieState> e : children.entrySet()) {
          if (e.getValue() != other.children.get(e.getKey()))
            return false;
        }

        return true;
      }
    }

    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(isFinal).append(children.keySet()).toHashCode();
  }

  int levelRequrse(TreeMap<Integer, ArrayList<HashTrieState>> pq) {
    int max = 0;
    for (HashTrieState e : children.values()) {
      int cur = e.levelRequrse(pq);
      if (cur > max)
        max = cur;
    }

    if (!pq.containsKey(max))
      pq.put(max, new ArrayList<HashTrieState>());
    pq.get(max).add(this);
    return max;
  }

  void iterateRecursive(StringBuilder sb, List<String> strings) {
    if (isFinal) {
      strings.add(sb.toString());
    }

    for (Map.Entry<Character, HashTrieState> entry : children.entrySet()) {
      sb.append(entry.getKey());
      entry.getValue().iterateRecursive(sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
