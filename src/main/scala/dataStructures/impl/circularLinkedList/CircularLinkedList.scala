package dataStructures.impl.circularLinkedList

import scala.collection.mutable.ArrayBuffer

class CircularLinkedList {

  var head : Node = null
  var length : Int = 0

  // Increase the length
  def lengthIncrement: Unit = this.length += 1
  // Decrease the length
  def lengthDecrement: Unit = {
    if(length > 0)
      this.length -= 1
    else
      this.length = 0
  }

  // Initialize the head
  def initialize(_data: Int) : Unit = {
    val newNode : Node = new Node(_data)
    this.head = newNode
    newNode.next = head
    lengthIncrement
  }

  // Print the CircularLinkedList
  def printList : Unit = {
    // If CircularLinkedList is empty
    if(this.head == null) {
      println(s"head --> end")
      return
    }

    var pointer : Node = this.head
    // Hold elements of LinkedList for printing
    var listData = ArrayBuffer[Int]()
    var counter = 0
    // Iterate over Nodes of the LinkedList
    while(pointer != head || counter == 0){
      listData.append(pointer.data)
      pointer = pointer.next
      counter += 1
    }
    val msg = "head --> " + listData.mkString(" --> ") + " --> end"
    println(msg)
  }

  // Flush the CircularLinkedList
  def clear : Unit = {
    this.head = null
    this.length = 0
  }

  // Add element at the front of the Circular LinkedList
  def push(_data: Int) : Unit = {
    if(this.head == null)
      initialize(_data)
    else{
      val newNode : Node = new Node(_data)
      val prevHead : Node = this.head
      newNode.next = head
      this.head = newNode
      lengthIncrement

      // Make last element point to fist
      var pointer : Node = this.head.next
      while(pointer.next != prevHead)
        pointer = pointer.next
      pointer.next = this.head
    }
  }

  // Add element at the end of the Circular LinkedList
  def append(_data: Int) : Unit = {
    if(head == null)
      initialize(_data)
    else{
      // Get to the end of the LinkedList
      var pointer : Node = head.next
      while(pointer.next != head){
        pointer = pointer.next
      }
      val newNode : Node = new Node(_data, this.head)
      pointer.next = newNode
      lengthIncrement
    }
  }

  // Append the array elements in the CircularLinkedList
  def appendArray(arr: Array[Int]) : Unit = {
    for(elem <- arr)
      this.append(elem)
  }

  // Delete first element from CircularLinkedList
  def deleteFirstElement : Unit = {
    // Base check
    if(head == null) {
      this.length = 0
      return
    }

    // If CircularLinkedList has just one element
    if(this.head.next != null && this.head.next == this.head){
      this.head = null
      this.length = 0
      return
    }

    // Rest of the scenario
    val prevHead : Node = this.head
    this.head = head.next
    // Get to the last element
    var pointer : Node = this.head
    while(pointer.next != prevHead)
      pointer = pointer.next
    pointer.next = head

    lengthDecrement
  }

  // Delete the last element from CircularLinkedList
  def deleteLastElement : Unit = {
    // Base check
    if(this.head == null)
      return
    // Base check
    if(this.head.next == this.head){
      this.head = null
      this.length = 0
      return
    }

    // Regular cases
    var pointer : Node = this.head
    var prev : Node = null
    while(pointer.next != head) {
      prev = pointer
      pointer = pointer.next
    }
    prev.next = this.head
    lengthDecrement
  }

  // Delete element at a given index
  def removeAt(index: Int) : Unit = {
    // Base check
    if(index > this.length - 1) {
      println(s"Error: Invalid index [${index}]. Index should be between range [0, ${this.length-1}]")
      return
    }

    // If last element is to be deleted
    if(index == this.length-1) {
      deleteLastElement
      return
    }

    // If first element is to be deleted
    if(index == 0) {
      deleteFirstElement
      return
    }

    // Regular cases
    var pointer : Node = this.head
    var prev : Node = null
    var counter = 0
    while(counter < index){
      prev = pointer
      pointer = pointer.next
      counter += 1
    }
    // At the index
    prev.next = pointer.next
    lengthDecrement
  }
}