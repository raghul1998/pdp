package bignumber;

public interface NumberADT {
  int count();

  NumberADT addBack(Number num);

  NumberADT addFront(Number num);

  String toString();

  String toStringHelper(StringBuilder acc);

  NumberADT removeAt(int length, boolean isSingleDigit);

  int getAt(int position, int count);

  NumberADT replaceAt(int value, int position);
}
