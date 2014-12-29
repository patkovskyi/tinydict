package jstore;

public interface IModifiableTrie extends ITrie {
  public boolean add(String s);

  public boolean remove(String s);
}
