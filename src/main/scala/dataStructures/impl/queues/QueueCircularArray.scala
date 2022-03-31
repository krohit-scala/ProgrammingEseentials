package dataStructures.impl.queues

import scala.collection.mutable.ArrayBuffer

// This class does the implementation of Queue using Dynamic Circular Array
class QueueCircularArray {

  var arr : Array[Int] = Array.fill(1){-1}
  var capacity : Int = 1
  var length : Int = 0

  // Pointers for front and read
  var front : Int = -1
  var rear : Int = -1

  // Is Queue empty
  def isEmpty : Boolean = {
    this.front == -1 && this.rear == -1
  }

  // Is Queue non-empty
  def isNotEmpty : Boolean = { !this.isEmpty }

  // Is Queue full
  def isFull : Boolean = {
    (this.rear + 1) % this.capacity == this.front
  }

  // Is Queue not full
  def isNotFull : Boolean = { !this.isFull }

  // Get the front element
  def getFront : Int = {
    if(this.isNotEmpty)
      this.arr(this.front)
    else
      throw new NullPointerException("Error: Queue is empty!")
  }

  // Get the rear element
  def getRear : Int = {
    if(this.isNotEmpty)
      this.arr(this.rear)
    else
      throw new NullPointerException("Error: Queue is empty!")
  }

  // Function to resize the Queue
  def resize: Unit = {
    val newCapacity = this.capacity * 2
    val newArr = Array.fill(newCapacity){-1}

    var index = 0
    while(this.isNotEmpty){
      val elem = this.dequeue   // This affects the Queue length as well. So handle length separately
      newArr(index) = elem
      index += 1
    }

    // Reassign references
    this.front = 0
    this.rear = index - 1
    this.capacity *= 2
    this.length = index
    this.arr = newArr
  }

  // Enqueue into the Queue
  def enqueue(item : Int) : Unit = {
    if(this.isFull || (this.front == 0 && this.rear == 0))
      this.resize

    // Check if Queue contains zero elements, reset front to 0
    if(this.front == -1)
      this.front = 0

    // Update the rear
    this.rear = (this.rear + 1) % this.capacity
    this.arr(this.rear) = item

    // Update the length of the Queue
    this.length += 1
  }

  // Dequeue into the Queue
  def dequeue : Int = {
    // Check if Queue is empty
    if(this.isEmpty)
      throw new Exception(s"Error: Cannot dequeue an empty queue...")
    else{
      val elem = this.arr(this.front)

      // Check if Queue contains just one element
      // If front == rear
      if(this.front == this.rear) {
        this.front = -1
        this.rear = -1
      }
      else
        this.front = (this.front + 1) % this.capacity

      // Increase the length
      this.length -= 1
      elem
    }
  }

  // Enqueue array into the Queue
  def enqueueArray(arr : Array[Int]) : Unit = {
    for(elem <- arr)
      this.enqueue(elem)
  }

  // Print the Queue
  def printQueue : String = {
    if (this.isNotEmpty) {
      val temp = ArrayBuffer[Int]()
      var index = this.front

      while(index != this.rear) {
        temp.append(arr(index))
        // println(s"Element in Queue: ${arr(index)}")
        index = (index + 1) % this.capacity
      }
      temp.append(this.arr(index))
      s"front --> ${temp.mkString(" >> ")} <-- rear\nLength: ${this.length}\n"
    }
    else
      "\nError: Queue is empty!\n"
  }

  // Get size of the Queue
  def getSize : Int = this.length
}