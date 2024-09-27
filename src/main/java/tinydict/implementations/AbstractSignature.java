package tinydict.implementations;

abstract class AbstractSignature {
  @Override
  public abstract boolean equals(Object other);

  @Override
  public abstract int hashCode();
}
