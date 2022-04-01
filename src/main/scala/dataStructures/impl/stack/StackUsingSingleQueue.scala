package dataStructures.impl.stack

import dataStructures.impl.queues.QueueArray

class StackUsingSingleQueue {
  // Data-structure to store values
  var q : QueueArray = new QueueArray()

  // Keeps the track of size of Stack
  var length : Int = 0

  // Check if the Stack is empty
  def isEmpty : Boolean = this.q.isEmpty

  // Check if the Stack is empty
  def isNotEmpty : Boolean = !this.q.isEmpty

  // Push elements to Stack
  def push(elem: Int) : Unit = {
    // Step-1: Insert into the Queue
    this.q.enqueue(elem)
    this.length += 1

    // Step-2: De-queue the front element of the Queue and enqueue
    // length-1 number of times
    var i = this.length - 1
    while(i > 0) {
      this.q.enqueue(this.q.dequeue)
      i -= 1
    }
  }

  // Peek into the Stack's top element
  def peek : Int = if(this.q.isNotEmpty)
      this.q.getFront
    else
      throw new Exception("Error: Stack is empty!")

  // Pop the top element of the Stack
  def pop : Int = {
    if(this.q.isNotEmpty) {
      // Get the element at the top
      val res = this.q.dequeue
      this.length -= 1

      // Return the result
      res
    }
    else
      throw new Exception("Error: Stack is empty!")
  }
}
