package bignumber;

public interface BigNumber extends Comparable<BigNumber> {
  int length();

  void shiftLeft(int shifts);

  void shiftRight(int shifts);

  void addDigit(int digit) throws IllegalArgumentException;

  int getDigitAt(int position) throws IllegalArgumentException;

  BigNumber copy();

  BigNumber add(BigNumber num);

  boolean same(BigNumber num);

  String toString();
}
