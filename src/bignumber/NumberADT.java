package bignumber;

import java.util.function.BiFunction;

/**
 * This interface is an abstract data type and represents the list and the nodes.
 */
public interface NumberADT {
  /**
   * This method helps to get the number of nodes in the list.
   * This basically tells the length of the list.
   *
   * @return the number of nodes as an integer
   */
  int count();

  /**
   * Adds a node to at the end of the list.
   * A number added back will have the current last node as its previous and the next as null.
   *
   * @param num  the node to be added
   * @param prev represents the previous node
   * @return the new node
   */
  NumberADT addBack(Number num, NumberADT prev);

  /**
   * Adds a node at the front of the list.
   * A number added to the front will have the current first node as its next node.
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
   * This method helps to get the integer value of the node at a given position.
   *
   * @param position position at which the value needs to be known
   * @param count    length of the list
   * @return the value of the node as an integer
   * @throws IllegalArgumentException if it is an empty node
   */
  int getAt(int position, int count) throws IllegalArgumentException;

  /**
   * This method helps to get the previous node of the given node.
   *
   * @return the previous node as NumberADT object
   */
  NumberADT prev();

  /**
   * This method helps to get the next node of the given node.
   *
   * @return the next node as NumberADT object
   */
  NumberADT next();

  /**
   * This method set the given node to the next of this node.
   * The set next node throws an exception if the next node is empty.
   *
   * @param node node to be set as next of this node
   * @throws IllegalArgumentException if it is an empty node
   */
  void setNext(NumberADT node) throws IllegalArgumentException;

  /**
   * A method helps to get the integer value of the node.
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

  Integer fold(Integer initial, BiFunction<Integer, Integer, Integer> combiner);
}
