package bignumber;

public interface NumberADT {
  int count();

  NumberADT addBack(Number num, NumberADT prev);

  NumberADT addFront(Number num, NumberADT next);

  String toString();

  String toStringHelper(StringBuilder acc);

  NumberADT removeAt(int length, boolean isSingleDigit, NumberADT tail);

  int getAt(int position, int count);

  NumberADT replaceAt(int value, int position);

  NumberADT next();

  void setNext(NumberADT node);

  int getValue();

  void replace(int value);

  NumberADT prev();
}
