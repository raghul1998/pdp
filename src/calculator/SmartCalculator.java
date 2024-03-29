package calculator;

/**
 * This class represents a Smart Calculator. This class also extends the Abstract Calculator class.
 * This class has the following variable.
 * <ul>
 *   <li>
 *     lastValidOperation - This variable keeps track of the last operation that was performed.
 *   </li>
 *   <li>
 *     lastVal2 - The second operand that was entered needs to be stored for special cases in the
 *     smart calculator. This variable helps to keep track of that.
 *   </li>
 *   <li>
 *     blockInput - certain inputs need not be pushed to the queue. This variable helps in keeping
 *     track of that.
 *   </li>
 * </ul>
 */
public class SmartCalculator extends AbstractCalculator {
  private boolean blockInput = false;
  private Character lastValidOperation = null;
  private int lastVal2 = 0;

  /**
   * This method is a helper function has clears the state of the calculator by clearing certain
   * variables and the queue.
   */
  protected void clearSmart() {
    clear();
    lastValidOperation = null;
  }

  /**
   * The calculator has certain rules which enforces certain sequence. This method checks if the
   * sequence is valid and as per rule.
   *
   * @param op Input value entered by the user
   * @return Returns true if the sequence is valid, else returns false
   */
  private boolean checkIfSequenceIsValid(char op) {
    blockInput = false;
    if (queue.isEmpty()) {
      if (isAnOperand(op)) {
        inputNumber += op;
        return true;
      } else {
        if (op == '+') {
          blockInput = true;
          return true;
        } else {
          return false;
        }
      }
    } else {
      if (isAnOperator(op)) {
        // Clear the last input if we see an operator
        inputNumber = "";
        // Check if the last element entered is an operator and override operations accordingly.
        if ((queue.getLast() == '+' || queue.getLast() == '-' || queue.getLast() == '*')
                && (op != '=')) {
          // If the last operation is one of the above, remove it and replace it with new operator.
          // Given that the incoming operator is not an '='
          queue.removeLast();
        }
      } else {
        // If a number is coming in immediately after '=' without any operator, then
        // it is considered as a new calculator. Hence, clear.
        if (queue.getLast() == '=') {
          clearSmart();
        }
        inputNumber += op;
      }
      return true;
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
    Character lastKnownQueueValue = null;
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
            //val1 = convertStringToInt(String.valueOf(number));
            sign = qVar;
            number.setLength(0);
            if (qVar != '=') {
              result += qVar;
            } else {
              isLastSignAnEquals = true;
            }
          } else {
            if (sign == '=' && qVar != '=') {
              sign = qVar;
            } else if (sign == '=') {
              // 3+2=== case
              val1 = calculate(val1, lastVal2, lastValidOperation);
              sign = qVar;
              result = String.valueOf(val1);
            } else if ((lastKnownQueueValue == '+'
                    || lastKnownQueueValue == '-'
                    || lastKnownQueueValue == '*') && qVar == '=') {
              // 32+2=+= case
              val2 = val1;
              lastVal2 = val2;
              val1 = calculate(val1, val1, lastKnownQueueValue);
              lastValidOperation = sign;
              sign = qVar;
              result = String.valueOf(val1);
            } else {
              val2 = Integer.parseInt(String.valueOf(number));
              lastVal2 = val2;
              //val2 = convertStringToInt(String.valueOf(number));
              val1 = calculate(val1, val2, sign);
              lastValidOperation = sign;
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
        lastKnownQueueValue = qVar;
      }
    }
    optimizeQueue();
  }

  @Override
  public Calculator input(char op) throws IllegalArgumentException {
    // Clear the cache if the 'C' or 'c' command is provided.
    if (op == 'C') {
      clearSmart();
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
        if (!blockInput) {
          // Take input only if flag is not set even-though input is valid.
          queue.add(op);
        }
      }
    }
    performCalculation();
    return this;
  }

}
