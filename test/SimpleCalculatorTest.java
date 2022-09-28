import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SimpleCalculatorTest extends AbstractCalculatorTest {
  private Calculator simpleCalc;

  protected Calculator calcObj() {
    return new SimpleCalculator();
  }

  @Before
  public void setup() {
    simpleCalc = calcObj();
  }

  /**
   * JUnit test to verify if the '+' operator at the beginning throws exception.
   */
  @Test
  public void testAddOperatorInputAtBeginning() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b++
   */
  @Test
  public void testInvalidSequence1() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('+');
    assertEquals("5+", calc.getResult());

    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5+", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5+5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5+57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5+570", calc.getResult());

    calc = calc.input('=');
    assertEquals("575", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b+-
   */
  @Test
  public void testInvalidSequence2() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('+');
    assertEquals("5+", calc.getResult());

    try {
      calc = calc.input('-');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5+", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5+5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5+57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5+570", calc.getResult());

    calc = calc.input('=');
    assertEquals("575", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b+*
   */
  @Test
  public void testInvalidSequence3() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('+');
    assertEquals("5+", calc.getResult());

    try {
      calc = calc.input('*');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5+", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5+5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5+57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5+570", calc.getResult());

    calc = calc.input('=');
    assertEquals("575", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b-+
   */
  @Test
  public void testInvalidSequence4() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('-');
    assertEquals("5-", calc.getResult());

    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5-", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5-5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5-57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5-570", calc.getResult());

    calc = calc.input('=');
    assertEquals("-565", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b--
   */
  @Test
  public void testInvalidSequence5() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('-');
    assertEquals("5-", calc.getResult());

    try {
      calc = calc.input('-');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5-", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5-5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5-57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5-570", calc.getResult());

    calc = calc.input('=');
    assertEquals("-565", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b-*
   */
  @Test
  public void testInvalidSequence6() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('-');
    assertEquals("5-", calc.getResult());

    try {
      calc = calc.input('*');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5-", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5-5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5-57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5-570", calc.getResult());

    calc = calc.input('=');
    assertEquals("-565", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b*+
   */
  @Test
  public void testInvalidSequence7() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('*');
    assertEquals("5*", calc.getResult());

    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5*", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5*5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5*57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5*570", calc.getResult());

    calc = calc.input('=');
    assertEquals("2850", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b*-
   */
  @Test
  public void testInvalidSequence8() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('*');
    assertEquals("5*", calc.getResult());

    try {
      calc = calc.input('-');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5*", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5*5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5*57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5*570", calc.getResult());

    calc = calc.input('=');
    assertEquals("2850", calc.getResult());
  }

  /**
   * JUnit test to verify if the invalid sequence for simple calculator
   * Case: a+b**
   */
  @Test
  public void testInvalidSequence9() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());
    try {
      calc = calc.input('+');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }
    calc = calc.input('3');
    assertEquals("3", calc.getResult());

    calc = calc.input('+');
    assertEquals("3+", calc.getResult());

    calc = calc.input('2');
    assertEquals("3+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('*');
    assertEquals("5*", calc.getResult());

    try {
      calc = calc.input('*');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5*", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('5');
    assertEquals("5*5", calc.getResult());

    calc = calc.input('7');
    assertEquals("5*57", calc.getResult());

    calc = calc.input('0');
    assertEquals("5*570", calc.getResult());

    calc = calc.input('=');
    assertEquals("2850", calc.getResult());
  }

  /**
   * JUnit test to verify if the equals input in simple calculator.
   * Case: a+b**
   */
  @Test
  public void testEqualsSign() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('5');
    assertEquals("5", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('=');
    assertEquals("5", calc.getResult());

    calc = calc.input('+');
    assertEquals("5+", calc.getResult());

    try {
      calc = calc.input('=');
      fail("Expected exception did not occur.");
    } catch (IllegalArgumentException e) {
      assertEquals("5+", calc.getResult());
      assertEquals("Invalid sequence", e.getMessage());
    }

    calc = calc.input('7');
    assertEquals("5+7", calc.getResult());

    calc = calc.input('=');
    assertEquals("12", calc.getResult());

    calc = calc.input('=');
    assertEquals("12", calc.getResult());

    calc = calc.input('=');
    assertEquals("12", calc.getResult());

    calc = calc.input('*');
    assertEquals("12*", calc.getResult());

    calc = calc.input('1');
    assertEquals("12*1", calc.getResult());

    calc = calc.input('0');
    assertEquals("12*10", calc.getResult());

    calc = calc.input('=');
    assertEquals("120", calc.getResult());

    calc = calc.input('=');
    assertEquals("120", calc.getResult());

    calc = calc.input('=');
    assertEquals("120", calc.getResult());

    calc = calc.input('-');
    assertEquals("120-", calc.getResult());

    calc = calc.input('1');
    assertEquals("120-1", calc.getResult());

    calc = calc.input('0');
    assertEquals("120-10", calc.getResult());

    calc = calc.input('=');
    assertEquals("110", calc.getResult());

    calc = calc.input('=');
    assertEquals("110", calc.getResult());

    calc = calc.input('=');
    assertEquals("110", calc.getResult());

    calc = calc.input('*');
    assertEquals("110*", calc.getResult());

    calc = calc.input('2');
    assertEquals("110*2", calc.getResult());

    calc = calc.input('=');
    assertEquals("220", calc.getResult());

    calc = calc.input('=');
    assertEquals("220", calc.getResult());

    calc = calc.input('=');
    assertEquals("220", calc.getResult());

  }

}