package dataStructures.impl.queues

import scala.collection.mutable.ArrayBuffer

// This class does the implementation of Queue using Dynamic Circular Array
class QueuesCircularArray {

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

  // Function to resize the Queue
  def resize: Unit = {
    this.capacity *= 2
    val newArr = Array.fill(this.capacity){-1}
    for(i <- 0 until length)
      newArr(i) = arr(i)
    this.arr = newArr
  }

  // Enqueue into the Queue
  def enqueue(item : Int) : Unit = {
    if(this.length < this.capacity){
      this.front += 1
      this.arr(this.front) = item

      if(this.rear == -1)
        this.rear += 1
    }
    else{
      this.resize
      this.front += 1
      this.arr(this.front) = item
    }
    this.length += 1
  }

  // Dequeue into the Queue
  def dequeue : Unit = {
    if(!this.isEmpty){
      this.rear += 1
      this.length += 1
    }
    else
      println(s"Error: Cannot dequeue an empty queue...")
  }

  // Enqueue array into the Queue
  def enqueueArray(arr : Array[Int]) : Unit = {
    for(elem <- arr)
      this.enqueue(elem)
  }

  // Print the Queue
  def printQueue : Unit = {
    if (this.front != -1 && this.rear != -1) {
      val i = this.rear
      val j = this.front
      val temp = ArrayBuffer[Int]()
      for (idx <- i to j) {
        temp.append(arr(idx))
        println(s"Element in Queue: ${arr(idx)}")
      }
      println(s"rear --> ${temp.mkString(" >> ")} <-- top\nLength: ${this.length}")
    }
    else
      println("Error: Queue is empty!")
  }
}