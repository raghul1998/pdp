import org.junit.Test;

import java.util.Random;

import calculator.Calculator;

import static org.junit.Assert.*;

/**
 * This class is an abstract class for all the other calculator test classes.
 * This class has the following variables.
 * <ul>
 *   <li>
 *     calcObj - calculator object
 *   </li>
 * </ul>
 */
public abstract class AbstractCalculatorTest {
  protected abstract Calculator calcObj();

  /**
   * A JUnit test to verify the initial state of an object.
   */
  @Test
  public void testInitialState() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
  }

  /**
   * A JUnit test to verify the negative input.
   */
  @Test
  public void testValidNegativeInput() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('-');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
  }

  /**
   * A JUnit test to verify the invalid inputs at beginning.
   */
  @Test
  public void testInvalidInputsAtBeginning() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('*');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    try {
      calc = calc.input('=');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    try {
      calc = calc.input('^');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid character ^ entered.", e.getMessage());
    }

    try {
      calc = calc.input('/');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid character / entered.", e.getMessage());
    }

    try {
      calc = calc.input('c');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid character c entered.", e.getMessage());
    }
  }

  /**
   * A JUnit test to verify the invalid inputs after valid operands.
   */
  @Test
  public void testInvalidInputsAfterValidOperand() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('1');
    assertEquals("1", calc.getResult());

    calc = calc.input('+');
    assertEquals("1+", calc.getResult());

    calc = calc.input('2');
    assertEquals("1+2", calc.getResult());

    calc = calc.input('1');
    assertEquals("1+21", calc.getResult());

    try {
      calc = calc.input('^');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("1+21", calc.getResult());
      assertEquals("Invalid character ^ entered.", e.getMessage());
    }

    try {
      calc = calc.input('/');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("1+21", calc.getResult());
      assertEquals("Invalid character / entered.", e.getMessage());
    }

    try {
      calc = calc.input('c');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("1+21", calc.getResult());
      assertEquals("Invalid character c entered.", e.getMessage());
    }

    calc = calc.input('=');
    assertEquals("22", calc.getResult());

    calc = calc.input('+');
    assertEquals("22+", calc.getResult());

    calc = calc.input('2');
    assertEquals("22+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("24", calc.getResult());
  }

  /**
   * A JUnit test to verify the invalid inputs after valid operators.
   */
  @Test
  public void testInvalidInputsAfterValidOperators() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('1');
    assertEquals("1", calc.getResult());

    calc = calc.input('+');
    assertEquals("1+", calc.getResult());

    try {
      calc = calc.input('^');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("1+", calc.getResult());
      assertEquals("Invalid character ^ entered.", e.getMessage());
    }

    try {
      calc = calc.input('/');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("1+", calc.getResult());
      assertEquals("Invalid character / entered.", e.getMessage());
    }

    try {
      calc = calc.input('c');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("1+", calc.getResult());
      assertEquals("Invalid character c entered.", e.getMessage());
    }

    calc = calc.input('2');
    assertEquals("1+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("3", calc.getResult());

    calc = calc.input('*');
    assertEquals("3*", calc.getResult());

    try {
      calc = calc.input('^');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("3*", calc.getResult());
      assertEquals("Invalid character ^ entered.", e.getMessage());
    }

    try {
      calc = calc.input('/');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("3*", calc.getResult());
      assertEquals("Invalid character / entered.", e.getMessage());
    }

    try {
      calc = calc.input('c');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("3*", calc.getResult());
      assertEquals("Invalid character c entered.", e.getMessage());
    }

    calc = calc.input('2');
    assertEquals("3*2", calc.getResult());

    calc = calc.input('-');
    assertEquals("6-", calc.getResult());

    try {
      calc = calc.input('^');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("6-", calc.getResult());
      assertEquals("Invalid character ^ entered.", e.getMessage());
    }

    try {
      calc = calc.input('/');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("6-", calc.getResult());
      assertEquals("Invalid character / entered.", e.getMessage());
    }

    try {
      calc = calc.input('c');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("6-", calc.getResult());
      assertEquals("Invalid character c entered.", e.getMessage());
    }

    calc = calc.input('1');
    assertEquals("6-1", calc.getResult());

    calc = calc.input('-');
    assertEquals("5-", calc.getResult());

    calc = calc.input('1');
    assertEquals("5-1", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());
  }

  /**
   * A JUnit test to verify a simple sequence.
   * Case: 33+123-23*23=*23-43=+234*2-34=
   */
  @Test
  public void testVerifyValidSequence() {
    Calculator calc = calcObj();

    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('3');
    assertEquals("33", calc.getResult());

    calc = calc.input('+');
    assertEquals("33+", calc.getResult());

    calc = calc.input('1');
    assertEquals("33+1", calc.getResult());

    calc = calc.input('2');
    assertEquals("33+12", calc.getResult());

    calc = calc.input('3');
    assertEquals("33+123", calc.getResult());

    calc = calc.input('-');
    assertEquals("156-", calc.getResult());

    calc = calc.input('2');
    assertEquals("156-2", calc.getResult());

    calc = calc.input('3');
    assertEquals("156-23", calc.getResult());

    calc = calc.input('*');
    assertEquals("133*", calc.getResult());

    calc = calc.input('2');
    assertEquals("133*2", calc.getResult());

    calc = calc.input('3');
    assertEquals("133*23", calc.getResult());

    calc = calc.input('=');
    assertEquals("3059", calc.getResult());

    calc = calc.input('*');
    assertEquals("3059*", calc.getResult());

    calc = calc.input('2');
    assertEquals("3059*2", calc.getResult());

    calc = calc.input('3');
    assertEquals("3059*23", calc.getResult());

    calc = calc.input('-');
    assertEquals("70357-", calc.getResult());

    calc = calc.input('4');
    assertEquals("70357-4", calc.getResult());

    calc = calc.input('3');
    assertEquals("70357-43", calc.getResult());

    calc = calc.input('=');
    assertEquals("70314", calc.getResult());

    calc = calc.input('+');
    assertEquals("70314+", calc.getResult());

    calc = calc.input('2');
    assertEquals("70314+2", calc.getResult());

    calc = calc.input('3');
    assertEquals("70314+23", calc.getResult());

    calc = calc.input('4');
    assertEquals("70314+234", calc.getResult());

    calc = calc.input('*');
    assertEquals("70548*", calc.getResult());

    calc = calc.input('2');
    assertEquals("70548*2", calc.getResult());

    calc = calc.input('-');
    assertEquals("141096-", calc.getResult());

    calc = calc.input('3');
    assertEquals("141096-3", calc.getResult());

    calc = calc.input('4');
    assertEquals("141096-34", calc.getResult());

    calc = calc.input('=');
    assertEquals("141062", calc.getResult());
  }

  /**
   * A JUnit test to verify the clear input.
   * Case: C, aC, a+C, a+bC, a+b=C, a+b-dCa+b=, a+b=C+, a+b=C-, a+b=*, a
   */
  @Test
  public void testClearInputAtAllPlaces() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('2');
    assertEquals("32", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('3');
    assertEquals("33", calc.getResult());

    calc = calc.input('+');
    assertEquals("33+", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('4');
    assertEquals("4", calc.getResult());

    calc = calc.input('2');
    assertEquals("42", calc.getResult());

    calc = calc.input('+');
    assertEquals("42+", calc.getResult());

    calc = calc.input('8');
    assertEquals("42+8", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('+');
    assertEquals("2+", calc.getResult());

    calc = calc.input('1');
    assertEquals("2+1", calc.getResult());

    calc = calc.input('9');
    assertEquals("2+19", calc.getResult());

    calc = calc.input('+');
    assertEquals("21+", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('+');
    assertEquals("2+", calc.getResult());

    calc = calc.input('1');
    assertEquals("2+1", calc.getResult());

    calc = calc.input('=');
    assertEquals("3", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    try {
      calc = calc.input('-');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    try {
      calc = calc.input('*');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    try {
      calc = calc.input('c');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid character c entered.", e.getMessage());
    }

    try {
      calc = calc.input('/');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid character / entered.", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5", calc.getResult());

    calc = calc.input('5');
    assertEquals("55", calc.getResult());

    calc = calc.input('5');
    assertEquals("555", calc.getResult());

    calc = calc.input('-');
    assertEquals("555-", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('7');
    assertEquals("7", calc.getResult());

    calc = calc.input('9');
    assertEquals("79", calc.getResult());
  }

  /**
   * A JUnit test to verify overflow inputs at beginning border number
   * Case: 2147483647
   */
  @Test
  public void testOverflowOperandAtBeginningBorder() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    assertEquals("2", calc.getResult());
    calc.input('1');
    assertEquals("21", calc.getResult());
    calc.input('4');
    assertEquals("214", calc.getResult());
    calc.input('7');
    assertEquals("2147", calc.getResult());
    calc.input('4');
    assertEquals("21474", calc.getResult());
    calc.input('8');
    assertEquals("214748", calc.getResult());
    calc.input('3');
    assertEquals("2147483", calc.getResult());
    calc.input('6');
    assertEquals("21474836", calc.getResult());
    calc.input('4');
    assertEquals("214748364", calc.getResult());
    calc.input('7');
    assertEquals("2147483647", calc.getResult());
  }

  /**
   * A JUnit test to verify overflow inputs at beginning just overflow
   * Case: 2147483648
   */
  @Test
  public void testOverflowOperandAtBeginningBorderJustOverflow() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    assertEquals("2", calc.getResult());
    calc.input('1');
    assertEquals("21", calc.getResult());
    calc.input('4');
    assertEquals("214", calc.getResult());
    calc.input('7');
    assertEquals("2147", calc.getResult());
    calc.input('4');
    assertEquals("21474", calc.getResult());
    calc.input('8');
    assertEquals("214748", calc.getResult());
    calc.input('3');
    assertEquals("2147483", calc.getResult());
    calc.input('6');
    assertEquals("21474836", calc.getResult());
    calc.input('4');
    assertEquals("214748364", calc.getResult());
    try {
      calc.input('8');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("214748364", calc.getResult());
      assertEquals("The entered value is causing overflow", e.getMessage());
    }

    calc.input('+');
    assertEquals("214748364+", calc.getResult());

    calc.input('6');
    assertEquals("214748364+6", calc.getResult());

    calc.input('=');
    assertEquals("214748370", calc.getResult());
  }

  /**
   * A JUnit test to verify overflow inputs at beginning big number
   */
  @Test
  public void testOverflowOperandAtBeginningBorderHugeOverflow() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('9');
    assertEquals("9", calc.getResult());
    calc.input('9');
    assertEquals("99", calc.getResult());
    calc.input('2');
    assertEquals("992", calc.getResult());
    calc.input('7');
    assertEquals("9927", calc.getResult());
    calc.input('4');
    assertEquals("99274", calc.getResult());
    calc.input('3');
    assertEquals("992743", calc.getResult());
    calc.input('5');
    assertEquals("9927435", calc.getResult());
    calc.input('3');
    assertEquals("99274353", calc.getResult());
    calc.input('8');
    assertEquals("992743538", calc.getResult());
    try {
      calc.input('0');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("992743538", calc.getResult());
      assertEquals("The entered value is causing overflow", e.getMessage());
    }

    calc.input('+');
    assertEquals("992743538+", calc.getResult());

    calc.input('6');
    assertEquals("992743538+6", calc.getResult());

    calc.input('=');
    assertEquals("992743544", calc.getResult());
  }


  /**
   * A JUnit test to verify overflow inputs
   * case: a+b=(ln)
   */
  @Test
  public void testOverflowOperandOverall1() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    calc.input('2');
    calc.input('+');
    calc.input('1');
    calc.input('9');
    calc.input('9');
    assertEquals("22+199", calc.getResult());

    calc.input('=');
    assertEquals("221", calc.getResult());

    calc.input('9');
    assertEquals("9", calc.getResult());
    calc.input('9');
    assertEquals("99", calc.getResult());
    calc.input('2');
    assertEquals("992", calc.getResult());
    calc.input('7');
    assertEquals("9927", calc.getResult());
    calc.input('4');
    assertEquals("99274", calc.getResult());
    calc.input('3');
    assertEquals("992743", calc.getResult());
    calc.input('5');
    assertEquals("9927435", calc.getResult());
    calc.input('3');
    assertEquals("99274353", calc.getResult());
    calc.input('8');
    assertEquals("992743538", calc.getResult());
    try {
      calc.input('0');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("992743538", calc.getResult());
      assertEquals("The entered value is causing overflow", e.getMessage());
    }

    calc.input('+');
    assertEquals("992743538+", calc.getResult());

    calc.input('6');
    assertEquals("992743538+6", calc.getResult());

    calc.input('=');
    assertEquals("992743544", calc.getResult());
  }

  /**
   * A JUnit test to verify overflow inputs
   * case: a+b=+(ln)
   */
  @Test
  public void testOverflowOperandOverall2() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    calc.input('2');
    calc.input('+');
    calc.input('1');
    calc.input('9');
    calc.input('9');
    assertEquals("22+199", calc.getResult());

    calc.input('=');
    assertEquals("221", calc.getResult());

    calc.input('+');
    assertEquals("221+", calc.getResult());

    calc.input('9');
    assertEquals("221+9", calc.getResult());
    calc.input('9');
    assertEquals("221+99", calc.getResult());
    calc.input('2');
    assertEquals("221+992", calc.getResult());
    calc.input('7');
    assertEquals("221+9927", calc.getResult());
    calc.input('4');
    assertEquals("221+99274", calc.getResult());
    calc.input('3');
    assertEquals("221+992743", calc.getResult());
    calc.input('5');
    assertEquals("221+9927435", calc.getResult());
    calc.input('3');
    assertEquals("221+99274353", calc.getResult());
    calc.input('8');
    assertEquals("221+992743538", calc.getResult());
    try {
      calc.input('0');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("221+992743538", calc.getResult());
      assertEquals("The entered value is causing overflow", e.getMessage());
    }

    calc.input('+');
    assertEquals("992743759+", calc.getResult());

    calc.input('6');
    assertEquals("992743759+6", calc.getResult());

    calc.input('=');
    assertEquals("992743765", calc.getResult());
  }

  /**
   * A JUnit test to verify overflow inputs
   * case: a+b=-(ln)
   */
  @Test
  public void testOverflowOperandOverall3() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    calc.input('2');
    calc.input('+');
    calc.input('1');
    calc.input('9');
    calc.input('9');
    assertEquals("22+199", calc.getResult());

    calc.input('=');
    assertEquals("221", calc.getResult());

    calc.input('-');
    assertEquals("221-", calc.getResult());

    calc.input('9');
    assertEquals("221-9", calc.getResult());
    calc.input('9');
    assertEquals("221-99", calc.getResult());
    calc.input('2');
    assertEquals("221-992", calc.getResult());
    calc.input('7');
    assertEquals("221-9927", calc.getResult());
    calc.input('4');
    assertEquals("221-99274", calc.getResult());
    calc.input('3');
    assertEquals("221-992743", calc.getResult());
    calc.input('5');
    assertEquals("221-9927435", calc.getResult());
    calc.input('3');
    assertEquals("221-99274353", calc.getResult());
    calc.input('8');
    assertEquals("221-992743538", calc.getResult());
    try {
      calc.input('0');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("221-992743538", calc.getResult());
      assertEquals("The entered value is causing overflow", e.getMessage());
    }

    calc.input('+');
    assertEquals("-992743317+", calc.getResult());

    calc.input('6');
    assertEquals("-992743317+6", calc.getResult());

    calc.input('=');
    assertEquals("-992743311", calc.getResult());
  }

  /**
   * A JUnit test to verify overflow inputs
   * case: a+b=*(ln)
   */
  @Test
  public void testOverflowOperandOverall4() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('1');
    calc.input('+');
    calc.input('1');
    assertEquals("1+1", calc.getResult());

    calc.input('=');
    assertEquals("2", calc.getResult());

    calc.input('*');
    assertEquals("2*", calc.getResult());

    calc.input('9');
    assertEquals("2*9", calc.getResult());
    calc.input('9');
    assertEquals("2*99", calc.getResult());
    calc.input('2');
    assertEquals("2*992", calc.getResult());
    calc.input('7');
    assertEquals("2*9927", calc.getResult());
    calc.input('4');
    assertEquals("2*99274", calc.getResult());
    calc.input('3');
    assertEquals("2*992743", calc.getResult());
    calc.input('5');
    assertEquals("2*9927435", calc.getResult());
    calc.input('3');
    assertEquals("2*99274353", calc.getResult());
    calc.input('8');
    assertEquals("2*992743538", calc.getResult());
    try {
      calc.input('0');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("2*992743538", calc.getResult());
      assertEquals("The entered value is causing overflow", e.getMessage());
    }

    calc.input('+');
    assertEquals("1985487076+", calc.getResult());

    calc.input('6');
    assertEquals("1985487076+6", calc.getResult());

    calc.input('=');
    assertEquals("1985487082", calc.getResult());
  }

  /**
   * A JUnit test to result overflow
   */
  @Test
  public void testValidResultOverflow1() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    assertEquals("2", calc.getResult());
    calc.input('1');
    assertEquals("21", calc.getResult());
    calc.input('4');
    assertEquals("214", calc.getResult());
    calc.input('7');
    assertEquals("2147", calc.getResult());
    calc.input('4');
    assertEquals("21474", calc.getResult());
    calc.input('8');
    assertEquals("214748", calc.getResult());
    calc.input('3');
    assertEquals("2147483", calc.getResult());
    calc.input('6');
    assertEquals("21474836", calc.getResult());
    calc.input('4');
    assertEquals("214748364", calc.getResult());
    calc.input('7');
    assertEquals("2147483647", calc.getResult());
    calc.input('+');
    assertEquals("2147483647+", calc.getResult());
    calc.input('1');
    assertEquals("2147483647+1", calc.getResult());
    calc.input('=');
    assertEquals("0", calc.getResult());
    calc.input('-');
    assertEquals("0-", calc.getResult());
    calc.input('2');
    assertEquals("0-2", calc.getResult());
    calc.input('+');
    assertEquals("-2+", calc.getResult());
  }

  /**
   * A JUnit test to result overflow
   */
  @Test
  public void testValidResultOverflow2() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc.input('2');
    assertEquals("2", calc.getResult());

    calc.input('-');
    assertEquals("2-", calc.getResult());

    calc.input('2');
    assertEquals("2-2", calc.getResult());

    calc.input('+');
    assertEquals("0+", calc.getResult());

    calc.input('2');
    assertEquals("0+2", calc.getResult());
    calc.input('1');
    assertEquals("0+21", calc.getResult());
    calc.input('4');
    assertEquals("0+214", calc.getResult());
    calc.input('7');
    assertEquals("0+2147", calc.getResult());
    calc.input('4');
    assertEquals("0+21474", calc.getResult());
    calc.input('8');
    assertEquals("0+214748", calc.getResult());
    calc.input('3');
    assertEquals("0+2147483", calc.getResult());
    calc.input('6');
    assertEquals("0+21474836", calc.getResult());
    calc.input('4');
    assertEquals("0+214748364", calc.getResult());
    calc.input('7');
    assertEquals("0+2147483647", calc.getResult());
    calc.input('*');
    assertEquals("2147483647*", calc.getResult());
    calc.input('2');
    assertEquals("2147483647*2", calc.getResult());
    calc.input('=');
    assertEquals("0", calc.getResult());
    calc.input('-');
    assertEquals("0-", calc.getResult());
    calc.input('2');
    assertEquals("0-2", calc.getResult());
    calc.input('+');
    assertEquals("-2+", calc.getResult());
  }

  /**
   * A JUnit test that performs fuzzy testing.
   */
  @Test
  public void testFuzzyInput() {
    Random random = new Random();
    random.setSeed(12345L);

    Character previousSign = null;
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    Character array[] = {'+', '-', '*', '='};
    long resultVal = 3;
    String resultString = "";

    calc.input('1');
    calc.input('+');
    calc.input('2');
    calc.input('+');
    assertEquals("3+", calc.getResult());

    for (int i = 0; i < 10000; i++) {
      Character randValue = Character.forDigit(random.nextInt(9), 10);
      if (randValue == '0') {
        continue;
      }
      int sign = random.nextInt(3);
      calc.input(randValue);
      calc.input(array[sign]);

      if (previousSign == null) {
        resultVal = Character.getNumericValue(randValue) + 3;
      } else {
        if (previousSign == '+') {
          resultVal += Character.getNumericValue(randValue);
        } else if (previousSign == '-') {
          resultVal -= Character.getNumericValue(randValue);
        } else if (previousSign == '*') {
          resultVal *= Character.getNumericValue(randValue);
        }
        if (resultVal > Integer.MAX_VALUE || resultVal < -Integer.MAX_VALUE) {
          resultVal = 0;
        }
      }

      if (array[sign] != '=') {
        resultString = String.format("%s%s", resultVal, array[sign]);
      } else {
        resultString = String.format("%s", resultVal);
      }
      assertEquals(resultString, calc.getResult());
      previousSign = array[sign];
    }
  }
}