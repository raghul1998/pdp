package calculator;

public interface Calculator {
  Calculator input(char op) throws IllegalArgumentException;

  String getResult();
}
