package jmh;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public enum TestFile {
  ZALIZNYAK_FULL("Zaliznyak-1251.txt", Charset.forName("Cp1251")),
  ZALIZNYAK_BASE("Zaliznyak-baseforms-1251.txt", Charset.forName("Cp1251"));

  public String getFileName() {
    return fileName;
  }

  public Charset getCharset() {
    return charset;
  }

  private final String fileName;
  private final Charset charset;

  public List<String> readLines() throws IOException {
    URL url = Resources.getResource(fileName);
    return Resources.readLines(url, charset);
  }

  TestFile(String fileName, Charset charset) {
    this.fileName = fileName;
    this.charset = charset;
  }
}
