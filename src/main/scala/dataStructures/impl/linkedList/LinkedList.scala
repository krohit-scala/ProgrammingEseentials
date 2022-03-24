package dataStructures.impl.linkedList

class LinkedList {
  var head : Node = null
  var length : Int = 0

  // Initialize LinkedList
  def initialize(data: Int) : Unit = {
    if(head == null){
      val newNode : Node = new Node(data)
      head = newNode
      lengthIncrement
    }
  }

  // Increment the length
  def lengthIncrement : Unit = {this.length += 1}

  // Increment the length
  def lengthDecrement : Unit = {
    if(this.length > 0)
      this.length -= 1
    else
      this.length = 0
  }

  // Print the LinkedList
  def printList : Unit = {
    var ptr = this.head
    var counter = 1
    var msg = "head ---> "
    while(ptr != null){
      if(counter == 1)
        msg = msg + s"${head.data}"
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
    if(head == null)
      initialize(data)
    else {
      val newNode = new Node(data)
      newNode.next = head
      head = newNode
      lengthIncrement
    }
    // Print output
    // printList()
  }

  // Add element at the end
  def append(data: Int) : Unit = {
    if(head == null)
      initialize(data)
    else {
      val newNode = new Node(data)
      var pointer = head
      while (pointer.next != null)
        pointer = pointer.next

      pointer.next = newNode
      lengthIncrement
    }

    // Print output
    // printList()
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
      lengthIncrement
    }

    // Print output
    // printList()
  }

  // Add array to LinkedList; appends at the end of the LinkedList
  def appendArray(arr: Array[Int]) : Unit = {
    // Insert elements one by one
    for(elem <- arr){
      // If LinkedList is empty, initialize it with first element of the array
      this.append(elem)
    }

    // Print the output
    printList
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
      lengthDecrement
    }

    // Print the output
    // printList()
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
      prev.next = pointer.next
      lengthDecrement
    }

    // Print the output
    // printList()
  }

  // Returns the length of the LinkedList
  def getLength: Int = {
    var pointer = head
    var counter = if(head != null) 1 else 0
    while(pointer.next != null) {
      pointer = pointer.next
      counter += 1
    }
    counter
  }

  // Reverse the LinkedList
  def reverse() : Unit = {
    var curr : Node = head
    var prev : Node = null
    var temp : Node = null

    while(curr != null){
      temp = curr.next
      curr.next = prev
      prev = curr
      curr = temp
    }
    // Reassign the head
    head = prev

    // Print output
    printList
  }

  // Divide a LinkedList into two equal halves
  // P.S.: The original LinkedList is divided
  def divideInHalves : (LinkedList, LinkedList) = {
    var head : Node = this.head
    var slow : Node = this.head
    var fast : Node = this.head

    while(fast.next != null){
      slow = slow.next
      fast = fast.next
      if(fast.next != null)
        fast = fast.next
    }

    val h1 : Node = head
    val h2 : Node = slow.next
    slow.next = null

    val l1 = new LinkedList()
    val l2 = new LinkedList()
    l1.head = h1
    l2.head = h2
    l1.length = l1.getLength
    l2.length = l2.getLength

    this.length = this.getLength
    // Return the LinkedLists
    (l1, l2)
  }
}