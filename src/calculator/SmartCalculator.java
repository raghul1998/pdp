package calculator;

import java.util.Deque;
import java.util.LinkedList;

public class SmartCalculator extends AbstractCalculator {
  private Deque<Character> q = new LinkedList<>();
  private String result = "";
  private String inputNumber = "";
  private boolean blockInput = false;

  private void clear() {
    result = "";
    inputNumber = "";
    q.clear();
  }

  private boolean checkIfSequenceIsValid(char op) {
    blockInput = false;
    if (q.isEmpty()) {
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
        if ((q.getLast() == '+' || q.getLast() == '-' || q.getLast() == '*') && (op != '=')) {
          // If the last operation is one of the above, remove it and replace it with new operator.
          // Given that the incoming operator is not an '='
          q.removeLast();
        }
      } else {
        // If a number is coming in immediately after '=' without any operator, then
        // it is considered as a new calculator. Hence, clear.
        if (q.getLast() == '=') {
          clear();
        }
        inputNumber += op;
      }
      return true;
    }
  }

  private void performCalculation() {
    int val1 = 0;
    int val2 = 0;
    Character sign = null;
    Character lastValidOperation = null;
    Character lastKnownQueueValue = null;
    StringBuilder number = new StringBuilder();
    result = "";
    if (q.isEmpty()) {
      result = "";
    } else {
      for (Character ch : q) {
        if (isAnOperand(ch)) {
          number.append(ch);
          result += ch;
        } else {
          if (sign == null) {
            val1 = Integer.parseInt(String.valueOf(number));
            //val1 = convertStringToInt(String.valueOf(number));
            sign = ch;
            number.setLength(0);
            if (ch != '=') {
              result += ch;
            }
          } else {
            if (sign == '=' && ch != '=') {
              sign = ch;
            } else if (sign == '=' && ch == '=') {
              // 3+2=== case
              val1 = Calculate(val1, val2, lastValidOperation);
              sign = ch;
              result = String.valueOf(val1);
            } else if ((lastKnownQueueValue == '+'
                    || lastKnownQueueValue == '-'
                    || lastKnownQueueValue == '*') && ch == '=') {
              // 32+2=+= case
              val2 = val1;
              val1 = Calculate(val1, val1, lastKnownQueueValue);
              lastValidOperation = sign;
              sign = ch;
              result = String.valueOf(val1);
            } else {
              val2 = Integer.parseInt(String.valueOf(number));
              //val2 = convertStringToInt(String.valueOf(number));
              val1 = Calculate(val1, val2, sign);
              lastValidOperation = sign;
              sign = ch;
              result = String.valueOf(val1);
            }
            if (ch != '=') {
              result += ch;
            }
            number.setLength(0);
          }
        }
        lastKnownQueueValue = ch;
      }
    }
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
        if (!blockInput) {
          // Take input only if flag is not set even-though input is valid.
          q.add(op);
        }
      }
    }
    performCalculation();
    return this;
  }

  @Override
  public String getResult() {
    return result;
  }

  public static void main(String[] args) {
    Calculator obj = new SmartCalculator();
    System.out.println(obj.getResult());
    obj.input('3');
    System.out.println(obj.getResult());
    obj.input('2');
    System.out.println(obj.getResult());
    obj.input('=');
    System.out.println(obj.getResult());
    obj.input('=');
    System.out.println(obj.getResult());
    obj.input('=');
    System.out.println(obj.getResult());

  }
}
