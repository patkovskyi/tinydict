package jstore.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jstore.Messages;
import jstore.StringSet;

import org.apache.commons.lang3.tuple.Pair;

abstract class AbstractDafsa<TState> implements StringSet {
  @Override
  public boolean contains(String string) {
    if (string == null) {
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
    }

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
      for (Pair<Character, TState> child : iterateDirectTransitions(cur)) {
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
    return getAll("", getRootState());
  }

  protected Collection<String> getAll(String prefix, TState fromState) {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder(prefix);
    this.iterateRecursive(fromState, builder, strings);
    return strings;
  }

  @Override
  public Collection<String> getByPrefix(String prefix) {
    Helper.verifyInputString(prefix);

    TState state = getRootState();
    for (int i = 0; i < prefix.length(); i++) {
      state = getNextState(state, prefix.charAt(i));
      if (state == null) {
        return Collections.emptyList();
      }
    }

    return getAll(prefix, state);
  }

  protected abstract TState getNextState(TState state, char symbol);

  protected abstract TState getRootState();

  protected abstract boolean isFinal(TState state);

  protected abstract Iterable<Pair<Character, TState>> iterateDirectTransitions(TState fromState);

  protected void iterateRecursive(TState state, StringBuilder sb, List<String> strings) {
    if (isFinal(state)) {
      strings.add(sb.toString());
    }

    for (Pair<Character, TState> entry : this.iterateDirectTransitions(state)) {
      sb.append(entry.getKey());
      iterateRecursive(entry.getValue(), sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
