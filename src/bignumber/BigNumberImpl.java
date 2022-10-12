package bignumber;

import java.util.Objects;

public class BigNumberImpl implements BigNumber {
  private NumberADT head;
  private int count;

  public BigNumberImpl() {
    head = new NumberADTEmptyNode();
    head = head.addBack(new Number(0));
    this.count++;
  }

  public BigNumberImpl(String number) throws IllegalArgumentException {
    if (number == null || number.equals("")) {
      throw new IllegalArgumentException("Null String");
    }

    if (checkIfStringIsValid(number)) {
      head = new NumberADTEmptyNode();
      for (int i = 0; i < number.length(); i++) {
        head = head.addBack(new Number(Character.getNumericValue(number.charAt(i))));
        this.count++;
      }
    } else {
      throw new IllegalArgumentException("Invalid Character");
    }
  }

  @Override
  public int length() {
    return this.count;
  }

  @Override
  public void shiftLeft(int shifts) {
    if ((shifts > 0) && (!this.toString().equals("0"))) {
      for (int i = 0; i < shifts; i++) {
        head = head.addBack(new Number(0));
        this.count++;
      }
    }
    if (shifts < 0 && (!this.toString().equals("0"))) {
      shiftRight(shifts * -1);
    }
  }

  @Override
  public void shiftRight(int shifts) {
    if (shifts > 0 && (!head.toString().equals("0"))) {
      for (int i = 0; i < shifts; i++) {
        head = head.removeAt(this.count, this.count > 1 ? false : true);
        this.count--;
      }
    }
    if (shifts < 0 && (!head.toString().equals("0"))) {
      shiftLeft(shifts * -1);
    }
  }

  @Override
  public void addDigit(int digit) throws IllegalArgumentException {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("Digit should be a single non-negative value");
    }

    if (digit != 0) {
      StringBuilder value = new StringBuilder(this.toString());
      int valueLength = value.length();
      boolean carry = false;
      int result;
      int reminder;
      for (int i = valueLength - 1; i >= 0; i--) {
        int opr = Character.getNumericValue(value.charAt(i));
        if (!carry) {
          result = opr + digit;
        } else {
          result = opr + 1;
        }
        if (result > 9) {
          carry = true;
          reminder = result - 10;
          //value.replace(i, i + 1, String.valueOf(reminder));
          head.replaceAt(reminder, valueLength - i);
        } else {
          //value.replace(i, i + 1, String.valueOf(result));
          head.replaceAt(result, valueLength - i);
          carry = false;
          break;
        }
      }
      if (carry) {
        //value.insert(0, 1);
        head = this.head.addFront(new Number(1));
        this.count++;
      }
      /*this.head = new NumberADTEmptyNode();
      for(int i = 0; i < value.length(); i++) {
        this.head = head.addBack(new Number(Character.getNumericValue(value.charAt(i))));
      }*/
    }
  }

  @Override
  public int getDigitAt(int position) throws IllegalArgumentException {
    if (position < 0 || position >= this.length()) {
      throw new IllegalArgumentException("Invalid position");
    }

    return head.getAt(position + 1);
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
