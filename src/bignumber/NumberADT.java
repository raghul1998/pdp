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
   * @param num  the node to be added
   * @param prev represents the previous node
   * @return the new node
   */
  NumberADT addBack(Number num, NumberADT prev);

  /**
   * Adds a node at the front of the list.
   *
   * @param num  the node to be added
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

  /**
   * This method returns the value of the node at a given position.
   *
   * @param position position at which the value needs to be known
   * @param count    length of the list
   * @return the value of the node as an integer
   * @throws IllegalArgumentException if it is an empty node
   */
  int getAt(int position, int count) throws IllegalArgumentException;

  /**
   * This method returns the previous node of the given node.
   *
   * @return the previous node as NumberADT object
   */
  NumberADT prev();

  /**
   * This method returns the next node of the given node.
   *
   * @return the next node as NumberADT object
   */
  NumberADT next();

  /**
   * This method set the given node to the next of this node.
   *
   * @param node node to be set as next of this node
   * @throws IllegalArgumentException if it is an empty node
   */
  void setNext(NumberADT node) throws IllegalArgumentException;

  /**
   * A method that returns the value of the node.
   *
   * @return the value of the node as an integer
   * @throws IllegalArgumentException if it is an empty node
   */
  int getValue() throws IllegalArgumentException;

  /**
   * This method replaces the given nodes value with a given value.
   *
   * @param value value to be replaced
   * @throws IllegalArgumentException if it is an empty node
   */
  void replace(int value) throws IllegalArgumentException;
}
