package jstore.testhelpers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jstore.Messages;
import jstore.StringSet;

import com.google.common.io.Resources;

public class TestHelper {
  public static final String ZALIZNYAK = "Zaliznyak-1251.txt";
  public static final String ZALIZNYAK_BASEFORMS = "Zaliznyak-baseforms-1251.txt";
  public static final Charset ZALIZNYAK_CHARSET = Charset.forName("Cp1251");

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

  public static Path getResourcePath(String fileName) {
    return Paths.get(getResourcePathString(fileName));
  }

  public static String getResourcePathString(String fileName) {
    ClassLoader classLoader = PerformanceTest.class.getClassLoader();
    return classLoader.getResource(fileName).getPath();
  }

  public static String getRandomFilePath() throws IOException {
    File tempFile = File.createTempFile("tmp", ".tmp");
    tempFile.deleteOnExit();
    return tempFile.getAbsolutePath();
  }

  public static List<String> readResourceFile(String resourceName, Charset charset)
      throws IOException {
    URL url = Resources.getResource(resourceName);
    return Resources.readLines(url, charset);
  }

  public static void verifyStringArray(String[] strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);
  }

  public static void verifyStringCollection(Collection<String> strings) {
    if (strings == null)
      throw new IllegalArgumentException(Messages.STRING_COLLECTION_CAN_NOT_BE_NULL);

    if (strings.contains(null)) {
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
    }
  }

  public static void verifyInputString(String input) {
    if (input == null)
      throw new IllegalArgumentException(Messages.NULL_STRINGS_ARE_NOT_ALLOWED);
  }
}
