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
 * </ul>
 */
public class SmartCalculator extends AbstractCalculator {
  private int lastVal2 = 0;
  private Character lastValidOperation = null;

  @Override
  protected boolean checkSequenceElsePart(char op) {
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
        clear();
        lastValidOperation = null;
      }
      inputNumber += op;
    }
    return true;
  }

  @Override
  protected void performCalculatorElsePart(Character qVar) {
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
