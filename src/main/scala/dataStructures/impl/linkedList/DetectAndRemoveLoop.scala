package dataStructures.impl.linkedList

import dataStructures.impl.Node

object DetectAndRemoveLoop {

  def removeLoop(_slow: Node, _head: Node): Node = {
    ???
  }

  def detectLoopUsingFloydAlgo(_head: Node): Node = {
    var head : Node = _head
    var slow : Node = head
    var fast : Node = head.next

    while(fast != null && slow != null){
      slow = slow.next
      fast = fast.next.next

      // Loop Detected
      if(slow == fast){
        slow = slow.next
        fast = fast.next
        return removeLoop(slow, head)
      }
    }
    slow.next = null
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
//    head1 = detectLoopUsingFloydAlgo(head1)
//    head1.printList

    head2 = detectLoopUsingHashmap(head2)
    head2.printList
  }
}
