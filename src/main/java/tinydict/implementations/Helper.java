package tinydict.implementations;

import java.util.Collection;
import tinydict.Messages;

class Helper {
  static void verifyStringArray(String[] strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
  }

  static void verifyStringCollection(Collection<String> strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
  }

  static void verifyInputString(String input) {
    if (input == null) throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
  }
}
