package jstore.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import jstore.StringSet;

abstract class AbstractDafsa<TState> implements StringSet {
  @Override
  public boolean contains(String string) {
    Helper.verifyInputString(string);

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
        TState next = child.getSecond();
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

  @Override
  public Iterable<String> iterateAll() {
    return new Iterable<String>() {

      @Override
      public Iterator<String> iterator() {
        return new Iterator<String>() {

          Stack<TState> stack;
          Stack<Iterator<Pair<Character, TState>>> stack2;
          StringBuilder sb;
          boolean alreadyAdvanced = isFinal(getRootState());

          {
            sb = new StringBuilder();

            stack = new Stack<TState>();
            stack2 = new Stack<Iterator<Pair<Character, TState>>>();

            stack.push(getRootState());
            stack2.push(iterateDirectTransitions(getRootState()).iterator());
          }

          private void advanceToNextFinalState() {
            do {
              Iterator<Pair<Character, TState>> topIterator = stack2.peek();
              if (topIterator.hasNext()) {
                Pair<Character, TState> nextTransition = topIterator.next();
                TState nextState = nextTransition.getSecond();

                sb.append(nextTransition.getFirst());
                stack.push(nextState);
                stack2.push(iterateDirectTransitions(nextState).iterator());

                if (isFinal(nextState)) {
                  return;
                }
              } else {
                if (sb.length() > 0) {
                  sb.deleteCharAt(sb.length() - 1);
                }

                stack.pop();
                stack2.pop();
              }
            } while (!stack.isEmpty());
          }

          @Override
          public boolean hasNext() {
            if (!alreadyAdvanced) {
              advanceToNextFinalState();
              alreadyAdvanced = true;
            }

            return !stack.isEmpty() && isFinal(stack.peek());
          }

          @Override
          public String next() {
            if (hasNext()) {
              alreadyAdvanced = false;
              return sb.toString();
            } else {
              throw new NoSuchElementException();
            }
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException("Implement me");
          }

        };
      }
    };
  }

  @Override
  public Iterable<String> iterateByPrefix(String prefix) {
    return null;
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

  protected Collection<String> getAll(String prefix, TState fromState) {
    List<String> strings = new ArrayList<String>();
    StringBuilder builder = new StringBuilder(prefix);
    this.iterateRecursive(fromState, builder, strings);
    return strings;
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
      sb.append(entry.getFirst());
      iterateRecursive(entry.getSecond(), sb, strings);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
