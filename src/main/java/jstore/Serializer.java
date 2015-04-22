package jstore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Serializer {
  public static StringSet deserialize(String path) throws ClassNotFoundException, IOException {
    return deserialize(Files.readAllBytes(Paths.get(path)));
  }

  public static StringSet deserialize(byte[] data) throws ClassNotFoundException, IOException {
    try (ByteArrayInputStream stream = new ByteArrayInputStream(data)) {
      try (ObjectInputStream in = new ObjectInputStream(stream)) {
        return (StringSet) in.readObject();
      } finally {
        stream.close();
      }
    }
  }

  public static void serialize(StringSet stringSet, String path) throws ClassNotFoundException,
      IOException {
    Files.write(Paths.get(path), serialize(stringSet));
  }

  public static byte[] serialize(StringSet stringSet) throws IOException {
    try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
        out.writeObject(stringSet);
        out.flush();
      }

      stream.flush();
      return stream.toByteArray();
    }
  }
}
