package dataStructures.impl.linkedList

import dataStructures.impl.Node

object RotateLinkedList {

  // Brute force approach
  def rotateLinkedListBruteForce(_head: Node, k: Int): _root_.dataStructures.impl.Node = {
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


  def rotateLinkedListCircular(_head: Node, k: Int) : Node = {
    // Base check
    if(_head == null || _head.next == null)
      return _head

    // Make the LinkedList circular and then break it at k-th Node
    var head = _head
    var pointer : Node = head
    var prev : Node = null
    while(pointer != null) {
      prev = pointer
      pointer = pointer.next
    }

    // Make last Node point to current 1-st
    prev.next = head
    // Reset the pointer to current node
    pointer = head

    // Find the k-th Node
    var counter = 0
    while(counter < k){
      prev = pointer
      pointer = pointer.next
      counter += 1
    }
    // Break at k-th Node
    prev.next = null

    // Make the current Node head
    head = pointer

    // Return the new head of the resultant LinkedList
    head
  }

  def main(args: Array[String]): Unit = {
    // Build a LinkedList
    val arr = Array[Int](22, 99, 11, 88, 55, 33, 77, 44, 66)
    var head1 : Node = null
    var head2 : Node = null
    for(elem <- arr) {
      head1 = new Node(elem, head1)
      head2 = new Node(elem, head2)
    }
    head1.printList

    // Rotate LinkedList by K elements
    val k = 3
    val newHead1 : Node = rotateLinkedListBruteForce(head1, k)
    newHead1.printList
    val newHead2 = rotateLinkedListCircular(head2, k)
    newHead2.printList
  }
}
