package sample;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import tinydict.Serializer;
import tinydict.StringSet;
import tinydict.implementations.BinaryMafsaSet;
import tinydict.implementations.LinearMafsaSet;
import tinydict.testhelpers.TestFile;

public class Main {
  public static void main(String[] args) throws IOException {
    {
      URL dictionaryURL = Resources.getResource(TestFile.ZALIZNYAK_FULL.getFileName());
      Path dictionaryPath = Paths.get(dictionaryURL.getPath());
      System.out.printf(
          "Dictionary file size (%s): %d kb.%n",
          TestFile.ZALIZNYAK_FULL.getCharset(), Files.size(dictionaryPath) / 1024);
    }

    {
      StringSet linearMafsaSet = LinearMafsaSet.create(TestFile.ZALIZNYAK_FULL.readLines());
      byte[] linearMafsaBytes = Serializer.serialize(linearMafsaSet);
      System.out.printf("Linear mafsa size (serialized): %d kb.%n", linearMafsaBytes.length / 1024);
    }

    {
      StringSet binaryMafsaSet = BinaryMafsaSet.create(TestFile.ZALIZNYAK_FULL.readLines());
      byte[] binaryMafsaBytes = Serializer.serialize(binaryMafsaSet);
      System.out.printf("Binary mafsa size (serialized): %d kb.%n", binaryMafsaBytes.length / 1024);
    }
  }
}
