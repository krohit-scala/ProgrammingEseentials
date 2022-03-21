package dataStructures.impl.linkedList

import dataStructures.impl.Node

object DetectAndRemoveLoop {

  def removeLoop(_slow: Node, _head: Node): Node = {
    var meet : Node = _slow       // Node where slow and fast pointers met
    var curr : Node = _head       // Head of original LinkedList
    var loopStart : Node = null   // Pointer to detect the point at which the loop starts
    var prev : Node = null        // Pointer to node which points to loopStart
    // Find node where the loop starts
    // Traverse till meet pointer meets with the curr pointer
    while(meet != curr){
      prev = meet
      curr = curr.next
      meet = meet.next
    }
    // When meet == curr, this is the starting point of loop
    loopStart = curr
    prev.next = null
    // If we want to use one more loop instead of prev pointer
    // Loop again to reach the node which points loopStart
    // while(meet.next != loopStart)
    //   meet = meet.next

    // When we are at the end of the looping element
    // meet.next = null
    _head
  }

  def detectLoopUsingFloydAlgo(_head: Node): Node = {
    val head : Node = _head
    var slow : Node = head
    var fast : Node = head

    // Fast and slow counters will meet some time
    while(fast != null && slow != null){
      slow = slow.next
      fast = fast.next.next

      // Loop Detected
      if(slow == fast)
        return removeLoop(slow, head)
    }
    head
  }

  def detectLoopUsingHashmap(_head: Node): Node = {
    var head = _head
    var set = scala.collection.mutable.HashSet[Node]()

    var pointer : Node = head
    var prev : Node = null
    var flag = true
    while(pointer.next != null && flag){
      if(!set.contains(pointer)){
        val currRef = pointer
        set.add(currRef)
        prev = pointer
        pointer = pointer.next
      }
      else{
        prev.next = null
        flag = false
      }
    }
    head
  }

  def main(args: Array[String]): Unit = {
    // Create a LinkedList
    val arr = 1.to(5).reverse
    var head1 : Node = null
    var head2 : Node = null
    for(elem <- arr){
      head1 = new Node(elem, head1)
      head2 = new Node(elem, head2)
    }
    // Before loop
    println("Before loop")
    head1.printList

    // Create a loop
    head1.next.next.next.next.next = head1.next.next
    // head1.printList
    head2.next.next.next.next.next = head2.next.next
    // head2.printList

    println("After loop")
    head1 = detectLoopUsingFloydAlgo(head1)
    head1.printList

//    head2 = detectLoopUsingHashmap(head2)
//    head2.printList
  }
}
