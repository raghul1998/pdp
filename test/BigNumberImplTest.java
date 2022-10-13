import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.*;

public class BigNumberImplTest {
  private BigNumber obj;
  private BigNumber obj2;

  private BigNumber obj3;

  @Before
  public void setup() {
    obj = new BigNumberImpl();
    obj2 = new BigNumberImpl("123");
    obj3 = new BigNumberImpl("0");
  }

  @Test
  public void testConstructor() {
    try {
      new BigNumberImpl("");
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Null or Empty String", e.getMessage());
    }

    try {
      new BigNumberImpl("-1");
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Number", e.getMessage());
    }

    try {
      new BigNumberImpl(null);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Null or Empty String", e.getMessage());
    }

    try {
      new BigNumberImpl("-928374393");
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Number", e.getMessage());
    }

    try {
      new BigNumberImpl("#(239ueojv9*^&YUIhaeviik");
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Number", e.getMessage());
    }
  }

  @Test
  public void lengthTest() {
    assertEquals(1, obj.length());
    assertEquals(3, obj2.length());
    assertEquals(1, obj3.length());

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
    assertEquals("0", obj3.toString());

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
    assertEquals(1, obj.length());
    assertEquals("0", obj.toString());

    assertEquals(3, obj2.length());
    assertEquals("123", obj2.toString());

    assertEquals(1, obj3.length());
    assertEquals("0", obj3.toString());

    obj.shiftLeft(2);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftRight(2);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj3.shiftLeft(2);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    obj3.shiftRight(2);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    obj2.shiftLeft(2);
    assertEquals("12300", obj2.toString());
    assertEquals(5, obj2.length());

    obj2.shiftLeft(-2);
    assertEquals("123", obj2.toString());
    assertEquals(3, obj2.length());

    obj2.shiftRight(-2);
    assertEquals("12300", obj2.toString());
    assertEquals(5, obj2.length());

    obj2.shiftRight(2);
    assertEquals("123", obj2.toString());
    assertEquals(3, obj2.length());

    obj2.shiftRight(1);
    assertEquals("12", obj2.toString());
    assertEquals(2, obj2.length());

    obj2.shiftRight(1);
    assertEquals("1", obj2.toString());
    assertEquals(1, obj2.length());

    obj2.shiftRight(1);
    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());

    obj2.shiftRight(10);
    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());

    obj2.addDigit(1);
    assertEquals("1", obj2.toString());
    assertEquals(1, obj2.length());

    obj2.shiftLeft(2);
    assertEquals("100", obj2.toString());
    assertEquals(3, obj2.length());

    obj2.shiftRight(200);
    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());
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
  public void leftShiftTestEmpty2() {
    BigNumber obj = new BigNumberImpl("0");
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
  public void leftShiftTestEmptyNegative() {
    BigNumber obj = new BigNumberImpl();
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-0);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-1);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-2);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-756543);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());
  }

  @Test
  public void leftShiftTestEmptyNegative2() {
    BigNumber obj = new BigNumberImpl("0");
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-0);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-1);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-2);
    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(-756543);
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
  public void leftShiftTestNumberByRandomNegative() {
    Random random = new Random();
    StringBuilder str = new StringBuilder(generateRandomBigInteger());
    BigNumber obj = new BigNumberImpl(str.toString());
    assertEquals(str.toString(), obj.toString());
    assertEquals(str.length(), obj.length());

    for (int i = 0; i < 100; i++) {
      int randValue = random.nextInt(100) - 100;
      obj.shiftLeft(randValue);
      if (str.length() - (randValue * -1) > 0) {
        str.delete(str.length() - (randValue * -1), str.length());
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
  public void leftRightTestNumberByRandomNegative() {
    Random random = new Random();
    String str = generateRandomInteger();
    BigNumber obj = new BigNumberImpl(str);
    assertEquals(str, obj.toString());
    assertEquals(str.length(), obj.length());

    for (int i = 0; i < 100; i++) {
      int randValue = random.nextInt(100) - 100;
      for (int j = 0; j < (randValue * -1); j++) {
        str += "0";
      }
      obj.shiftRight(randValue);
      assertEquals(str, obj.toString());
      assertEquals(str.length(), obj.length());
    }
    assertEquals(str, obj.toString());
    assertEquals(str.length(), obj.length());
  }

  @Test
  public void addDigitTestInitial() {
    BigNumber obj1 = new BigNumberImpl();
    BigNumber obj2 = new BigNumberImpl("99999999999999999995");
    BigNumber obj3 = new BigNumberImpl("0");

    try {
      obj1.addDigit(-1);
      obj2.addDigit(-1);
      obj3.addDigit(-1);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("0", obj1.toString());
      assertEquals(1, obj1.length());

      assertEquals("99999999999999999995", obj2.toString());
      assertEquals(20, obj2.length());

      assertEquals("0", obj3.toString());
      assertEquals(1, obj3.length());
    }

    try {
      obj1.addDigit(-1243);
      obj2.addDigit(-1243);
      obj3.addDigit(-1243);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("0", obj1.toString());
      assertEquals(1, obj1.length());

      assertEquals("99999999999999999995", obj2.toString());
      assertEquals(20, obj2.length());

      assertEquals("0", obj3.toString());
      assertEquals(1, obj3.length());
    }

    try {
      obj1.addDigit(124);
      obj2.addDigit(124);
      obj3.addDigit(124);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("0", obj1.toString());
      assertEquals(1, obj1.length());

      assertEquals("99999999999999999995", obj2.toString());
      assertEquals(20, obj2.length());

      assertEquals("0", obj3.toString());
      assertEquals(1, obj3.length());
    }

    try {
      obj1.addDigit(12);
      obj2.addDigit(12);
      obj3.addDigit(12);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("0", obj1.toString());
      assertEquals(1, obj1.length());

      assertEquals("99999999999999999995", obj2.toString());
      assertEquals(20, obj2.length());

      assertEquals("0", obj3.toString());
      assertEquals(1, obj3.length());
    }

    try {
      obj1.addDigit(12237890);
      obj2.addDigit(12237890);
      obj3.addDigit(12237890);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("0", obj1.toString());
      assertEquals(1, obj1.length());

      assertEquals("99999999999999999995", obj2.toString());
      assertEquals(20, obj2.length());

      assertEquals("0", obj3.toString());
      assertEquals(1, obj3.length());
    }


    obj1.addDigit(0);
    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    obj2.addDigit(0);
    assertEquals("99999999999999999995", obj2.toString());
    assertEquals(20, obj2.length());

    obj3.addDigit(0);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());
  }

  @Test
  public void addDigitTestGenericWithShift() {
    BigNumber obj1 = new BigNumberImpl();
    BigNumber obj2 = new BigNumberImpl("99999999999999999995");
    BigNumber obj3 = new BigNumberImpl("0");

    obj1.addDigit(1);
    assertEquals("1", obj1.toString());
    assertEquals(1, obj1.length());

    obj2.addDigit(1);
    assertEquals("99999999999999999996", obj2.toString());
    assertEquals(20, obj2.length());

    obj3.addDigit(1);
    assertEquals("1", obj3.toString());
    assertEquals(1, obj3.length());

    obj1.addDigit(9);
    assertEquals("10", obj1.toString());
    assertEquals(2, obj1.length());

    obj2.addDigit(9);
    assertEquals("100000000000000000005", obj2.toString());
    assertEquals(21, obj2.length());

    obj3.addDigit(9);
    assertEquals("10", obj3.toString());
    assertEquals(2, obj3.length());


    obj1.shiftLeft(3);
    assertEquals("10000", obj1.toString());
    assertEquals(5, obj1.length());

    obj1.addDigit(9);
    assertEquals("10009", obj1.toString());
    assertEquals(5, obj1.length());

    obj1.addDigit(9);
    assertEquals("10018", obj1.toString());
    assertEquals(5, obj1.length());

    obj1.shiftRight(1);
    assertEquals("1001", obj1.toString());
    assertEquals(4, obj1.length());

    obj1.addDigit(9);
    assertEquals("1010", obj1.toString());
    assertEquals(4, obj1.length());


    obj2.shiftLeft(3);
    assertEquals("100000000000000000005000", obj2.toString());
    assertEquals(24, obj2.length());

    obj2.addDigit(9);
    assertEquals("100000000000000000005009", obj2.toString());
    assertEquals(24, obj2.length());

    obj2.addDigit(9);
    assertEquals("100000000000000000005018", obj2.toString());
    assertEquals(24, obj2.length());

    obj2.shiftRight(1);
    assertEquals("10000000000000000000501", obj2.toString());
    assertEquals(23, obj2.length());

    obj2.addDigit(3);
    assertEquals("10000000000000000000504", obj2.toString());
    assertEquals(23, obj2.length());


    obj3.shiftLeft(3);
    assertEquals("10000", obj3.toString());
    assertEquals(5, obj3.length());

    obj3.addDigit(9);
    assertEquals("10009", obj3.toString());
    assertEquals(5, obj3.length());

    obj3.addDigit(9);
    assertEquals("10018", obj3.toString());
    assertEquals(5, obj3.length());

    obj3.shiftRight(2);
    assertEquals("100", obj3.toString());
    assertEquals(3, obj3.length());

    obj3.addDigit(9);
    assertEquals("109", obj3.toString());
    assertEquals(3, obj3.length());

    obj3.addDigit(3);
    assertEquals("112", obj3.toString());
    assertEquals(3, obj3.length());
  }

  @Test
  public void addDigitFuzzy() {
    String str = generateRandomBigInteger();
    BigNumber obj = new BigNumberImpl(str);
    Random rand = new Random();

    for (int i = 0; i < 1000; i++) {
      int val = rand.nextInt(10);
      obj.addDigit(val);
      str = sumOfTwoBigNumbers(Integer.toString(val), str);
      assertEquals(str, obj.toString());
      assertEquals(str.length(), obj.length());
    }
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
  public void addEmptyObject() {
    BigNumber obj1 = new BigNumberImpl();
    BigNumber obj2 = new BigNumberImpl();
    BigNumber obj3;

    obj3 = obj1.add(obj2);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());

    obj3 = obj2.add(obj1);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());
  }

  @Test
  public void addEmptyObject2() {
    BigNumber obj1 = new BigNumberImpl();
    BigNumber obj2 = new BigNumberImpl("0");
    BigNumber obj3;

    obj3 = obj1.add(obj2);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());

    obj3 = obj2.add(obj1);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());
  }

  @Test
  public void addEmptyObject3() {
    BigNumber obj1 = new BigNumberImpl("0");
    BigNumber obj2 = new BigNumberImpl("0");
    BigNumber obj3;

    obj3 = obj1.add(obj2);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());

    obj3 = obj2.add(obj1);
    assertEquals("0", obj3.toString());
    assertEquals(1, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("0", obj2.toString());
    assertEquals(1, obj2.length());
  }

  @Test
  public void addEmptyWithNonEmpty1() {
    BigNumber obj1 = new BigNumberImpl();
    BigNumber obj2 = new BigNumberImpl("12345");
    BigNumber obj3;

    obj3 = obj1.add(obj2);
    assertEquals("12345", obj3.toString());
    assertEquals(5, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("12345", obj2.toString());
    assertEquals(5, obj2.length());

    obj3 = obj2.add(obj1);
    assertEquals("12345", obj3.toString());
    assertEquals(5, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("12345", obj2.toString());
    assertEquals(5, obj2.length());
  }

  @Test
  public void addEmptyWithNonEmpty2() {
    BigNumber obj1 = new BigNumberImpl("0");
    BigNumber obj2 = new BigNumberImpl("12345");
    BigNumber obj3;

    obj3 = obj1.add(obj2);
    assertEquals("12345", obj3.toString());
    assertEquals(5, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("12345", obj2.toString());
    assertEquals(5, obj2.length());

    obj3 = obj2.add(obj1);
    assertEquals("12345", obj3.toString());
    assertEquals(5, obj3.length());

    assertEquals("0", obj1.toString());
    assertEquals(1, obj1.length());

    assertEquals("12345", obj2.toString());
    assertEquals(5, obj2.length());
  }

  @Test
  public void addNonEmpty() {
    BigNumber obj1 = new BigNumberImpl("123");
    BigNumber obj2 = new BigNumberImpl("12345");
    BigNumber obj3;

    obj3 = obj1.add(obj2);
    assertEquals("12468", obj3.toString());
    assertEquals(5, obj3.length());

    assertEquals("123", obj1.toString());
    assertEquals(3, obj1.length());

    assertEquals("12345", obj2.toString());
    assertEquals(5, obj2.length());

    obj3 = obj2.add(obj1);
    assertEquals("12468", obj3.toString());
    assertEquals(5, obj3.length());

    assertEquals("123", obj1.toString());
    assertEquals(3, obj1.length());

    assertEquals("12345", obj2.toString());
    assertEquals(5, obj2.length());
  }

  @Test
  public void addNonEmptyFuzzy() {
    for (int i = 0; i < 1000; i++) {
      String str1 = generateRandomBigInteger();
      String str2 = generateRandomBigInteger();
      String sum;
      BigNumber obj1 = new BigNumberImpl(str1);
      BigNumber obj2 = new BigNumberImpl(str2);
      BigNumber obj3;

      if (str1.length() <= str2.length()) {
        sum = sumOfTwoBigNumbers(str1, str2);
      } else {
        sum = sumOfTwoBigNumbers(str2, str1);
      }

      obj3 = obj1.add(obj2);
      assertEquals(sum, obj3.toString());
      assertEquals(sum.length(), obj3.length());

      assertEquals(str1, obj1.toString());
      assertEquals(str1.length(), obj1.length());

      assertEquals(str2, obj2.toString());
      assertEquals(str2.length(), obj2.length());

      obj3 = obj2.add(obj1);
      assertEquals(sum, obj3.toString());
      assertEquals(sum.length(), obj3.length());

      assertEquals(str1, obj1.toString());
      assertEquals(str1.length(), obj1.length());

      assertEquals(str2, obj2.toString());
      assertEquals(str2.length(), obj2.length());
    }
  }

  @Test
  public void getPositionTestEmpty() {
    BigNumber obj = new BigNumberImpl();
    assertEquals(0, obj.getDigitAt(0));
    assertEquals(0, obj.getDigitAt(0000));

    try {
      assertEquals(0, obj.getDigitAt(1));
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      assertEquals(0, obj.getDigitAt(-11));
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      assertEquals(0, obj.getDigitAt(-1));
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      assertEquals(0, obj.getDigitAt(1028));
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      assertEquals(0, obj.getDigitAt(01011));
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

  }

  @Test
  public void getPositionTestEmpty2() {
    BigNumber obj = new BigNumberImpl("0");
    assertEquals(0, obj.getDigitAt(0));
    assertEquals(0, obj.getDigitAt(0000));

    try {
      obj.getDigitAt(1);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(-11);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(-1);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(1028);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(01011);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

  }

  @Test
  public void getPositionTestNonEmpty() {
    BigNumber obj = new BigNumberImpl("123456");
    assertEquals(6, obj.getDigitAt(0));
    assertEquals(6, obj.getDigitAt(0000));
    assertEquals(5, obj.getDigitAt(1));
    assertEquals(4, obj.getDigitAt(2));
    assertEquals(3, obj.getDigitAt(3));
    assertEquals(2, obj.getDigitAt(4));
    assertEquals(1, obj.getDigitAt(5));


    try {
      obj.getDigitAt(6);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(-11);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(-1);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(1028);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

    try {
      obj.getDigitAt(01011);
      fail("Expected exception did not occur");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position", e.getMessage());
    }

  }

  @Test
  public void getPositionTestNonEmptyBigNumber() {
    String str = generateRandomBigInteger();
    StringBuilder strbld = new StringBuilder(str);
    strbld.reverse();
    BigNumber obj = new BigNumberImpl(str);
    Random rand = new Random();

    for (int i = 0; i < 1000; i++) {
      int val = rand.nextInt(3000);

      if(val < str.length()) {
        assertEquals(Character.getNumericValue(strbld.charAt(val)), obj.getDigitAt(val));
      } else {
        try {
          obj.getDigitAt(val);
          fail("Expected exception did not occur");
        } catch (IllegalArgumentException e) {
          assertEquals("Invalid position", e.getMessage());
        }
      }
    }
  }

  @Test
  public void copyTest() {
    BigNumber copy = obj2.copy();
    copy.shiftLeft(2);
    assertEquals("12300", copy.toString());
    assertEquals("123", obj2.toString());
  }

  @Test
  public void copyTestEmpty() {
    BigNumber obj = new BigNumberImpl();
    BigNumber copy = obj.copy();

    assertEquals("0", copy.toString());
    assertEquals(1, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.addDigit(2);
    assertEquals("2", copy.toString());
    assertEquals(1, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.shiftLeft(5);
    assertEquals("200000", copy.toString());
    assertEquals(6, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.shiftRight(3);
    assertEquals("200", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.addDigit(3);
    copy.addDigit(9);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.addDigit(3);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("3", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(3);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("3000", obj.toString());
    assertEquals(4, obj.length());

    obj.shiftRight(1);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("300", obj.toString());
    assertEquals(3, obj.length());
  }

  @Test
  public void copyTestEmpty2() {
    BigNumber obj = new BigNumberImpl("0");
    BigNumber copy = obj.copy();

    assertEquals("0", copy.toString());
    assertEquals(1, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.addDigit(2);
    assertEquals("2", copy.toString());
    assertEquals(1, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.shiftLeft(5);
    assertEquals("200000", copy.toString());
    assertEquals(6, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.shiftRight(3);
    assertEquals("200", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    copy.addDigit(3);
    copy.addDigit(9);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("0", obj.toString());
    assertEquals(1, obj.length());

    obj.addDigit(3);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("3", obj.toString());
    assertEquals(1, obj.length());

    obj.shiftLeft(3);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("3000", obj.toString());
    assertEquals(4, obj.length());

    obj.shiftRight(1);
    assertEquals("212", copy.toString());
    assertEquals(3, copy.length());

    assertEquals("300", obj.toString());
    assertEquals(3, obj.length());
  }

  @Test
  public void copyTestEmpty3() {
    BigNumber obj = new BigNumberImpl("1234567890");
    BigNumber copy = obj.copy();

    assertEquals("1234567890", copy.toString());
    assertEquals(10, obj.length());

    assertEquals("1234567890", obj.toString());
    assertEquals(10, obj.length());

    copy.addDigit(2);
    assertEquals("1234567892", copy.toString());
    assertEquals(10, copy.length());

    assertEquals("1234567890", obj.toString());
    assertEquals(10, obj.length());

    copy.shiftLeft(5);
    assertEquals("123456789200000", copy.toString());
    assertEquals(15, copy.length());

    assertEquals("1234567890", obj.toString());
    assertEquals(10, obj.length());

    copy.shiftRight(3);
    assertEquals("123456789200", copy.toString());
    assertEquals(12, copy.length());

    assertEquals("1234567890", obj.toString());
    assertEquals(10, obj.length());

    copy.addDigit(3);
    copy.addDigit(9);
    assertEquals("123456789212", copy.toString());
    assertEquals(12, copy.length());

    assertEquals("1234567890", obj.toString());
    assertEquals(10, obj.length());

    obj.addDigit(3);
    assertEquals("123456789212", copy.toString());
    assertEquals(12, copy.length());

    assertEquals("1234567893", obj.toString());
    assertEquals(10, obj.length());

    obj.shiftLeft(3);
    assertEquals("123456789212", copy.toString());
    assertEquals(12, copy.length());

    assertEquals("1234567893000", obj.toString());
    assertEquals(13, obj.length());

    obj.shiftRight(1);
    assertEquals("123456789212", copy.toString());
    assertEquals(12, copy.length());

    assertEquals("123456789300", obj.toString());
    assertEquals(12, obj.length());
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
  public void compareToTestEmptyItself() {
    BigNumber one = new BigNumberImpl();
    assertEquals(true, one.same(one));
  }

  @Test
  public void compareToTestEmptyItself2() {
    BigNumber one = new BigNumberImpl("0");
    assertEquals(true, one.same(one));
  }

  @Test
  public void compareToTestNonEmptyItself() {
    BigNumber one = new BigNumberImpl("23434545653421245678765");
    assertEquals(true, one.same(one));
  }

  @Test
  public void compareToTestEmptyToEmpty() {
    BigNumber one = new BigNumberImpl();
    BigNumber two = new BigNumberImpl();
    assertEquals(true, one.same(two));
    assertEquals(true, two.same(one));
  }

  @Test
  public void compareToTestEmptyToNonEmpty1() {
    BigNumber one = new BigNumberImpl();
    BigNumber two = new BigNumberImpl("0");
    assertEquals(true, one.same(two));
    assertEquals(true, two.same(one));
  }

  @Test
  public void compareToTestEmptyToNonEmpty2() {
    BigNumber one = new BigNumberImpl();
    BigNumber two = new BigNumberImpl("335443235");
    assertEquals(false, one.same(two));
    assertEquals(false, two.same(one));
  }

  @Test
  public void compareToTestNonEmptySame() {
    BigNumber one = new BigNumberImpl("2345643423");
    BigNumber two = new BigNumberImpl("2345643423");
    assertEquals(true, one.same(two));
    assertEquals(true, two.same(one));
  }

  @Test
  public void compareToTestNonEmptyDifferent1() {
    BigNumber one = new BigNumberImpl("0");
    BigNumber two = new BigNumberImpl("2345643423");
    assertEquals(false, one.same(two));
    assertEquals(false, two.same(one));
  }

  @Test
  public void compareToTestNonEmptyDifferent2() {
    BigNumber one = new BigNumberImpl("1243");
    BigNumber two = new BigNumberImpl("2345643423");
    assertEquals(false, one.same(two));
    assertEquals(false, two.same(one));
  }

  @Test
  public void compareToTestNonEmptyDifferentBigNumberSame() {
    String str = generateRandomBigInteger();
    BigNumber one = new BigNumberImpl(str);
    BigNumber two = new BigNumberImpl(str);
    assertEquals(true, one.same(two));
    assertEquals(true, two.same(one));
  }

  @Test
  public void compareToTestNonEmptyDifferentBigNumberDifferent() {
    BigNumber one = new BigNumberImpl(generateRandomBigInteger());
    BigNumber two = new BigNumberImpl(generateRandomBigInteger());
    assertEquals(false, one.same(two));
    assertEquals(false, two.same(one));
  }

  @Test
  public void Test() {
    BigNumber one = new BigNumberImpl("5");
    assertEquals(1, one.length());
    one.shiftRight(20);
    assertEquals("0", one.toString());
    assertEquals(1, one.length());
  }

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

  private String sumOfTwoBigNumbers(String opr1, String opr2) {
    StringBuilder result = new StringBuilder();

    int opr1Length = opr1.length();
    int opr2Length = opr2.length();
    int difference = opr2Length - opr1Length;
    int carry = 0;

    for (int i = opr1Length - 1; i >= 0; i--) {
      int sum = ((opr1.charAt(i) - '0') + (opr2.charAt(i + difference) - '0') + carry);
      result.append((char) (sum % 10 + '0'));
      carry = sum / 10;
    }

    for (int i = opr2Length - opr1Length - 1; i >= 0; i--) {
      int sum = ((opr2.charAt(i) - '0') + carry);
      result.append((char) (sum % 10 + '0'));
      carry = sum / 10;
    }

    if (carry > 0) {
      result.append((char) (carry + '0'));
    }

    return result.reverse().toString();
  }
}