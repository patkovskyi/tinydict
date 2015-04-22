package jstore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class Serializer {
  public static StringSet deserialize(String path) throws ClassNotFoundException, IOException {
    try (FileInputStream stream = new FileInputStream(path)) {
      try (ObjectInputStream in = new ObjectInputStream(stream)) {
        return (StringSet) in.readObject();
      } finally {
        stream.close();
      }
    }
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
    try (FileOutputStream stream = new FileOutputStream(path)) {
      try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
        out.writeObject(stringSet);
        out.flush();
      }

      stream.flush();
    }
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
