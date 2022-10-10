import org.junit.Before;
import org.junit.Test;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.*;

public class BigNumberImplTest {
  private BigNumber obj;
  private BigNumber obj2;

  @Before
  public void setup() {
    obj = new BigNumberImpl();
    obj2 = new BigNumberImpl("123");
  }

  @Test
  public void lengthTest() {
    assertEquals(1, obj.length());
    assertEquals(3, obj2.length());
  }

  @Test
  public void toStringTest() {
    assertEquals("0", obj.toString());
    assertEquals("123", obj2.toString());
  }

  @Test
  public void shiftTest() {
    obj.shiftLeft(2);
    assertEquals("0", obj.toString());
    obj2.shiftLeft(2);
    assertEquals("12300", obj2.toString());
    obj2.shiftLeft(-2);
    assertEquals("123", obj2.toString());
    obj2.shiftRight(-2);
    assertEquals("12300", obj2.toString());
    obj2.shiftRight(2);
    assertEquals("123", obj2.toString());
  }

  @Test
  public void getPositionTest() {
    assertEquals(0, obj.getDigitAt(0));
    assertEquals(3, obj2.getDigitAt(0));
    assertEquals(2, obj2.getDigitAt(1));
    assertEquals(1, obj2.getDigitAt(2));
  }

  @Test
  public void copyTest() {
    BigNumber copy = obj2.copy();
    copy.shiftLeft(2);
    assertEquals("12300", copy.toString());
    assertEquals("123", obj2.toString());
  }

  @Test
  public void addDigitTest() {
    BigNumber obj3 = new BigNumberImpl("99999999999999999999");
    obj3.addDigit(9);
    assertEquals("100000000000000000008", obj3.toString());
  }

  @Test
  public void addTest() {
    BigNumber one = new BigNumberImpl("123456");
    BigNumber two = new BigNumberImpl("999999999");
    BigNumber result = one.add(two);
    assertEquals("123456", one.toString());
    assertEquals("999999999", two.toString());
    assertEquals("1000123455", result.toString());
  }

  @Test
  public void compareToTest() {
    BigNumber one = new BigNumberImpl("999999999");
    BigNumber two = new BigNumberImpl("999999999");
    BigNumber three = new BigNumberImpl("12345");
    assertEquals(true, one.same(two));
    assertEquals(true, two.same(one));
    assertEquals(false, one.same(three));
    assertEquals(false, three.same(one));
  }

}