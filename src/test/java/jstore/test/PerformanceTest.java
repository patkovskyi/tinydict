package jstore.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import jstore.StringSet;

import org.junit.BeforeClass;

public class PerformanceTest {

  private static void createSerializedFile(List<String> rawStrings) throws IOException {
    StringSet stringSet = StringSet.create(rawStrings);
    stringSet.serialize(TestHelper.getStringPath(SERIALIZED_FILE_NAME));
  }

  private static List<String> readRawFile() throws IOException {
    return Files.readAllLines(TestHelper.getPath(RAW_FILE_NAME), Charset.forName(RAW_FILE_CHARSET));
  }

  @BeforeClass
  public static void setUp() throws Exception {
    List<String> rawStrings = readRawFile();

    if (!validSerializedFileExists(rawStrings)) {
      System.out.println("No valid serialized file found. Creating one!");
      createSerializedFile(rawStrings);

      if (!validSerializedFileExists(rawStrings)) {
        throw new Exception("Could not create valid serialized file.");
      }
    }
  }

  private static boolean validSerializedFileExists(List<String> rawList) {
    try {
      StringSet stringSet = StringSet.deserialize(TestHelper.getStringPath(SERIALIZED_FILE_NAME));
      return TestHelper.areEquivalent(stringSet, rawList);
    } catch (Exception e) {
      return false;
    }
  }

  private static final String SERIALIZED_FILE_NAME = "Zaliznyak.ser";

  private static final String RAW_FILE_NAME = "Zaliznyak-1251.txt";

  private static final String RAW_FILE_CHARSET = "Cp1251";
}
