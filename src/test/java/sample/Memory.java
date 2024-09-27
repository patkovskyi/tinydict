package sample;

import jstore.StringSet;
import jstore.implementations.LinearMafsaSet;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.Arrays;

public class Memory {

  public static void main(String[] args) {
    System.out.println(VM.current().details());
    StringSet set = LinearMafsaSet.create(Arrays.asList("abc", "ab"));
    System.out.printf("Memory used: %s bytes", GraphLayout.parseInstance(set).totalSize());
  }
}
