package jstore.implementations;

import java.util.Collection;

import jstore.Messages;

class Helper {
  static void verifyStringArray(String[] strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
  }

  static void verifyStringCollection(Collection<String> strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
  }
}
