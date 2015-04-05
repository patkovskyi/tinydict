package jstore.testhelpers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import jstore.StringSet;
import jstore.implementations.MafsaSet;

public class TestHelper {
  public static boolean areEquivalent(StringSet stringSet, Collection<String> stringList) {
    return areEquivalent(stringSet, stringList.toArray(new String[0]));
  }

  public static boolean areEquivalent(StringSet stringSet, String[] stringArray) {
    String[] expected = Arrays.copyOf(stringArray, stringArray.length, stringArray.getClass());
    Arrays.sort(expected);

    String[] actual = stringSet.getAll().toArray(new String[0]);
    Arrays.sort(actual);

    return Arrays.equals(expected, actual);
  }

  public static Path getPath(String fileName) {
    return Paths.get(getStringPath(fileName));
  }

  public static String getStringPath(String fileName) {
    ClassLoader classLoader = PerformanceTest.class.getClassLoader();
    return classLoader.getResource(fileName).getPath();
  }

  public static StringSet getStringSet(String[] strings) {
    return MafsaSet.create(strings);
  }
}
