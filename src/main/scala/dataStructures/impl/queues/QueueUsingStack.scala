package dataStructures.impl.queues

import dataStructures.impl.stack.StackArray

class QueueUsingStack {
  // Data-structures to hold values
  var s1 : StackArray = new StackArray
  var s2 : StackArray = new StackArray

  // Size of the Queue
  var length : Int = 0

  // Check whether the Queue is empty
  def isEmpty : Boolean = {
    // Push everything from S1 to S2
    while(!this.s1.isEmpty)
      this.s2.push(this.s1.pop)

    // Return the result
    if(this.s2.isEmpty)
      true
    else
      false
  }

  // Print the Queue
  def printQueue : String = {
    if(isNotEmpty)
      this.s2.printStack
    else
      throw new NullPointerException("Error: Queue is empty!")
  }

  // Check whether the Queue is not empty
  def isNotEmpty : Boolean = {! this.isEmpty}

  // Enqueue element to the Queue
  def enqueue(elem: Int) : Unit = {
    this.s1.push(elem)
    this.length += 1
  }

  // De-queue an element off the Queue
  def dequeue : Int = {
    if(!this.isEmpty) {
      val res = s2.pop
      this.length -= 1
      res
    }
    else
      throw new NullPointerException("Error: Queue is empty!")
  }

  // De-queue an element off the Queue
  def getFront : Int = {
    if(!this.isEmpty) {
      val res = s2.peek
      res
    }
    else
      throw new Exception("Error: Queue is empty!")
  }
}
