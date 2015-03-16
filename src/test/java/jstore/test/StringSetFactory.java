package jstore.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;

import jstore.Messages;
import jstore.StringSet;

public abstract class StringSetFactory {
  public StringSet deserialize(String path) throws ClassNotFoundException, IOException {
    try (FileInputStream stream = new FileInputStream(path)) {
      try (ObjectInputStream in = new ObjectInputStream(stream)) {
        return (StringSet) in.readObject();
      }
    }
  }

  public void serialize(StringSet trie, String path) throws ClassNotFoundException, IOException {
    try (FileOutputStream stream = new FileOutputStream(path)) {
      try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
        out.writeObject(trie);
      }
    }
  }

  public StringSet create(String[] strings) {
    if (strings == null) {
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
    }

    return create(Arrays.asList(strings));
  }

  public abstract StringSet create(Collection<String> strings);
}
