package dataStructures.impl.queues

import dataStructures.impl.linkedList.{LinkedList, Node}

class QueueLinkedList {
  var ll : LinkedList = new LinkedList()
  var length : Int = 0

  // Enqueue in the Queue
  def enqueue(elem: Int) : Unit = {
    // Insert at the end of the LinkedList
    this.ll.append(elem)
    this.length += 1
  }

  // Enqueue all elements of the array
  def enqueueArray(arr: Array[Int]) : Unit = {
    // Enqueue elements one by one
    for(elem <- arr)
      this.enqueue(elem)
  }

  // De-queue an element from Queue
  def dequeue : Int = {
    if(!isEmpty) {
      // Get data from the 1st element and remove it from the LinkedList
      val elem = ll.head.data
      ll.deleteFirstElement
      this.length -= 1
      elem
    }
    else
      throw new Exception("Error: Cannot de-queue an empty Queue!")
  }

  // Print the contents of the Queue
  def printQueue : Unit = { println(); ll.printList }

  // Is Queue empty
  def isEmpty : Boolean = { this.ll.head == null }

  // Is Queue not empty
  def isNotEmpty : Boolean = { !(this.ll.head == null) }

  // Get front element of the Queue
  def getFront : Int = { this.ll.head.data}

  // Get front element of the Queue
  def getRear : Int = { this.ll.getLast.data}

  // Get size of the LinkedList
  def getSize : Int = (this.length)
}