package jstore.testhelpers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;

import jstore.Serializer;
import jstore.StringSet;
import jstore.implementations.LinearMafsaSet;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;

import org.testng.annotations.BeforeClass;

public class PerformanceTest extends AbstractBenchmark {

  public static String[] array;

  public static HashSet<String> hashSet;

  private static final String RAW_FILE_CHARSET = "Cp1251";

  private static final String RAW_FILE_NAME = "Zaliznyak-1251.txt";

  private static final String SERIALIZED_FILE_NAME = "Zaliznyak.ser";

  public static StringSet stringSet;

  private static void createSerializedFile(List<String> list) throws IOException,
  ClassNotFoundException {
    LinearMafsaSet stringSet = LinearMafsaSet.create(list);
    Serializer.serialize(stringSet, SERIALIZED_FILE_NAME);
  }

  private static boolean isValidStringSet(StringSet stringSet, List<String> list) {
    return stringSet != null && TestHelper.areEquivalent(stringSet, list);
  }

  private static List<String> readRawFile() throws IOException {
    return TestHelper.readResourceFile(RAW_FILE_NAME, Charset.forName(RAW_FILE_CHARSET));
  }

  @BeforeClass
  public static void setUp() throws Exception {
    List<String> list = readRawFile();

    stringSet = tryLoadSerializedFile();
    if (!isValidStringSet(stringSet, list)) {
      System.out.print("No valid serialized file found. Creating one...");
      createSerializedFile(list);

      stringSet = tryLoadSerializedFile();
      if (!isValidStringSet(stringSet, list)) {
        throw new Exception("Could not create valid serialized file.");
      }

      System.out.println("Done.");
    }

    hashSet = new HashSet<String>(list);
    array = list.toArray(new String[0]);
  }

  private static StringSet tryLoadSerializedFile() {
    try {
      return Serializer.deserialize(SERIALIZED_FILE_NAME);
    } catch (Exception e) {
      return null;
    }
  }
}
