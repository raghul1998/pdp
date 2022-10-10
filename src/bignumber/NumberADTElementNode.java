package bignumber;

public class NumberADTElementNode implements NumberADT {
  private final Number val;
  private NumberADT rest;

  public NumberADTElementNode(Number num, NumberADT rest) {
    this.val = num;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public NumberADT addBack(Number num) {
    this.rest = this.rest.addBack(num);
    return this;
  }

  @Override
  public NumberADT addFront(Number num) {
    return new NumberADTElementNode(num, this);
  }

  public String toString() {
    return toStringHelper(new StringBuilder());
  }

  public String toStringHelper(StringBuilder acc) {
    return this.rest.toStringHelper(acc.append(this.val.toString()));
  }

  @Override
  public NumberADT removeAt(int length) {
    if (length == 1) {
      return this.rest;
    } else {
      length = length - 1;
      this.rest = this.rest.removeAt(length);
      return this;
    }
  }

  @Override
  public int getAt(int position) {
    if (position == this.count()) {
      return this.val.getValue();
    } else {
      return this.rest.getAt(position);
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

}
