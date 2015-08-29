package jstore.testhelpers;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public enum TestFile {
  ZALIZNYAK_FULL("Zaliznyak-1251.txt", Charset.forName("Cp1251")),
  ZALIZNYAK_BASE("Zaliznyak-baseforms-1251.txt", Charset.forName("Cp1251"));

  private String name;
  private Charset charset;

  public List<String> readLines() throws IOException {
    URL url = Resources.getResource(name);
    return Resources.readLines(url, charset);
  }

  TestFile(String name, Charset charset){
    this.name = name;
    this.charset = charset;
  }
}
