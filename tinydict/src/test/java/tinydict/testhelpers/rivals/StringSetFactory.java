package tinydict.testhelpers.rivals;

import java.util.Arrays;
import java.util.Collection;
import tinydict.Messages;
import tinydict.StringSet;

public abstract class StringSetFactory {
  public StringSet create(String[] strings) {
    if (strings == null) {
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
    }

    return create(Arrays.asList(strings));
  }

  public abstract StringSet create(Collection<String> strings);
}
