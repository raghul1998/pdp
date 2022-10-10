package bignumber;

public class NumberADTEmptyNode implements NumberADT {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public NumberADT addBack(Number num) {
    return new NumberADTElementNode(num, this);
  }

  @Override
  public NumberADT addFront(Number num) {
    return new NumberADTElementNode(num, this);
  }

  public String toString() {
    return "";
  }

  @Override
  public String toStringHelper(StringBuilder acc) {
    return acc.toString();
  }

  @Override
  public NumberADT removeAt(int length) {
    // Cannot remove anything from empty node
    return this;
  }

  @Override
  public int getAt(int position) throws IllegalArgumentException {
    throw new IllegalArgumentException("Invalid Position");
  }

  @Override
  public NumberADT replaceAt(int value, int position) {
    // Nothing to replace
    throw new IllegalArgumentException("Invalid Position");
  }

}
