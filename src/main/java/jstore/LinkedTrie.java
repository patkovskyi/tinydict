package jstore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedTrie {
  final class TrieState {
    boolean isFinal;
    OrderedList<Character> symbols;
    LinkedList<TrieState> transitions;

    TrieState() {
      isFinal = false;
      symbols = new OrderedList<Character>();
      transitions = new LinkedList<TrieState>();
    }

    void recurse(StringBuilder sb, List<String> strings) {
      if (isFinal) {
        strings.add(sb.toString());
      }

      ListIterator<Character> symbolIterator = symbols.listIterator();
      ListIterator<TrieState> transitionIterator = transitions.listIterator();

      while (symbolIterator.hasNext()) {
        sb.append(symbolIterator.next());
        transitionIterator.next().recurse(sb, strings);
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }

  public static LinkedTrie create(String[] strings) {
    LinkedTrie trie = new LinkedTrie();
    trie.init(strings);
    return trie;
  }

  private LinkedList<TrieState> states;

  public LinkedTrie() {
    states = new LinkedList<TrieState>();
    states.add(new TrieState());
  }

  public boolean add(String s) {
    TrieState state = states.getFirst();
    for (int j = 0; j < s.length(); j++) {
      state = findOrCreateNextState(state, s.charAt(j));
    }

    if (state.isFinal)
      return false;

    state.isFinal = true;
    return true;
  }

  private TrieState findOrCreateNextState(TrieState state, char symbol) {
    int symbolIndex = state.symbols.orderedAdd(symbol);
    if (symbolIndex < 0) {
      TrieState newState = new TrieState();
      state.transitions.add(~symbolIndex, newState);
      return newState;
    }

    return state.transitions.get(symbolIndex);
  }

  public String[] getStrings() {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder();
    states.getFirst().recurse(builder, strings);
    return strings.toArray(new String[strings.size()]);
  }

  private void init(String[] strings) {
    for (int i = 0; i < strings.length; i++) {
      // TODO: check for duplicates?
      add(strings[i]);
    }
  }

  public boolean remove(String s) {
    TrieState state = states.getFirst();
    TrieState removalRoot = null;
    int removalIndex = 0;

    for (int i = 0; i < s.length(); ++i) {
      int symbolIndex = state.symbols.indexOf(s.charAt(i));
      if (symbolIndex < 0) {
        return false;
      }

      if (state.symbols.size() > 1 || state.isFinal || removalRoot == null) {
        removalRoot = state;
        removalIndex = symbolIndex;
      }

      state = state.transitions.get(symbolIndex);
    }

    if (!state.isFinal) {
      return false;
    }

    state.isFinal = false;
    if (state.symbols.isEmpty() && removalRoot != null) {
      removalRoot.symbols.remove(removalIndex);
      removalRoot.transitions.remove(removalIndex);
    }

    return true;
  }
}
