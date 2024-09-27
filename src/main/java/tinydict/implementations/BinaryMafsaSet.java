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

public class BinaryMafsaSet extends AbstractDafsa<Integer> implements Serializable {

  private static final long serialVersionUID = 1L;

  int[] states;
  int[] transitions;
  char[] symbols;

  private static final int FINAL_MASK = 1 << 31;

  private static final int CLEAR_MASK = ~FINAL_MASK;

  public static BinaryMafsaSet create(Collection<String> strings) {
    Helper.verifyStringCollection(strings);
    return create(strings.toArray(new String[0]));
  }

  public static BinaryMafsaSet create(String[] strings) {
    HashTrieSet ht = HashTrieSet.create(strings);
    ht.minimize();
    return from(ht);
  }

  public static <TState> BinaryMafsaSet from(AbstractDafsa<TState> dafsa) {
    Queue<TState> statesToVisit = new ArrayDeque<TState>();
    statesToVisit.add(dafsa.getRootState());

    Map<TState, Integer> assignedStateIndexes = new HashMap<TState, Integer>();
    assignedStateIndexes.put(dafsa.getRootState(), 0);

    List<Integer> states = new ArrayList<Integer>();
    List<TState> transitions = new ArrayList<TState>();
    List<Character> symbols = new ArrayList<Character>();

    while (!statesToVisit.isEmpty()) {
      TState currentState = statesToVisit.remove();

      PriorityQueue<Pair<Character, TState>> currentStateTransitions =
          new PriorityQueue<Pair<Character, TState>>();
      for (Pair<Character, TState> transition : dafsa.iterateDirectTransitions(currentState)) {
        currentStateTransitions.add(transition);

        TState nextState = transition.getSecond();
        if (!assignedStateIndexes.containsKey(nextState)) {
          assignedStateIndexes.put(nextState, assignedStateIndexes.size());
          statesToVisit.add(nextState);
        }
      }

      int lowerTransitionIndex = transitions.size();
      if (dafsa.isFinal(currentState)) {
        lowerTransitionIndex = lowerTransitionIndex | FINAL_MASK;
      }
      states.add(lowerTransitionIndex);

      while (!currentStateTransitions.isEmpty()) {
        Pair<Character, TState> child = currentStateTransitions.poll();
        transitions.add(child.getSecond());
        symbols.add(child.getFirst());
      }
    }

    BinaryMafsaSet mafsa = new BinaryMafsaSet();
    mafsa.states = new int[states.size()];
    mafsa.symbols = new char[symbols.size()];
    mafsa.transitions = new int[transitions.size()];

    for (int i = 0; i < states.size(); i++) {
      mafsa.states[i] = states.get(i);
    }

    for (int i = 0; i < transitions.size(); i++) {
      mafsa.symbols[i] = symbols.get(i);
      mafsa.transitions[i] = assignedStateIndexes.get(transitions.get(i));
    }

    return mafsa;
  }

  private int getLower(int state) {
    return states[state] & CLEAR_MASK;
  }

  private int getUpper(int state) {
    if (state + 1 == states.length)
      return transitions.length;

    return states[state + 1] & CLEAR_MASK;
  }

  @Override
  protected Integer getNextState(Integer state, char symbol) {
    int upper = getUpper(state);

    for (int i = getLower(state); i < upper; i++) {
      if (symbols[i] >= symbol) {
        if (symbols[i] == symbol)
          return transitions[i];
        else
          return null;
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
    return (states[state] & FINAL_MASK) != 0;
  }

  @Override
  protected Iterable<Pair<Character, Integer>> iterateDirectTransitions(final Integer state) {
    return new Iterable<Pair<Character, Integer>>() {

      @Override
      public Iterator<Pair<Character, Integer>> iterator() {
        Iterator<Pair<Character, Integer>> it = new Iterator<Pair<Character, Integer>>() {

          private int current = getLower(state);
          private int upper = getUpper(state);

          @Override
          public boolean hasNext() {
            return current < upper;
          }

          @Override
          public Pair<Character, Integer> next() {
            if (!hasNext())
              throw new NoSuchElementException();
            return Pair.of(symbols[current], transitions[current++]);
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
