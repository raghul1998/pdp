package calculator;

/**
 * This class represents a Simple Calculator.
 * This class also extends the Abstract Calculator class.
 */
public class SimpleCalculator extends AbstractCalculator {

  @Override
  protected boolean checkSequenceElsePart(char op) {
    if (isAnOperator(op)) {
      // Clear the last input if we see an operator
      inputNumber = "";
      // Check if the last element in the queue is an operand
      if (isAnOperand(queue.getLast())) {
        return true;
      } else {
        // This means the last element in the queue is an operator
        // The calculator does allow inputting "=" multiple times.
        // Calculator also allows operators after '='.
        // Cannot also two operators. Invalid sequence.
        return queue.getLast() == '=';
      }
    } else {
      // If a number is coming in immediately after '=' without any operator, then
      // it is considered as a new calculator. Hence, clear.
      if (queue.getLast() == '=') {
        clear();
      }
      inputNumber += op;
      return true;
    }
  }
}
