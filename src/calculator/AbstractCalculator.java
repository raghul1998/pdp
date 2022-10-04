package calculator;

import java.util.Deque;
import java.util.LinkedList;

/**
 * This class is an abstract class for all the other calculator classes. This class also implements
 * the Calculator interface.
 * This has the following variables.
 * <ul>
 *   <li> ValidOperators - A char array that holds the valid operators that can be inputted </li>
 *   <li> queue - a queue that holds the input </li>
 *   <li> result - holds the value of the result as a string </li>
 *   <li> inputNumber - a variable to hold a single operand that was entered </li>
 *   <li> isLastSignAnEquals - this boolean variable helps to track the equals operator </li>
 * </ul>
 */
public abstract class AbstractCalculator implements Calculator {
  protected char[] validOperators = {'+', '-', '*', '='};

  protected Deque<Character> queue = new LinkedList<>();
  protected String result = "";
  protected String inputNumber = "";
  protected boolean isLastSignAnEquals = false;

  protected boolean blockInput = false;

  /**
   * This method checks if the value passed is an operand or not. Meaning it returns true if the
   * value passes is a number, else returns false.
   *
   * @param op character that is passed to check if it is an operand or not
   * @return true if the passed argument is an operand, else returns false
   */
  protected boolean isAnOperand(char op) {
    int opVal = Character.getNumericValue(op);
    return opVal >= 0 && opVal <= 9;
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
    return isAnOperand(op) || isAnOperator(op);
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
  protected int calculate(int num1, int num2, Character sign) {
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

  @Override
  public String getResult() {
    return this.result;
  }

  /**
   * This method is a helper function that helps in optimizing the queue as soon as an operation
   * is performed. This helps reduce the overhead of storing all the previous input values.
   */
  protected void optimizeQueue() {
    this.queue.clear();
    for (int i = 0; i < this.result.length(); i++) {
      this.queue.add(this.result.charAt(i));
    }
    if (this.isLastSignAnEquals) {
      this.queue.add('=');
    }
  }

  /**
   * This method is a helper function has clears the state of the calculator by clearing certain
   * variables and the queue.
   */
  protected void clear() {
    this.result = "";
    this.inputNumber = "";
    this.queue.clear();
  }

  /**
   * The calculator has certain rules which enforces certain sequence. This method checks if the
   * sequence is valid and as per rule.
   *
   * @param op Input value entered by the user
   * @return Returns true if the sequence is valid, else returns false
   */
  protected boolean checkIfSequenceIsValid(char op) {
    blockInput = false;
    if (queue.isEmpty()) {
      if (isAnOperand(op)) {
        inputNumber += op;
        return true;
      } else {
        if (this instanceof SmartCalculator) {
          if (op == '+') {
            blockInput = true;
            return true;
          } else {
            return false;
          }
        }
        return false;
      }
    } else {
      return checkSequenceElsePart(op);
    }
  }

  protected abstract boolean checkSequenceElsePart(char op);

  /**
   * This is a helper method that performs the calculation. It goes through the queue, analysis
   * what operation to be performed and performs them accordingly and also stores the result as
   * string in the global variable.
   */
  protected void performCalculation() {
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
        if(this instanceof SmartCalculator) {
          if(!blockInput) {
            queue.add(op);
          }
        } else {
          queue.add(op);
        }
      }
    }
    performCalculation();
    return this;
  }

}
