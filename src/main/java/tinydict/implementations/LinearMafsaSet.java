package tinydict.implementations;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

public class LinearMafsaSet extends AbstractDafsa<Integer> implements Serializable {

  public static LinearMafsaSet create(Collection<String> strings) {
    Helper.verifyStringCollection(strings);
    return create(strings.toArray(new String[0]));
  }

  public static LinearMafsaSet create(String[] strings) {
    HashTrieSet ht = HashTrieSet.create(strings);
    ht.minimize();
    return from(ht);
  }

  public static <TState> LinearMafsaSet from(AbstractDafsa<TState> trie) {
    List<TState> transitions = new ArrayList<TState>();
    List<Character> symbols = new ArrayList<Character>();

    Queue<TState> toVisit = new ArrayDeque<TState>();
    Map<TState, Integer> visited = new HashMap<TState, Integer>();

    TState root = trie.getRootState();
    toVisit.add(root);
    visited.put(root, 0);

    while (!toVisit.isEmpty()) {
      TState cur = toVisit.remove();
      int size = transitions.size();

      PriorityQueue<Pair<Character, TState>> queue = new PriorityQueue<Pair<Character, TState>>();
      for (Pair<Character, TState> child : trie.iterateDirectTransitions(cur)) {
        TState next = child.getSecond();
        queue.add(child);

        if (!visited.containsKey(next)) {
          visited.put(next, 0);
          toVisit.add(next);
        }
      }

      while (!queue.isEmpty()) {
        Pair<Character, TState> child = queue.poll();
        transitions.add(child.getSecond());
        symbols.add(child.getFirst());
      }

      if (transitions.size() > size) {
        visited.put(cur, size);
      }
    }

    LinearMafsaSet ss = new LinearMafsaSet();
    ss.symbols = new char[transitions.size()];
    ss.transitions = new int[transitions.size()];
    ss.isRootStateFinal = trie.isFinal(root);

    if (transitions.size() > 0) {
      ss.transitions[visited.get(root)] |= FIRST_MASK | (trie.isFinal(root) ? FINAL_MASK : 0);
      for (int i = 0; i < transitions.size(); i++) {
        ss.symbols[i] = symbols.get(i);

        TState state = transitions.get(i);
        int stateIndex = visited.get(state);

        if (stateIndex > 0) {
          ss.transitions[stateIndex] |= FIRST_MASK | (trie.isFinal(state) ? FINAL_MASK : 0);
        } else {
          stateIndex = transitions.size();
        }

        ss.transitions[i] |= stateIndex;
      }
    }

    return ss;
  }

  private static final long serialVersionUID = 1L;

  int[] transitions;

  char[] symbols;

  boolean isRootStateFinal;

  private static final int FINAL_MASK = 1 << 31;

  private static final int FIRST_MASK = 1 << 30;

  private static final int CLEAR_MASK = ~(FIRST_MASK | FINAL_MASK);

  @Override
  protected Integer getNextState(Integer state, char symbol) {
    for (int i = state; i < symbols.length && (i == state || !isFirst(i)); i++) {
      if (symbols[i] >= symbol) {
        if (symbols[i] == symbol) return transitions[i] & CLEAR_MASK;
        else return null;
      }
    }

    return null;
  }

  @Override
  protected Integer getRootState() {
    return 0;
  }

  @Override
  protected boolean isFinal(Integer state) {
    if (state == 0) return isRootStateFinal;

    return state == transitions.length || (transitions[state] & FINAL_MASK) != 0;
  }

  boolean isFirst(int state) {
    return (transitions[state] & FIRST_MASK) != 0;
  }

  @Override
  protected Iterable<Pair<Character, Integer>> iterateDirectTransitions(final Integer state) {
    return new Iterable<Pair<Character, Integer>>() {

      @Override
      public Iterator<Pair<Character, Integer>> iterator() {
        Iterator<Pair<Character, Integer>> it =
            new Iterator<Pair<Character, Integer>>() {

              private int current = state;

              @Override
              public boolean hasNext() {
                return (current < transitions.length) && ((current == state) || !isFirst(current));
              }

              @Override
              public Pair<Character, Integer> next() {
                if (!hasNext()) throw new NoSuchElementException();
                return Pair.of(symbols[current], transitions[current++] & CLEAR_MASK);
              }

              @Override
              public void remove() {
                throw new UnsupportedOperationException();
              }
            };

        return it;
      }
    };
  }
}
