package dataStructures.impl.stack

import dataStructures.impl.queues.QueueArray

class StackUsingQueues {
  var q1 : QueueArray = new QueueArray()
  var q2 : QueueArray = new QueueArray()

  var length : Int = 0

  // Check if the Stack is empty
  def isEmpty : Boolean = this.q1.isEmpty

  // Check if the Stack is empty
  def isNoEmpty : Boolean = !this.q1.isEmpty

  // Add element to the Stack
  def push(elem : Int) : Unit = {
    // Insert into Q1
    this.q2.enqueue(elem)
    while(this.q1.isNotEmpty)
      this.q2.enqueue(this.q1.dequeue)

    val temp = this.q1
    this.q1 = this.q2
    this.q2 = temp
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

// Main program to call test the StackUsingQueues
object StackUsingQueuesImpl {
  def main(args: Array[String]): Unit = {
    // Instantiate the Stack class
    val stack = new StackUsingQueues()

    // Push to Stack
    for(elem <- 2.to(12).by(3)) {
      println(s"Element getting pushed: ${elem}")
      stack.push(elem)
    }

    // State of Stack after push
    println(s"\nLength after all push's: ${stack.length}\n")

    // Pop the Stack till empty
    while(stack.isNoEmpty){
      println(s"Element to be removed: ${stack.peek}, Length: ${stack.length}")
      stack.pop
    }

    // State of Stack after pop
    println(s"\nLength after all pops: ${stack.length}\n")
  }
}