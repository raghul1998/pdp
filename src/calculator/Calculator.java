package calculator;

/**
 * This interface represents a Calculator and set of operations performed by the calculator.
 * This calculator basically has two operations, input and result.
 */
public interface Calculator {
  /**
   * This method takes in input for the calculator. The method takes only one character at a time
   * as an input and the valid input characters are operands 0-9 and operators '+', '-', '*', '='.
   *
   * @param op Input for the calculator. Can be either an operand or operator
   * @return Returns the Calculator object
   * @throws IllegalArgumentException if the input or sequence is not valid
   */
  Calculator input(char op) throws IllegalArgumentException;

  /**
   * This method results the current state or the result of the calculator.
   *
   * @return returns the result as a string
   */
  String getResult();
}
