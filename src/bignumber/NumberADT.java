package bignumber;

/**
 * This interface is an abstract data type and represents the list and the nodes.
 */
public interface NumberADT {
  /**
   * This method returns the number of nodes in the list.
   *
   * @return the number of nodes as an integer
   */
  int count();

  /**
   * Adds a node to at the end of the list.
   *
   * @param num the node to be added
   * @param prev represents the previous node
   * @return the new node
   */
  NumberADT addBack(Number num, NumberADT prev);

  /**
   * Adds a node at the front of the list.
   *
   * @param num the node to be added
   * @param next represents the next node
   * @return the new node
   */
  NumberADT addFront(Number num, NumberADT next);

  /**
   * Overrides the toString method and returns the string representation of the node.
   *
   * @return the value of the node as a string.
   */
  String toString();

  /**
   * A string helper method that takes acts as an accumulator.
   *
   * @param acc accumulator as a string builder
   * @return the string value of the node
   */
  String toStringHelper(StringBuilder acc);

  NumberADT removeAt(int length, boolean isSingleDigit, NumberADT tail);

  int getAt(int position, int count);

  NumberADT replaceAt(int value, int position);

  NumberADT next();

  void setNext(NumberADT node);

  int getValue();

  void replace(int value);

  NumberADT prev();
}
