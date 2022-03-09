package dataStructures.impl.linkedList

import dataStructures.impl.Node

class LinkedList {
  var head : Node = null

  // Print the LinkedList
  def printList() : Unit = {
    var ptr = this.head
    var counter = 1
    var msg = ""
    while(ptr != null){
      if(counter == 1)
        msg = "head"
      else
        msg = msg + " ---> " + ptr.data
      counter += 1
      ptr = ptr.next
    }
    msg = msg + " ---> end"
    println(s"LIST OUTPUT: \n$msg\n")
  }

  // Add an element at the beginning
  def push(data: Int) : Unit = {
    val newNode = new Node(data)
    newNode.next = head.next
    head.next = newNode

    // Print output
    printList()
  }

  // Add element at the end
  def append(data: Int) : Unit = {
    val newNode = new Node(data)
    var pointer = head
    while(pointer.next != null)
      pointer = pointer.next

    pointer.next = newNode

    // Print output
    printList()
  }

  // Add element after a given element
  def insertAfter(data: Int, item: Int) : Unit = {
    var pointer = head
    while(pointer != null && pointer.data != item)
      pointer = pointer.next
    if(pointer == null)
      println(s"ERROR: Item [${item}] not found! Insertion of [${data}] after [${item}] failed!\n")
    else{
      val newNode = new Node(data)
      newNode.next = pointer.next
      pointer.next = newNode
    }

    // Print output
    printList()
  }

  // Add array to LinkedList; appends at the end of the LinkedList
  def appendArray(arr: Array[Int]) : Unit = {
    // Go to the end
    var pointer = head
    while(pointer.next != null)
      pointer = pointer.next

    // Insert elements one by one
    for(elem <- arr){
      append(elem)
    }
  }

  // Delete a given item from LinkedList
  def delete(item: Int) : Unit = {
    var pointer = head
    var prev = head
    // Find the item to be deleted
    while(pointer != null && pointer.data != item) {
      prev = pointer
      pointer = pointer.next
    }

    // Confirm if item exists and then delete
    if(pointer == null)
      println(s"ERROR: Cannot delete item [${item}]! Item does not exist!")
    else{
      prev.next = pointer.next
    }

    // Print the output
    printList()
  }

  // Delete an item at a given index
  def deleteAtIndex(index: Int) : Unit = {
    var pointer = head
    var prev = head
    var counter = -1
    while(pointer.next != null && counter < index){
      prev = pointer
      pointer = pointer.next
      counter += 1
    }

    if(counter < index)
      println(s"ERROR: Cannot delete. Index [${index}] exceeds the index range for the elements [0 to ${counter}]!\n")
    else{
      println(s"PointerIndex: ${counter}")
      prev.next = pointer.next
    }

    // Print the output
    printList()
  }

  def length: Int = {
    var pointer = head
    var counter = 0
    while(pointer.next != null) {
      pointer = pointer.next
      counter += 1
    }
    counter
  }
}
