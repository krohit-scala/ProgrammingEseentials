package dataStructures.impl.stack

class StackArray {
  var arr : Array[Int] = Array.fill(10){-1}
  var len : Int = 0
  var capacity : Int = 1

  // Pointer to the top element of the Stack
  var top : Int = -1

  // toString implementation of the StackArray class
  def printStack: String = {
    s"Stack: ${arr.mkString(" >> ")}\nTop: ${this.top}, Capacity: ${this.capacity}, Length: ${this.len}\n"
  }

  // Check if Stack is empty
  def isEmpty : Boolean = {
    this.len == 0
  }

  // Check if Stack is full
  def isFull : Boolean = {
    this.len == this.capacity
  }

  // Method to resize array for Stack
  def resize : Unit = {
    // Increase the capacity
    this.capacity *= 2
    // Create temp array with twice the size and copy old values
    var tempArr = Array.fill(this.capacity){-1}
    for(i <- 0.until(this.len))
      tempArr(i) = this.arr(i)

    // Reassign top : Is needed?
    this.top = this.len - 1
    this.arr = tempArr
  }

  // Push item to the Stack
  def push(item : Int) : Unit = {
    // Check if current capacity is already reached
    // If true, resize the array
    if(this.isFull)
      this.resize

    // Push the new item
    this.top = this.top + 1
    this.len += 1
    this.arr(this.top) = item
    println(this.printStack)
  }

  // Push array to the Stack
  def pushArray(arr : Array[Int]) : Unit = {
    for(item <- arr)
    this.push(item)
  }

  // Method to see the element on the top of the Stack
  def peek : Option[Int] = if(!this.isEmpty) Some(this.arr(this.top)) else None

  /*
  // Method to remove element from Stack
  def pop : Option[Int] = {
    if(!this.isEmpty){
      val temp = this.arr(this.top)
      this.top -= 1
      this.len -= 1

      // Return the element
      Some(temp)
    }
    else
      None
  }
  */

  // Method to remove element from Stack
  def pop : Int = {
    if(!this.isEmpty){
      val temp = this.arr(this.top)
      this.top -= 1
      this.len -= 1

      // Return the element
      temp
    }
    else
      throw new NullPointerException("ERROR: Cannot pop an empty Stack!")
  }
}
