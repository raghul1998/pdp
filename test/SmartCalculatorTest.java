import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SmartCalculator;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the Smart Calculator class. This class also extends the Abstract
 * Calculator class.
 */
public class SmartCalculatorTest extends AbstractCalculatorTest {
  /**
   * A method that returns the smart calculator object. This is a helper function for the factory
   * method.
   *
   * @return Returns smart calculator object
   */
  protected Calculator calcObj() {
    return new SmartCalculator();
  }

  /**
   * A JUint test to verify the + operator at the beginning
   * Note: My code will not show + operator after inputting at beginning.
   * It will still show empty string.
   * Case: +a, ++a, +++a
   */
  @Test
  public void testValidAdditionInputAtBeginning() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

  }

  /**
   * A JUint test to verify the + operator with a sequence
   * Case: +a+b
   */
  @Test
  public void testValidAdditionInputAtBeginning2() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('+');
    assertEquals("2+", calc.getResult());

    calc = calc.input('2');
    assertEquals("2+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());

  }

  /**
   * A JUint test to verify the + operator with a sequence
   * Case: ++a+b
   */
  @Test
  public void testValidAdditionInputAtBeginning3() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('+');
    assertEquals("2+", calc.getResult());

    calc = calc.input('2');
    assertEquals("2+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());
  }

  /**
   * A JUint test to verify the + operator with a sequence
   * Case: +a-b
   */
  @Test
  public void testValidAdditionInputAtBeginning4() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('9');
    assertEquals("29", calc.getResult());

    calc = calc.input('-');
    assertEquals("29-", calc.getResult());

    calc = calc.input('2');
    assertEquals("29-2", calc.getResult());

    calc = calc.input('=');
    assertEquals("27", calc.getResult());

  }

  /**
   * A JUint test to verify the + operator with a sequence
   * Case: +a*b
   */
  @Test
  public void testValidAdditionInputAtBeginning5() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('7');
    assertEquals("7", calc.getResult());

    calc = calc.input('8');
    assertEquals("78", calc.getResult());

    calc = calc.input('*');
    assertEquals("78*", calc.getResult());

    calc = calc.input('2');
    assertEquals("78*2", calc.getResult());

    calc = calc.input('1');
    assertEquals("78*21", calc.getResult());

    calc = calc.input('=');
    assertEquals("1638", calc.getResult());
  }

  /**
   * A JUnit test to verify multiple '=' inputs.
   * Case: a+b===, a+b==+a==
   */
  @Test
  public void testMultipleEqualsInputAddition() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('1');
    assertEquals("1", calc.getResult());

    calc = calc.input('2');
    assertEquals("12", calc.getResult());

    calc = calc.input('+');
    assertEquals("12+", calc.getResult());

    calc = calc.input('1');
    assertEquals("12+1", calc.getResult());

    calc = calc.input('0');
    assertEquals("12+10", calc.getResult());

    calc = calc.input('=');
    assertEquals("22", calc.getResult());

    calc = calc.input('=');
    assertEquals("32", calc.getResult());

    calc = calc.input('=');
    assertEquals("42", calc.getResult());

    calc = calc.input('+');
    assertEquals("42+", calc.getResult());

    calc = calc.input('4');
    assertEquals("42+4", calc.getResult());

    calc = calc.input('=');
    assertEquals("46", calc.getResult());

    calc = calc.input('=');
    assertEquals("50", calc.getResult());

    calc = calc.input('=');
    assertEquals("54", calc.getResult());
  }

  /**
   * A JUnit test to verify multiple '=' inputs.
   * Case: a-b===, a-b==-a==
   */
  @Test
  public void testMultipleEqualsInputSubtract() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('1');
    assertEquals("1", calc.getResult());

    calc = calc.input('2');
    assertEquals("12", calc.getResult());

    calc = calc.input('-');
    assertEquals("12-", calc.getResult());

    calc = calc.input('1');
    assertEquals("12-1", calc.getResult());

    calc = calc.input('0');
    assertEquals("12-10", calc.getResult());

    calc = calc.input('=');
    assertEquals("2", calc.getResult());

    calc = calc.input('=');
    assertEquals("-8", calc.getResult());

    calc = calc.input('=');
    assertEquals("-18", calc.getResult());

    calc = calc.input('-');
    assertEquals("-18-", calc.getResult());

    calc = calc.input('4');
    assertEquals("-18-4", calc.getResult());

    calc = calc.input('=');
    assertEquals("-22", calc.getResult());

    calc = calc.input('=');
    assertEquals("-26", calc.getResult());

    calc = calc.input('=');
    assertEquals("-30", calc.getResult());
  }

  /**
   * A JUnit test to verify multiple '=' inputs.
   * Case: a*b===, a*b==*a==
   */
  @Test
  public void testMultipleEqualsInputMultiply() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('1');
    assertEquals("1", calc.getResult());

    calc = calc.input('2');
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
    assertEquals("1200", calc.getResult());

    calc = calc.input('=');
    assertEquals("12000", calc.getResult());

    calc = calc.input('*');
    assertEquals("12000*", calc.getResult());

    calc = calc.input('4');
    assertEquals("12000*4", calc.getResult());

    calc = calc.input('=');
    assertEquals("48000", calc.getResult());

    calc = calc.input('=');
    assertEquals("192000", calc.getResult());

    calc = calc.input('=');
    assertEquals("768000", calc.getResult());
  }

  /**
   * A JUnit test to skipping operand test for addition
   * Case: a+=, a+==, a+==+a==
   */
  @Test
  public void testSkippingOperandAddition() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('+');
    assertEquals("2+", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());

    calc = calc.input('=');
    assertEquals("6", calc.getResult());

    calc = calc.input('=');
    assertEquals("8", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('=');
    assertEquals("2", calc.getResult());

    calc = calc.input('=');
    assertEquals("2", calc.getResult());

    calc = calc.input('+');
    assertEquals("2+", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());

    calc = calc.input('=');
    assertEquals("6", calc.getResult());

    calc = calc.input('=');
    assertEquals("8", calc.getResult());
  }


  /**
   * A JUnit test to skipping operand test at beginning
   * Case: a-=, a-==, a-==-a==
   */
  @Test
  public void testSkippingOperandSubtraction() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('4');
    assertEquals("4", calc.getResult());

    calc = calc.input('-');
    assertEquals("4-", calc.getResult());

    calc = calc.input('=');
    assertEquals("0", calc.getResult());

    calc = calc.input('=');
    assertEquals("-4", calc.getResult());

    calc = calc.input('=');
    assertEquals("-8", calc.getResult());

    calc = calc.input('=');
    assertEquals("-12", calc.getResult());

    calc = calc.input('-');
    assertEquals("-12-", calc.getResult());

    calc = calc.input('1');
    assertEquals("-12-1", calc.getResult());

    calc = calc.input('=');
    assertEquals("-13", calc.getResult());

    calc = calc.input('=');
    assertEquals("-14", calc.getResult());

    calc = calc.input('=');
    assertEquals("-15", calc.getResult());

    calc = calc.input('=');
    assertEquals("-16", calc.getResult());
  }

  /**
   * A JUnit test to skipping operand test for multiply
   * Case: a*=, a*==, a*==*a==
   */
  @Test
  public void testSkippingOperandMultiply() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('*');
    assertEquals("2*", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());

    calc = calc.input('=');
    assertEquals("8", calc.getResult());

    calc = calc.input('=');
    assertEquals("16", calc.getResult());

    calc = calc.input('*');
    assertEquals("16*", calc.getResult());

    calc = calc.input('=');
    assertEquals("256", calc.getResult());

    calc = calc.input('=');
    assertEquals("4096", calc.getResult());

  }

  /**
   * A JUnit test to verify consecutive operator.
   * Case: like 1+-3=-+9*-1-4==23-*12
   */
  @Test
  public void testConsecutiveOperator() {
    Calculator calc = calcObj();
    assertEquals("", calc.getResult());

    calc = calc.input('+');
    assertEquals("", calc.getResult());

    calc = calc.input('1');
    assertEquals("1", calc.getResult());

    calc = calc.input('5');
    assertEquals("15", calc.getResult());

    calc = calc.input('+');
    assertEquals("15+", calc.getResult());

    calc = calc.input('-');
    assertEquals("15-", calc.getResult());

    calc = calc.input('*');
    assertEquals("15*", calc.getResult());

    calc = calc.input('2');
    assertEquals("15*2", calc.getResult());

    calc = calc.input('=');
    assertEquals("30", calc.getResult());

    calc = calc.input('-');
    assertEquals("30-", calc.getResult());

    calc = calc.input('*');
    assertEquals("30*", calc.getResult());

    calc = calc.input('+');
    assertEquals("30+", calc.getResult());

    calc = calc.input('2');
    assertEquals("30+2", calc.getResult());

    calc = calc.input('=');
    assertEquals("32", calc.getResult());

    calc = calc.input('=');
    assertEquals("34", calc.getResult());

    calc = calc.input('=');
    assertEquals("36", calc.getResult());

    calc = calc.input('+');
    assertEquals("36+", calc.getResult());

    calc = calc.input('=');
    assertEquals("72", calc.getResult());

    calc = calc.input('=');
    assertEquals("108", calc.getResult());

    calc = calc.input('=');
    assertEquals("144", calc.getResult());

    calc = calc.input('-');
    assertEquals("144-", calc.getResult());

    calc = calc.input('1');
    assertEquals("144-1", calc.getResult());

    calc = calc.input('0');
    assertEquals("144-10", calc.getResult());

    calc = calc.input('=');
    assertEquals("134", calc.getResult());

    calc = calc.input('=');
    assertEquals("124", calc.getResult());

    calc = calc.input('=');
    assertEquals("114", calc.getResult());

    calc = calc.input('-');
    assertEquals("114-", calc.getResult());

    calc = calc.input('=');
    assertEquals("0", calc.getResult());

    calc = calc.input('=');
    assertEquals("-114", calc.getResult());

    calc = calc.input('C');
    assertEquals("", calc.getResult());

    calc = calc.input('2');
    assertEquals("2", calc.getResult());

    calc = calc.input('*');
    assertEquals("2*", calc.getResult());

    calc = calc.input('=');
    assertEquals("4", calc.getResult());

    calc = calc.input('*');
    assertEquals("4*", calc.getResult());

    calc = calc.input('=');
    assertEquals("16", calc.getResult());

    calc = calc.input('=');
    assertEquals("64", calc.getResult());

    calc = calc.input('=');
    assertEquals("256", calc.getResult());

  }

}