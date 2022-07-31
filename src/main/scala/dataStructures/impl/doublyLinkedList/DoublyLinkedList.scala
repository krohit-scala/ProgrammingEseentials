package dataStructures.impl.doublyLinkedList

import scala.collection.mutable.ArrayBuffer

class DoublyLinkedList {
  var head : Node = null
  var length : Int = 0

  // Print Doubly LinkedList
  def printList: Unit = {
    var pointer : Node = this.head
    val dataList = ArrayBuffer[Int]()
    while(pointer != null) {
      dataList.append(pointer.data)
      pointer = pointer.next
    }
    val msg = "head --> " + dataList.mkString(" --> ") + " --> end"
    println(msg)
  }

  // Initialize DoublyLinkedList
  def initialize(_data: Int) : Unit = {
    val newNode : Node = new Node(_data, null, null)
    this.head = newNode
    this.length = 1
  }

  // Increase the length
  def lengthIncrement: Unit = this.length += 1
  // Decrease the length
  def lengthDecrement: Unit = {
    if(length > 0)
      this.length -= 1
    else
      this.length = 0
  }

  // Insert element at the beginning
  def push(_data: Int) : Unit = {
    if(this.head == null) {
      initialize(_data)
    }
    else {
      val newNode: Node = new Node(_data, null, null)
      newNode.next = head
      head.prev = newNode
      head = newNode
      lengthIncrement
    }
  }

  // Insert element at the end
  def append(_data: Int) : Unit = {
    // If Doubly LinkedList is empty
    if (head == null)
      initialize(_data)
    else{
      // Go to the end node and append the new node
      var pointer : Node = this.head
      while(pointer.next != null)
        pointer = pointer.next
      val newNode : Node = new Node(_data, null, pointer)
      pointer.next = newNode

      // Increment the length
      lengthIncrement
    }
  }

  // Append array to DoublyLinkedList
  def appendArray(arr: Array[Int]) : Unit = {
    for(elem <- arr){
      this.append(elem)
    }
  }

  // Insert item after a given element
  def insertAfter(findItem: Int, newItem: Int) ={
    var pointer : Node = this.head
    while(pointer != null && findItem != pointer.data){
      pointer = pointer.next
    }
    // Confirm if item is found
    if(pointer == null)
      println(s"Error: Item (${findItem}) not found! Insertion failed!")
    else{
      val newNode : Node = new Node(newItem, pointer.next, pointer)
      pointer.next = newNode
      lengthIncrement
    }
  }

  // Delete the first element
  def deleteFirstElement : Unit = {
    // If LinkedList has more than one elements, remove first
    if(head != null && head.next != null) {
      head = head.next
      head.prev = null
      lengthDecrement
    }
    else {
      head = null
      lengthDecrement
    }
  }

  // Delete the last element
  def deleteLastElement : Unit = {
    // Base check
    if(head == null)
      return
    // If only one element
    if(head != null && head.next == null) {
      head = null
      lengthDecrement
      return
    }

    // Traverse through the end and delete the last
    var pointer : Node = this.head
    var prev : Node = null
    while(pointer.next != null){
      prev = pointer
      pointer = pointer.next
    }
    prev.next = null
    lengthDecrement
  }

  // Delete element at a given index in the LinkedList
  def removeAt(index: Int) : Unit = {
    // Base check
    if(index > length-1)
      println(s"Error: Invalid index! Index (${index}) is not in range [0, ${length-1}]")
    else if(index == 0){
      deleteFirstElement
    }else if(index == this.length-1){
      deleteLastElement
    }
    else{
      var counter = -1
      var pointer : Node = head
      var prev : Node = null
      while(pointer != null && counter < index){
        counter += 1
        prev = pointer
        pointer = pointer.next
      }
      // At the given index
      prev.next = pointer.next
      pointer.next.prev = prev
      lengthDecrement
    }
  }

  // Flush the LinkedList
  def clear : Unit = {
    this.head = null
    this.length = 0
  }
}
