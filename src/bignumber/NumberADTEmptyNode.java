package bignumber;

import java.util.function.BiFunction;

/**
 * This is a package private class that represents an empty node. This class implements the
 * NumberADT interface.
 */
class NumberADTEmptyNode<T> implements NumberADT<T> {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public NumberADT<T> addBack(Number num, T prev) {
    return new NumberADTElementNode<T>(num, (NumberADT<T>) prev, this);
  }

  @Override
  public NumberADT<T> addFront(Number num, T next) {
    return new NumberADTElementNode<T>(num, this, (NumberADT<T>) next);
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
  public NumberADT<T> next() {
    return null;
  }

  @Override
  public NumberADT<T> prev() {
    return null;
  }

  @Override
  public void setNext(T node) {
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
