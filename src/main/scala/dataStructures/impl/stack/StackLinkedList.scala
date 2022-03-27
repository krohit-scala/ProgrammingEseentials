package dataStructures.impl.stack

import dataStructures.impl.linkedList.LinkedList

class StackLinkedList {
  var ll : LinkedList = new LinkedList

  // Push element into Stack
  def push(item: Int) : Unit = ll.push(item)

  // Push array elements into Stack
  def pushArray(arr: Array[Int]) : Unit = {
    for(elem <- arr) {
      this.push(elem)
    }
    this.ll.printList
  }

  // Return element of the Stack
  def peek : Int = {
    val top = ll.head.data
    top
  }

  // Pop an element from the Stack
  def pop : Int = if(!this.isEmpty) {
      val top = ll.head.data
      ll.deleteFirstElement
      top
    }
    else
      throw new NullPointerException("ERROR: Cannot pop an empty Stack!")

  // Length of Stack
  def length : Int = ll.length

  // Check if Stack is empty
  def isEmpty : Boolean = if(ll.head == null)
      true
    else
      false

  // Check if Stack is not empty
  def isNotEmpty : Boolean = !(this.isEmpty)

  // Print contents of a Stack
  def printStack : Unit = this.ll.printList

  // Clear the Stack
  // Remove all elements
  def clearStack : Unit = this.ll.clear()
}
