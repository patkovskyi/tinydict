package jstore.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import jstore.Messages;
import jstore.ModifiableStringSet;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.tuple.Pair;

public class HashTrieSet extends AbstractDafsa<HashTrieSet> implements ModifiableStringSet {

  public static HashTrieSet create(String[] strings) {
    Helper.verifyStringArray(strings);

    HashTrieSet trie = new HashTrieSet();
    for (String s : strings) {
      if (s == null) {
        throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
      }

      trie.add(s);
    }

    return trie;
  }

  public HashMap<Character, HashTrieSet> children;

  public boolean isFinal;

  public HashTrieSet() {
    children = new HashMap<Character, HashTrieSet>();
    isFinal = false;
  }

  @Override
  public boolean add(String string) {
    HashTrieSet cur = this;
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      HashTrieSet child = cur.children.get(c);
      if (child == null) {
        child = new HashTrieSet();
        cur.children.put(c, child);
      }

      cur = child;
    }

    boolean result = cur.isFinal;
    cur.isFinal = true;
    return result;
  }

  @Override
  protected HashTrieSet getNextState(HashTrieSet state, char symbol) {
    return state.children.get(symbol);
  }

  @Override
  protected HashTrieSet getRootState() {
    return this;
  }

  private int getStatesByHeight(TreeMap<Integer, ArrayList<HashTrieSet>> states) {
    int max = 0;
    for (HashTrieSet e : children.values()) {
      int cur = 1 + e.getStatesByHeight(states);
      if (cur > max)
        max = cur;
    }

    if (!states.containsKey(max))
      states.put(max, new ArrayList<HashTrieSet>());
    states.get(max).add(this);
    return max;
  }

  @Override
  protected boolean isFinal(HashTrieSet state) {
    return state.isFinal;
  }

  @Override
  public Iterable<Pair<Character, HashTrieSet>> iterate(final HashTrieSet state) {
    return new Iterable<Pair<Character, HashTrieSet>>() {

      @Override
      public Iterator<Pair<Character, HashTrieSet>> iterator() {
        Iterator<Pair<Character, HashTrieSet>> it = new Iterator<Pair<Character, HashTrieSet>>() {
          private Iterator<Entry<Character, HashTrieSet>> mapIterator = state.children.entrySet()
              .iterator();

          @Override
          public boolean hasNext() {
            return mapIterator.hasNext();
          }

          @Override
          public Pair<Character, HashTrieSet> next() {
            Entry<Character, HashTrieSet> val = mapIterator.next();
            return Pair.of(val.getKey(), val.getValue());
          }

          @Override
          public void remove() {
            mapIterator.remove();
          }
        };

        return it;
      }
    };
  }

  public void minimize() {
    TreeMap<Integer, ArrayList<HashTrieSet>> states =
        new TreeMap<Integer, ArrayList<HashTrieSet>>();
    getStatesByHeight(states);

    HashMap<HashTrieSet, HashTrieSet> oldToNew = new HashMap<HashTrieSet, HashTrieSet>();
    for (Entry<Integer, ArrayList<HashTrieSet>> entry : states.entrySet()) {
      ArrayList<HashTrieSet> level = entry.getValue();

      // first pass
      for (HashTrieSet state : level) {
        for (Entry<Character, HashTrieSet> kv : state.children.entrySet()) {
          HashTrieSet newChild = oldToNew.get(kv.getValue());

          if (newChild != null) {
            state.children.put(kv.getKey(), newChild);
          }
        }
      }

      // second pass
      HashMap<AbstractSignature, HashTrieSet> signatureToTrie =
          new HashMap<AbstractSignature, HashTrieSet>();

      for (HashTrieSet state : level) {
        AbstractSignature signature = new HashTrieSignature(state);
        HashTrieSet newState = signatureToTrie.get(signature);
        if (newState == null) {
          signatureToTrie.put(signature, state);
        } else {
          oldToNew.put(state, newState);
        }
      }
    }
  }

  @Override
  public boolean remove(String s) {
    // TODO Auto-generated method stub
    throw new NotImplementedException("TODO");
  }
}
