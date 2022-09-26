package calculator;

public abstract class AbstractCalculator implements Calculator {
  protected char [] validOperators = {'+', '-', '*', '='};

  protected boolean isAnOperand(char op) {
    int opVal = Character.getNumericValue(op);
    if (opVal >= 0 && opVal <= 9) {
      return true;
    } else {
      return false;
    }
  }

  protected boolean isAnOperator(char op) {
    for (char validOperators : validOperators) {
      if (op == validOperators) {
        return true;
      }
    }
    return false;
  }

  protected boolean checkIfInputIsValid(char op) {
    if (isAnOperand(op) || isAnOperator(op)) {
      return true;
    } else {
      return false;
    }
  }

  protected int Calculate(int num1, int num2, Character sign) throws RuntimeException {
    long result = 0;
    if (sign == '+') {
      result = (long) num1 + num2;
    } else if (sign == '-') {
      result = (long) num1 - num2;
    } else if (sign == '*') {
      result = (long) num1 * num2;
    }

    if(result > Integer.MAX_VALUE) {
      // Overflow
      result = 0;
    }
    return (int) result;
  }

}
