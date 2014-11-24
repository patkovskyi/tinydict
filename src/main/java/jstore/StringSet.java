package jstore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringSet {

  public static StringSet createWithSorting(String[] strings) {
    Arrays.sort(strings);

    List<Character> symbols = new ArrayList<Character>();
    List<Integer> transitions = new ArrayList<Integer>();
    List<Integer> lower = new ArrayList<Integer>();

    for (int i = 0; i < strings.length; i++) {
      String s = strings[i];
      int start = 0;
      for (int j = 0; j < s.length(); j++) {

      }
    }

    return new StringSet();
  }

  private char[] symbols;
  private int[] transitions;
  private int[] lower;

  public String[] getStrings() {
    return null;
  }
}
