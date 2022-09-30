package calculator;

/**
 * This class is an abstract class for all the other calculator classes. This class also implements
 * the Calculator interface.
 * This has the following variables.
 * <ul>
 *   <li>
 *     ValidOperators - A char array that holds the valid operators that can be inputted.
 *   </li>
 * </ul>
 */
public abstract class AbstractCalculator implements Calculator {
  protected char[] validOperators = {'+', '-', '*', '='};

  /**
   * This method checks if the value passed is an operand or not. Meaning it returns true if the
   * value passes is a number, else returns false.
   *
   * @param op character that is passed to check if it is an operand or not
   * @return true if the passed argument is an operand, else returns false
   */
  protected boolean isAnOperand(char op) {
    int opVal = Character.getNumericValue(op);
    if (opVal >= 0 && opVal <= 9) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method checks if the value passed is an operator or not. Meaning it returns true if the
   * value passes is a one of the values in validOperators array.
   *
   * @param op character that is passed to check if it is an operand or not
   * @return true if the passed argument is an operator, else returns false
   */
  protected boolean isAnOperator(char op) {
    for (char validOperators : validOperators) {
      if (op == validOperators) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if the inputted value is a valid character or not.
   *
   * @param op The input that was entered by the user
   * @return Returns true if the input value is valid, else returns false
   */
  protected boolean checkIfInputIsValid(char op) {
    if (isAnOperand(op) || isAnOperator(op)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This is a helper function that helps in performing calculations based on the passed arguments
   * and the sign.
   *
   * @param num1 The first operand
   * @param num2 The second operand
   * @param sign The operation that needs to be performed
   * @return Returns the result of the operation
   */
  protected int Calculate(int num1, int num2, Character sign) {
    long result = 0;
    if (sign == null) {
      return num1;
    }
    if (sign == '+') {
      result = (long) num1 + num2;
    } else if (sign == '-') {
      result = (long) num1 - num2;
    } else if (sign == '*') {
      result = (long) num1 * num2;
    }

    if (result > Integer.MAX_VALUE || result < -Integer.MAX_VALUE) {
      // Overflow
      result = 0;
    }
    return (int) result;
  }

}
