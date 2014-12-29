package jstore;

import java.util.Map.Entry;


public class HashTrieSignature extends AbstractSignature {

  HashTrie trie;

  public HashTrieSignature(HashTrie trie) {
    this.trie = trie;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof HashTrieSignature))
      return false;
    if (obj == this)
      return true;

    HashTrieSignature other = (HashTrieSignature) obj;
    if (other.trie.children.size() != this.trie.children.size()
        || other.trie.isFinal() != this.trie.isFinal())
      return false;

    for (Entry<Character, HashTrie> entry : this.trie.children.entrySet()) {
      if (other.trie.children.get(entry.getKey()) != entry.getValue())
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hash = trie.isFinal() ? 17 : 19;
    for (Entry<Character, HashTrie> entry : trie.children.entrySet()) {
      hash += entry.getKey() * entry.getValue().hashCode();
    }

    return hash;
  }

}
