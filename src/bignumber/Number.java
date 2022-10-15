package bignumber;

/**
 * This class represents a single number and the operations that can be performed on the number.
 * This class has the following variables.
 * <ul>
 *   <li> value - single digit value </li>
 * </ul>
 */
class Number {
  private int value;

  /**
   * Constructs a Number object and initializes it to the given number.
   *
   * @param number number to be created
   */
  public Number(int number) {
    this.value = number;
  }

  /**
   * Override the toString function in this class to return the number as a string.
   *
   * @return the number value as string
   */
  public String toString() {
    return String.valueOf(this.value);
  }

  /**
   * This method helps to retrieve the integer value of the given number node.
   *
   * @return value of the number as an integer.
   */
  public int getValue() {
    return this.value;
  }

  /**
   * This method replaces this number with the provided value.
   *
   * @param val the number to be replaced
   */
  public void replaceValue(int val) {
    this.value = val;
  }
}
