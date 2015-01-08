package jstore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.tuple.Pair;

public abstract class AbstractTrie<TState> implements ITrie {
  @Override
  public boolean contains(String string) {
    TState state = getRootState();
    for (int i = 0; i < string.length(); i++) {
      state = getNextState(state, string.charAt(i));
      if (state == null) {
        return false;
      }
    }

    return isFinal(state);
  }

  public int countStates() {
    HashSet<TState> visited = new HashSet<TState>();
    Queue<TState> toVisit = new LinkedList<TState>();

    int counter = 0;
    toVisit.add(getRootState());
    visited.add(getRootState());
    while (!toVisit.isEmpty()) {
      ++counter;
      TState cur = toVisit.poll();
      for (Pair<Character, TState> child : iterate(cur)) {
        TState next = child.getValue();
        if (!visited.contains(next)) {
          visited.add(next);
          toVisit.add(next);
        }
      }
    }

    return counter;
  }

  @Override
  public Collection<String> getAll() {
    return getAll(getRootState());
  }

  Collection<String> getAll(TState fromState) {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder();
    this.iterateRecursive(fromState, builder, strings);
    return strings;
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
    TState state = getRootState();
    for (int i = 0; i < prefix.length(); i++) {
      state = getNextState(state, prefix.charAt(i));
      if (state == null) {
        return null;
      }
    }

    return getAll(state);
  }

  abstract TState getNextState(TState state, char symbol);

  abstract TState getRootState();

  abstract boolean isFinal(TState state);

  abstract Iterable<Pair<Character, TState>> iterate(TState state);

  void iterateRecursive(TState state, StringBuilder sb, List<String> strings) {
    if (isFinal(state)) {
      strings.add(sb.toString());
    }

    for (Pair<Character, TState> entry : this.iterate(state)) {
      sb.append(entry.getKey());
      iterateRecursive(entry.getValue(), sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
