package jstore.test.rivals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import jstore.ITrie;

public abstract class ITrieFactory {
  public ITrie deserialize(String path) throws ClassNotFoundException, IOException {
    try (FileInputStream stream = new FileInputStream(path)) {
      try (ObjectInputStream in = new ObjectInputStream(stream)) {
        return (ITrie) in.readObject();
      }
    }
  }

  public void serialize(ITrie trie, String path) throws ClassNotFoundException, IOException {
    try (FileOutputStream stream = new FileOutputStream(path)) {
      try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
        out.writeObject(trie);
      }
    }
  }

  public abstract ITrie create(Collection<String> strings);
}
