package jstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.tuple.Pair;

public class HashTrie extends AbstractTrie<HashTrie> implements IModifiableTrie {

  public static HashTrie create(String[] strings) {
    HashTrie trie = new HashTrie();
    for (String s : strings) {
      trie.add(s);
    }

    return trie;
  }

  public HashMap<Character, HashTrie> children;

  public boolean isFinal;

  public HashTrie() {
    children = new HashMap<Character, HashTrie>();
    isFinal = false;
  }

  @Override
  public boolean add(String string) {
    HashTrie cur = this;
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      HashTrie child = cur.children.get(c);
      if (child == null) {
        child = new HashTrie();
        cur.children.put(c, child);
      }

      cur = child;
    }

    boolean result = cur.isFinal;
    cur.isFinal = true;
    return result;
  }

  @Override
  HashTrie getNextState(HashTrie state, char symbol) {
    return state.children.get(symbol);
  }

  @Override
  HashTrie getRootState() {
    return this;
  }

  private int getStatesByHeight(TreeMap<Integer, ArrayList<HashTrie>> states) {
    int max = 0;
    for (HashTrie e : children.values()) {
      int cur = 1 + e.getStatesByHeight(states);
      if (cur > max)
        max = cur;
    }

    if (!states.containsKey(max))
      states.put(max, new ArrayList<HashTrie>());
    states.get(max).add(this);
    return max;
  }

  @Override
  boolean isFinal(HashTrie state) {
    return state.isFinal;
  }

  @Override
  public Iterable<Pair<Character, HashTrie>> iterate(final HashTrie state) {
    return new Iterable<Pair<Character, HashTrie>>() {

      @Override
      public Iterator<Pair<Character, HashTrie>> iterator() {
        Iterator<Pair<Character, HashTrie>> it = new Iterator<Pair<Character, HashTrie>>() {
          private Iterator<Entry<Character, HashTrie>> mapIterator = state.children.entrySet()
              .iterator();

          @Override
          public boolean hasNext() {
            return mapIterator.hasNext();
          }

          @Override
          public Pair<Character, HashTrie> next() {
            Entry<Character, HashTrie> val = mapIterator.next();
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
    TreeMap<Integer, ArrayList<HashTrie>> states = new TreeMap<Integer, ArrayList<HashTrie>>();
    getStatesByHeight(states);

    HashMap<HashTrie, HashTrie> oldToNew = new HashMap<HashTrie, HashTrie>();
    for (Entry<Integer, ArrayList<HashTrie>> entry : states.entrySet()) {
      ArrayList<HashTrie> level = entry.getValue();

      // first pass
      for (HashTrie state : level) {
        for (Entry<Character, HashTrie> kv : state.children.entrySet()) {
          HashTrie newChild = oldToNew.get(kv.getValue());

          if (newChild != null) {
            state.children.put(kv.getKey(), newChild);
          }
        }
      }

      // second pass
      HashMap<AbstractSignature, HashTrie> signatureToTrie =
          new HashMap<AbstractSignature, HashTrie>();

      for (HashTrie state : level) {
        AbstractSignature signature = new HashTrieSignature(state);
        HashTrie newState = signatureToTrie.get(signature);
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
