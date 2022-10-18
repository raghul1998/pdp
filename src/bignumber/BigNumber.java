package bignumber;

/**
 * This interface represents a BigNumber and a set of operations that can be performed
 * on the BigNumber.
 */
public interface BigNumber<T> extends Comparable<T> {
  /**
   * This method length that returns the number of digits in this number.
   * This method basically helps to find the length of the big number.
   *
   * @return the length as an integer
   */
  int length();

  /**
   * This method left shifts the BigNumber. This method takes the number of shifts as an argument
   * and shifts this number to the left by that number. A negative number of left-shifts will
   * correspond to those many right shifts.
   *
   * @param shifts specifies the number of shifts to be performed
   */
  void shiftLeft(int shifts);

  /**
   * This method right shifts the BigNumber. It takes the number of shifts as an argument and
   * shifts this number to the right by that number. A negative number of right-shifts will
   * correspond to those many left shifts.
   *
   * @param shifts specifies the number of shifts to be performed
   */
  void shiftRight(int shifts);

  /**
   * This method takes a single digit as an argument and adds it to this number.
   * This method throws an IllegalArgumentException if the digit passed to it is not a single
   * non-negative digit.
   *
   * @param digit the digit to be added to the BigNumber
   * @throws IllegalArgumentException if the digit is not a single non-negative digit
   */
  void addDigit(int digit) throws IllegalArgumentException;

  /**
   * This method takes a position as an argument and returns the digit at that position.
   * Positions start at 0 (rightmost digit). This method throws an IllegalArgumentException if an
   * invalid position is passed.
   *
   * @param position the position of the digit
   * @return the digit at the given position as an integer
   * @throws IllegalArgumentException if the position passed is invalid
   */
  int getDigitAt(int position) throws IllegalArgumentException;

  /**
   * This method helps to get an identical and independent copy of this number. Modifying the
   * return object will not modify the current object.
   *
   * @return the copy of the number as a BigNumber object
   */
  T copy();

  /**
   * This method takes another BigNumber and returns the sum of these two numbers.
   * This operation does not change either number.
   *
   * @param num the other BigNumber which is to be added
   * @return the sum of these two BigNumbers as a BigNumber object
   */
  T add(BigNumber<T> num);

  /**
   * This method compares two BigNumbers for ordering. This method overrides the
   * comparable interface.
   *
   * @param num the object to be compared
   * @return 0 if both big numbers are same, else returns a positive number if this object is
   *         greater, else returns a negative number if this object is smaller
   */
  int compareTo(T num);

}
