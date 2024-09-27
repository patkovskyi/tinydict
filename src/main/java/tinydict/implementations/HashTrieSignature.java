package tinydict.implementations;

import java.util.Map.Entry;

class HashTrieSignature extends AbstractSignature {

  HashTrieSet trie;

  HashTrieSignature(HashTrieSet trie) {
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
        || other.trie.isFinal(other.trie) != this.trie.isFinal(this.trie))
      return false;

    for (Entry<Character, HashTrieSet> entry : this.trie.children.entrySet()) {
      if (other.trie.children.get(entry.getKey()) != entry.getValue())
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hash = trie.isFinal(trie) ? 17 : 19;
    for (Entry<Character, HashTrieSet> entry : trie.children.entrySet()) {
      hash += entry.getKey() * entry.getValue().hashCode();
    }

    return hash;
  }

}
