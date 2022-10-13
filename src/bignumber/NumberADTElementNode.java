package bignumber;

class NumberADTElementNode implements NumberADT {
  private final Number val;
  private NumberADT prev;
  private NumberADT rest;

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
  public NumberADT removeAt(int length, boolean isSingleDigit, NumberADT tail) {
    if (isSingleDigit && length == 1 && this.val.getValue() != 0) {
      this.val.replaceValue(0);
      return this;
    } else if (isSingleDigit && length == 1 && this.val.getValue() == 0) {
      return this;
    } else if (!isSingleDigit && length == 1) {
      tail = this.rest;
      return this.rest;
    } else {
      this.rest = this.rest.removeAt(length - 1, false, tail);
      return this;
    }
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
  public NumberADT replaceAt(int value, int position) {
    if (position == this.count()) {
      this.val.replaceValue(value);
      return this;
    } else {
      return this.rest.replaceAt(value, position);
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
