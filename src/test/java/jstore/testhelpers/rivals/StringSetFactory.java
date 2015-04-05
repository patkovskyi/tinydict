package jstore.testhelpers.rivals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import jstore.Messages;
import jstore.Serializer;
import jstore.StringSet;

public abstract class StringSetFactory {
  public StringSet deserialize(String path) throws ClassNotFoundException, IOException {
    return Serializer.deserialize(path);
  }

  public void serialize(StringSet stringSet, String path) throws ClassNotFoundException,
      IOException {
    Serializer.serialize(stringSet, path);
  }

  public StringSet create(String[] strings) {
    if (strings == null) {
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
    }

    return create(Arrays.asList(strings));
  }

  public abstract StringSet create(Collection<String> strings);
}
