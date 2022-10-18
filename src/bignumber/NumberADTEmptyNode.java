package bignumber;

import java.util.function.BiFunction;

/**
 * This is a package private class that represents an empty node. This class implements the
 * NumberADT interface.
 */
class NumberADTEmptyNode implements NumberADT {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public NumberADT addBack(Number num, NumberADT prev) {
    return new NumberADTElementNode(num, prev, this);
  }

  @Override
  public NumberADT addFront(Number num, NumberADT next) {
    return new NumberADTElementNode(num, this, next);
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public String toStringHelper(StringBuilder acc) {
    return acc.toString();
  }

  @Override
  public int getAt(int position, int count) throws IllegalArgumentException {
    throw new IllegalArgumentException("Invalid Position");
  }

  @Override
  public NumberADT next() {
    return null;
  }

  @Override
  public NumberADT prev() {
    return null;
  }

  @Override
  public void setNext(NumberADT node) {
    throw new IllegalArgumentException("Invalid Position");
  }

  @Override
  public int getValue() {
    throw new IllegalArgumentException("Invalid Position");
  }

  @Override
  public void replace(int value) {
    throw new IllegalArgumentException("Invalid Position");
  }

  @Override
  public Integer fold(Integer initial, BiFunction<Integer, Integer, Integer> combiner) {
    return initial;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof NumberADT)) {
      return false;
    }

    return this.next() == null && this.prev() == null;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
