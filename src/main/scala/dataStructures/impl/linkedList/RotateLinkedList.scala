package dataStructures.impl.linkedList

import dataStructures.impl.Node

object RotateLinkedList {

  def rotateLinkedList(_head: Node, k: Int): _root_.dataStructures.impl.Node = {
    // Base check
    if(_head == null || _head.next == null)
      return _head

    var head : Node = _head       // Current head of the LinkedList
    var newHead : Node = null     // Head of the resultant LinkedList
    var pointer : Node = head     // Iterator for the LinkedList
    var prev : Node = null        // Holds the previous visited Node
    var counter = 0               // Counter for the iteration over Nodes

    // Iterate through the LinkedList
    while(pointer != null){
      prev = pointer
      pointer = pointer.next
      counter += 1
      // If we reach the desired Node
      if(counter == k){
        prev.next = null      // Break the LinkedList from that point
        newHead = pointer     // Assign new head for resultant LinkedList
      }
    }
    // Point the last Node of the LinkedList to prev head
    prev.next = head
    newHead
  }

  def main(args: Array[String]): Unit = {
    // Build a LinkedList
    val arr = Array[Int](22, 99, 11, 88, 55, 33, 77, 44, 66)
    var head : Node = null
    for(elem <- arr)
      head = new Node(elem, head)
    head.printList

    // Rotate LinkedList by K elements
    val k = 3
    val newHead : Node = rotateLinkedList(head, k)
    newHead.printList
  }
}
