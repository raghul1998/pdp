package bignumber;

public class Number {
  private int value;

  public Number(int number) {
    this.value = number;
  }

  public String toString() {
    return String.valueOf(this.value);
  }

  public int getValue() {
    return this.value;
  }

  public void replaceValue(int val) {
    this.value = val;
  }
}
