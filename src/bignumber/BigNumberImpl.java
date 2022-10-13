package bignumber;

import java.util.Objects;

public class BigNumberImpl implements BigNumber {
  private NumberADT head;
  private NumberADT tail;
  private int count;

  public BigNumberImpl() {
    head = tail = new NumberADTEmptyNode();
    head = tail = tail.addBack(new Number(0));
    this.count++;
  }

  public BigNumberImpl(String number) throws IllegalArgumentException {
    if (number == null || number.equals("")) {
      throw new IllegalArgumentException("Null or Empty String");
    }

    if (checkIfStringIsValid(number)) {
      head = tail = new NumberADTEmptyNode();
      head = tail = tail.addBack(new Number(Character.getNumericValue(number.charAt(0))));
      this.count++;
      for (int i = 1; i < number.length(); i++) {
        tail = tail.addBack(new Number(Character.getNumericValue(number.charAt(i))));
        this.count++;
      }
    } else {
      throw new IllegalArgumentException("Invalid Number");
    }
  }

  @Override
  public int length() {
    return this.count;
  }

  @Override
  public void shiftLeft(int shifts) {
    if ((shifts > 0) && (this.count > 1 || !this.toString().equals("0"))) {
      for (int i = 0; i < shifts; i++) {
        tail = tail.addBack(new Number(0));
        this.count++;
      }
    } else if (shifts < 0) {
      shiftRight(shifts * -1);
    }
  }

  @Override
  public void shiftRight(int shifts) {
    if (shifts > 0 && (this.count > 1 || !this.toString().equals("0"))) {
      if (shifts < this.count - 1) {
        NumberADT temp = head;
        for (int i = 0; i < this.count - shifts - 1; i++) {
          temp = temp.next();
        }
        tail = temp;
        tail.setNext(new NumberADTEmptyNode());
        this.count = this.count - shifts;
      } else if (shifts + 1 == this.count) {
        tail = head;
        tail.setNext(new NumberADTEmptyNode());
        this.count = 1;
      } else {
        head = tail = new NumberADTEmptyNode();
        head = tail = tail.addBack(new Number(0));
        this.count = 1;
      }

    } else if (shifts < 0) {
      shiftLeft(shifts * -1);
    }
  }

  @Override
  public void addDigit(int digit) throws IllegalArgumentException {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("Digit should be a single non-negative value");
    }
    if (digit != 0) {
      String result = sumOfTwoBigNumbers(Integer.toString(digit), this.toString());
      int len = result.length();
      head = tail = new NumberADTEmptyNode();
      head = tail = tail.addBack(new Number(Character.getNumericValue(result.charAt(0))));
      for (int i = 1; i < len; i++) {
        tail = tail.addBack(new Number(Character.getNumericValue(result.charAt(i))));
      }
      this.count = len;
    }
  }

  @Override
  public int getDigitAt(int position) throws IllegalArgumentException {
    if (position < 0) {
      throw new IllegalArgumentException("Invalid position");
    }
    if (position >= this.length()) {
      return 0;
    }
    return head.getAt(position + 1, count);
  }

  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.toString());
  }

  @Override
  public BigNumber add(BigNumber num) {
    if (num == null || Objects.equals(num.toString(), "0")) {
      return this;
    }
    String result;
    if (this.length() <= num.length()) {
      result = sumOfTwoBigNumbers(this.toString(), num.toString());
    } else {
      result = sumOfTwoBigNumbers(num.toString(), this.toString());
    }
    return new BigNumberImpl(result);
  }

  @Override
  public boolean same(BigNumber num) {
    int result = this.compareTo(num);
    return result == 0;
  }

  @Override
  public int compareTo(BigNumber obj) {
    if (this == obj) {
      return 0;
    }

    String opr1 = this.toString();
    String opr2 = obj.toString();

    int opr1Length = opr1.length();
    int opr2Length = opr2.length();

    if (opr1Length > opr2Length) {
      return 1;
    } else if (opr1Length < opr2Length) {
      return -1;
    } else {
      for (int i = 0; i < opr1Length; i++) {
        if (opr1.charAt(i) > opr2.charAt(i)) {
          return 1;
        } else if (opr1.charAt(i) < opr2.charAt(i)) {
          return -1;
        }
      }
      return 0;
    }
  }

  public String toString() {
    return this.head.toString();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof BigNumber)) {
      return false;
    }

    BigNumber num = (BigNumber) obj;
    return Objects.equals(this.toString(), num.toString());
  }

  @Override
  public int hashCode () {
    return Long.hashCode(Long.parseLong(this.toString()));
  }

  private boolean checkIfStringIsValid(String num) {
    boolean result = true;
    for (int i = 0; i < num.length(); i++) {
      char val = num.charAt(i);
      if (Character.getNumericValue(val) >= 0 && Character.getNumericValue(val) <= 9) {
        continue;
      } else {
        result = false;
        break;
      }
    }
    return result;
  }

  private String sumOfTwoBigNumbers(String opr1, String opr2) {
    StringBuilder result = new StringBuilder();

    int opr1Length = opr1.length();
    int opr2Length = opr2.length();
    int difference = opr2Length - opr1Length;
    int carry = 0;

    for (int i = opr1Length - 1; i >= 0; i--) {
      int sum = ((opr1.charAt(i) - '0') + (opr2.charAt(i + difference) - '0') + carry);
      result.append((char) (sum % 10 + '0'));
      carry = sum / 10;
    }

    for (int i = opr2Length - opr1Length - 1; i >= 0; i--) {
      int sum = ((opr2.charAt(i) - '0') + carry);
      result.append((char) (sum % 10 + '0'));
      carry = sum / 10;
    }

    if (carry > 0) {
      result.append((char) (carry + '0'));
    }

    return result.reverse().toString();
  }

}
