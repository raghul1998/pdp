package calculator;

/**
 * This class represents a Simple Calculator.
 * This class also extends the Abstract Calculator class.
 */
public class SimpleCalculator extends AbstractCalculator {
  /**
   * The calculator has certain rules which enforces certain sequence. This method checks if the
   * sequence is valid and as per rule.
   *
   * @param op Input value entered by the user
   * @return Returns true if the sequence is valid, else returns false
   */
  private boolean checkIfSequenceIsValid(char op) {
    if (queue.isEmpty()) {
      if (isAnOperand(op)) {
        inputNumber += op;
        return true;
      } else {
        return false;
      }
    } else {
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

  /**
   * This is a helper method that performs the calculation. It goes through the queue, analysis
   * what operation to be performed and performs them accordingly and also stores the result as
   * string in the global variable.
   */
  private void performCalculation() {
    int val1 = 0;
    int val2;
    Character sign = null;
    StringBuilder number = new StringBuilder();
    result = "";
    isLastSignAnEquals = false;
    boolean isFirstQueueASign = false;

    if (queue.isEmpty()) {
      result = "";
    } else {
      if (queue.peek() == '-') {
        isFirstQueueASign = true;
        result += '-';
        queue.removeFirst();
      }
      for (Character qVar : queue) {
        isLastSignAnEquals = false;
        if (isAnOperand(qVar)) {
          number.append(qVar);
          result += qVar;
        } else {
          if (sign == null) {
            val1 = Integer.parseInt(String.valueOf(number));
            if (isFirstQueueASign) {
              val1 = val1 * -1;
              isFirstQueueASign = false;
            }
            sign = qVar;
            number.setLength(0);
            if (qVar != '=') {
              result += qVar;
            } else {
              isLastSignAnEquals = true;
            }
          } else {
            if (sign == '=') {
              sign = qVar;
            } else {
              val2 = Integer.parseInt(String.valueOf(number));
              val1 = calculate(val1, val2, sign);
              sign = qVar;
              result = String.valueOf(val1);
            }
            if (qVar != '=') {
              result += qVar;
            } else {
              isLastSignAnEquals = true;
            }
            number.setLength(0);
          }
        }
      }
    }
    optimizeQueue();
  }

  @Override
  public Calculator input(char op) throws IllegalArgumentException {
    // Clear the cache if the 'C' or 'c' command is provided.
    if (op == 'C') {
      clear();
      return this;
    }
    if (!checkIfInputIsValid(op)) {
      String exception = String.format("Invalid character %c entered.", op);
      throw new IllegalArgumentException(exception);
    } else {
      if (!checkIfSequenceIsValid(op)) {
        String exception = "Invalid sequence";
        throw new IllegalArgumentException(exception);
      } else {
        if (inputNumber.length() > 0) {
          long val = Long.parseLong(inputNumber);
          if (val > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("The entered value is causing overflow");
          }
        }
        queue.add(op);
      }
    }
    performCalculation();
    return this;
  }

}
