package calculator;

import java.util.Deque;
import java.util.LinkedList;

public class SimpleCalculator extends AbstractCalculator {
  private Deque<Character> q = new LinkedList<>();
  private String result = "";
  private String inputNumber = "";

  private void clear() {
    result = "";
    inputNumber = "";
    q.clear();
  }

  private void optimizeQueue () {
    q.clear();
    for(int i=0; i<result.length(); i++) {
      q.add(result.charAt(i));
    }
  }

  private boolean checkIfSequenceIsValid(char op) {
    if (q.isEmpty()) {
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
        if (isAnOperand(q.getLast())) {
          return true;
        } else {
          // This means the last element in the queue is an operator
          if (q.getLast() == '=') {
            // The calculator does allow inputting "=" multiple times.
            // Calculator also allows operators after '='.
            return true;
          } else {
            // Cannot also two operators. Invalid sequence.
            return false;
          }
        }
      } else {
        // If a number is coming in immediately after '=' without any operator, then
        // it is considered as a new calculator. Hence, clear.
        if(q.getLast() == '=') {
          clear();
        }
        inputNumber += op;
        return true;
      }
    }
  }

  private void performCalculation() {
    int val1 = 0;
    int val2 = 0;
    Character sign = null;
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
            if (sign == '=') {
              sign = ch;
              if (ch != '=') {
                result += ch;
              }
            } else {
              val2 = Integer.parseInt(String.valueOf(number));
              //val2 = convertStringToInt(String.valueOf(number));
              val1 = Calculate(val1, val2, sign);
              sign = ch;
              result = String.valueOf(val1);
              if (ch != '=') {
                result += ch;
              }
            }
            number.setLength(0);
          }
        }
      }
    }
    //optimizeQueue();
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
        if(inputNumber.length() > 0) {
          long val = Long.parseLong(inputNumber);
          if(val > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("The entered value is causing overflow");
          }
        }
        q.add(op);
      }
    }
    performCalculation();
    return this;
  }

  private int convertStringToInt (String number) {
    long result = Long.parseLong(number);
    if(result > Integer.MAX_VALUE) {
      throw new RuntimeException("The entered value is causing overflow");
    }
    return (int) result;
  }

  @Override
  public String getResult() throws RuntimeException {
    return result;
  }

  public static void main(String[] args) {
    Calculator obj = new SimpleCalculator();
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
