package bignumber;

/**
 * This interface represents a BigNumber and a set of operations that can be performed
 * on the BigNumber.
 */
public interface BigNumber extends Comparable<BigNumber> {
  /**
   * This method returns the length of the BigNumber.
   *
   * @return returns the length as an integer
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
   * This method returns an identical and independent copy of this number.
   *
   * @return the copy of the number as a BigNumber object
   */
  BigNumber copy();

  /**
   * This method takes another BigNumber and returns the sum of these two numbers.
   * This operation does not change either number.
   *
   * @param num the other BigNumber which is to be added
   * @return the sum of these two BigNumbers as a BigNumber object
   */
  BigNumber add(BigNumber num);

  /**
   * A method that determines if two BigNumbers are same or not.
   *
   * @param num the BigNumber to be compared to
   * @return true both are same, else false
   */
  boolean same(BigNumber num);
}
