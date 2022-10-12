import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;
import bignumber.NumberADT;

import static org.junit.Assert.*;

public class BigNumberImplTest {
  private BigNumber obj;
  private BigNumber obj2;

  private String generateRandomInteger() {
    Random random = new Random();
    StringBuilder str = new StringBuilder();
    boolean firstVal = false;

    for (int i = 0; i < random.nextInt(2000); i++) {
      int randValue = random.nextInt(10);
      if (!firstVal) {
        if (randValue == 0) {
          continue;
        }
        firstVal = true;
      }
      str.append(randValue);
    }

    if (str.length() == 0) {
      str.append(random.nextInt(10));
    }

    return str.toString();
  }

  private String generateRandomBigInteger() {
    Random random = new Random();
    StringBuilder str = new StringBuilder();
    boolean firstVal = false;

    for (int i = 0; i < 2000; i++) {
      int randValue = random.nextInt(10);
      if (!firstVal) {
        if (randValue == 0) {
          continue;
        }
        firstVal = true;
      }
      str.append(randValue);
    }

    return str.toString();
  }

  @Before
  public void setup() {
    obj = new BigNumberImpl();
    obj2 = new BigNumberImpl("123");
  }

  @Test
  public void lengthTest() {
    assertEquals(1, obj.length());
    assertEquals(3, obj2.length());

    for (int i = 0; i < 100; i++) {
      String str1 = generateRandomInteger();
      String str2 = generateRandomBigInteger();

      BigNumber temp1 = new BigNumberImpl(str1);
      assertEquals(str1.length(), temp1.length());

      BigNumber temp2 = new BigNumberImpl(str2);
      assertEquals(str2.length(), temp2.length());
    }
  }

  @Test
  public void toStringTest() {
    assertEquals("0", obj.toString());
    assertEquals("123", obj2.toString());

    for (int i = 0; i < 100; i++) {
      String str1 = generateRandomInteger();
      String str2 = generateRandomBigInteger();

      BigNumber temp1 = new BigNumberImpl(str1);
      assertEquals(str1, temp1.toString());

      BigNumber temp2 = new BigNumberImpl(str2);
      assertEquals(str2, temp2.toString());
    }
  }

  @Test
  public void shiftTestGeneral() {
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
  public void leftShiftTestEmpty() {
    BigNumber obj = new BigNumberImpl();
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(0);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(1);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(2);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(756543);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());
  }

  @Test
  public void leftShiftTestNumberByOne() {
    String str = generateRandomBigInteger();
    BigNumber obj = new BigNumberImpl(str);
    assertEquals(str, obj.toString());
    assertEquals(str.length(), obj.length());

    for (int i = 0; i < 100; i++) {
      str += "0";
      obj.shiftLeft(1);
      assertEquals(str, obj.toString());
      assertEquals(str.length(), obj.length());
    }
    assertEquals(str, obj.toString());
    assertEquals(str.length(), obj.length());
  }

  @Test
  public void leftShiftTestNumberByRandom() {
    Random random = new Random();
    String str = generateRandomInteger();
    BigNumber obj = new BigNumberImpl(str);
    assertEquals(str, obj.toString());
    assertEquals(str.length(), obj.length());

    for (int i = 0; i < 100; i++) {
      int randValue = random.nextInt(100);
      for (int j = 0; j < randValue; j++) {
        str += "0";
      }
      obj.shiftLeft(randValue);
      assertEquals(str, obj.toString());
      assertEquals(str.length(), obj.length());
    }
    assertEquals(str, obj.toString());
    assertEquals(str.length(), obj.length());
  }

  @Test
  public void leftShiftNumberCreationByOne() {
    Random random = new Random();
    BigNumber obj = new BigNumberImpl();
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    StringBuilder str = new StringBuilder();
    boolean isFirstDigit = true;
    for (int i = 0; i < 1000; i++) {
      int val = random.nextInt(10);
      if (isFirstDigit && val == 0) {
        continue;
      }

      obj.shiftLeft(1);
      if (isFirstDigit) {
        assertEquals("0", obj.toString());
        assertEquals(1, obj.length());
      } else {
        StringBuilder temp = new StringBuilder(str.toString());
        temp.append("0");
        assertEquals(temp.toString(), obj.toString());
        assertEquals(temp.length(), obj.length());
      }
      obj.addDigit(val);
      str.append(val);
      assertEquals(str.toString(), obj.toString());
      assertEquals(str.length(), obj.length());
      isFirstDigit = false;
    }
  }

  @Test
  public void leftShiftNumberCreationByRandom() {
    Random random = new Random();
    BigNumber obj = new BigNumberImpl();
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    StringBuilder str = new StringBuilder();
    boolean isFirstDigit = true;
    for (int i = 0; i < 100; i++) {
      int val = random.nextInt(10);
      if (isFirstDigit && val == 0) {
        continue;
      }

      obj.shiftLeft(val);
      if (isFirstDigit) {
        assertEquals("0", obj.toString());
        assertEquals(1, obj.length());
        str.append("0");
      } else {
        str.append("0".repeat(val));
        assertEquals(str.toString(), obj.toString());
        assertEquals(str.length(), obj.length());
      }

      obj.addDigit(val);

      if (val != 0) {
        str.replace(str.length() - 1, str.length(), Integer.toString(val));
      }

      assertEquals(str.toString(), obj.toString());
      assertEquals(str.length(), obj.length());
      isFirstDigit = false;
    }
  }

  @Test
  public void leftRightTestEmpty() {
    BigNumber obj = new BigNumberImpl();
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftRight(0);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftRight(1);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftRight(2);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftRight(756543);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());
  }

  @Test
  public void leftRightTestNumberByOne() {
    StringBuilder str = new StringBuilder(generateRandomBigInteger());
    BigNumber obj = new BigNumberImpl(str.toString());
    assertEquals(str.toString(), obj.toString());
    assertEquals(str.length(), obj.length());

    for (int i = 0; i < 100; i++) {
      str.deleteCharAt(str.length() - 1);
      obj.shiftRight(1);
      assertEquals(str.toString(), obj.toString());
      assertEquals(str.length(), obj.length());
    }
    assertEquals(str.toString(), obj.toString());
    assertEquals(str.length(), obj.length());
  }

  @Test
  public void leftRightTestNumberByRandom() {
    Random random = new Random();
    StringBuilder str = new StringBuilder(generateRandomBigInteger());
    BigNumber obj = new BigNumberImpl(str.toString());
    assertEquals(str.toString(), obj.toString());
    assertEquals(str.length(), obj.length());

    for (int i = 0; i < 100; i++) {
      int randValue = random.nextInt(100);
      obj.shiftRight(randValue);
      if (str.length() - randValue > 0) {
        str.delete(str.length() - randValue, str.length());
      } else {
        str.setLength(0);
        str.append("0");
      }
      assertEquals(str.toString(), obj.toString());
      assertEquals(str.length(), obj.length());
    }
    assertEquals(str.toString(), obj.toString());
    assertEquals(str.length(), obj.length());
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

  @Test
  public void Test() {
    BigNumber one = new BigNumberImpl("5");
    assertEquals(1, one.length());
    one.shiftRight(20);
    assertEquals("0", one.toString());
    assertEquals(1, one.length());
  }
}