package jstore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public abstract class AbstractTrie implements ITrie {
  @Override
  public boolean contains(String string) {
    AbstractTrie cur = this;
    for (int i = 0; i < string.length(); i++) {
      cur = cur.getNext(string.charAt(i));
      if (cur == null) {
        return false;
      }
    }

    return cur.isFinal();
  }

  @Override
  public Collection<String> getAll() {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder();
    this.iterateRecursive(builder, strings);
    return strings;
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
    AbstractTrie cur = this;
    for (int i = 0; i < prefix.length(); i++) {
      cur = cur.getNext(prefix.charAt(i));
      if (cur == null) {
        return null;
      }
    }

    return cur.getAll();
  }

  abstract AbstractTrie getNext(char symbol);

  abstract AbstractSignature getSignature();

  abstract boolean isFinal();

  abstract Iterable<Pair<Character, AbstractTrie>> iterate();

  void iterateRecursive(StringBuilder sb, List<String> strings) {
    if (isFinal()) {
      strings.add(sb.toString());
    }

    for (Pair<Character, AbstractTrie> entry : this.iterate()) {
      sb.append(entry.getKey());
      entry.getValue().iterateRecursive(sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
