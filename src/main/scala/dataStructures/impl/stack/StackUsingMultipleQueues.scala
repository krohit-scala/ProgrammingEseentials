package dataStructures.impl.stack

import dataStructures.impl.queues.QueueArray

// Implementing Stack using 2 Queues
class StackUsingMultipleQueues {
  // Data-structures to store values
  var q1 : QueueArray = new QueueArray()
  var q2 : QueueArray = new QueueArray()

  // Keeps the track of size of Stack
  var length : Int = 0

  // Check if the Stack is empty
  def isEmpty : Boolean = this.q1.isEmpty

  // Check if the Stack is empty
  def isNotEmpty : Boolean = !this.q1.isEmpty

  // Add element to the Stack
  def push(elem : Int) : Unit = {
    // Step-1: Insert into Q1
    this.q2.enqueue(elem)

    // Step-2: Append all elements from Q1 to Q2
    while(this.q1.isNotEmpty)
      this.q2.enqueue(this.q1.dequeue)

    // Swap Q1 and Q2
    val temp = this.q1
    this.q1 = this.q2
    this.q2 = temp

    // Increment the size of the Stack
    this.length += 1
  }

  // Peek the top element the Stack
  def peek : Int = if(q1.isNotEmpty)
    this.q1.getFront
  else
    throw new NullPointerException("Error: Stack is empty!")

  // Get the element from the Stack
  def pop : Int = {
    var res : Int = -1
    if(this.q1.isNotEmpty) {
      res = this.q1.dequeue
      this.length -= 1
      res
    }
    else
      throw new NullPointerException("Error: Stack is empty!")
  }
}