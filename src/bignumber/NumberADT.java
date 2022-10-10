package bignumber;

public interface NumberADT {
  int count();

  NumberADT addBack(Number num);

  NumberADT addFront(Number num);

  String toString();

  String toStringHelper(StringBuilder acc);

  NumberADT removeAt(int length);

  int getAt(int position);

  NumberADT replaceAt(int value, int position);
}
