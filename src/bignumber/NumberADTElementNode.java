package bignumber;

import java.util.function.BiFunction;

/**
 * This is a package private class that represents an element node. This class implements the
 * NumberADT interface. This class has the following variables.
 * <ul>
 *   <li> val - value of the node </li>
 *   <li> prev - previous node of this node </li>
 *   <li> rest - next node of this node </li>
 * </ul>
 */
class NumberADTElementNode<T> implements NumberADT<T> {
  private final Number val;
  private NumberADT<T> prev;
  private NumberADT<T> rest;

  /**
   * Constructs a new element node with the given value and set its previous and next node.
   *
   * @param num  value of the node
   * @param prev previous to this new node
   * @param rest rest of this new node
   */
  public NumberADTElementNode(Number num, NumberADT<T> prev, NumberADT<T> rest) {
    this.val = num;
    this.prev = prev;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public NumberADT<T> addBack(Number num, T prev) {
    this.rest = this.rest.addBack(num, prev);
    return this.rest;
  }

  @Override
  public NumberADT<T> addFront(Number num, T next) {
    this.prev = this.prev.addFront(num, next);
    return this.prev;
  }

  @Override
  public String toString() {
    return toStringHelper(new StringBuilder());
  }

  @Override
  public String toStringHelper(StringBuilder acc) {
    return this.rest.toStringHelper(acc.append(this.val.toString()));
  }

  @Override
  public int getAt(int position, int count) {
    if (position == count) {
      return this.val.getValue();
    } else {
      return this.rest.getAt(position, count - 1);
    }
  }

  @Override
  public NumberADT<T> next() {
    return this.rest;
  }

  @Override
  public NumberADT<T> prev() {
    return this.prev;
  }

  @Override
  public void setNext(T node) {
    this.rest = (NumberADT<T>) node;
  }

  @Override
  public int getValue() {
    return this.val.getValue();
  }

  @Override
  public void replace(int value) {
    this.val.replaceValue(value);
  }

  @Override
  public <U> U fold(U initial, BiFunction<U, U, U> combiner) {
    return combiner.apply(initial, this.rest.fold(initial, combiner));
  }

}
