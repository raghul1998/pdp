package bignumber;

/**
 * This is a package private class that represents an element node. This class implements the
 * NumberADT interface. This class has the following variables.
 * <ul>
 *   <li> val - value of the node </li>
 *   <li> prev - previous node of this node </li>
 *   <li> rest - next node of this node </li>
 * </ul>
 */
class NumberADTElementNode implements NumberADT {
  private final Number val;
  private NumberADT prev;
  private NumberADT rest;

  /**
   * Constructs a new element node with the given value and set its previous and next node.
   *
   * @param num  value of the node
   * @param prev previous to this new node
   * @param rest rest of this new node
   */
  public NumberADTElementNode(Number num, NumberADT prev, NumberADT rest) {
    this.val = num;
    this.prev = prev;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public NumberADT addBack(Number num, NumberADT prev) {
    this.rest = this.rest.addBack(num, prev);
    return this.rest;
  }

  @Override
  public NumberADT addFront(Number num, NumberADT next) {
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
  public NumberADT next() {
    return this.rest;
  }

  @Override
  public NumberADT prev() {
    return this.prev;
  }

  @Override
  public void setNext(NumberADT node) {
    this.rest = node;
  }

  @Override
  public int getValue() {
    return this.val.getValue();
  }

  @Override
  public void replace(int value) {
    this.val.replaceValue(value);
  }

}
