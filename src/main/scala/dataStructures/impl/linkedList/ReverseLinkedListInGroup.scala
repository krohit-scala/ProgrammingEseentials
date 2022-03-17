package dataStructures.impl.linkedList

import dataStructures.impl.Node

object ReverseLinkedListInGroup {
  // Reverse the LinkedList in groups of K
  def reverseLinkedListInGroup(_head: Node, k: Int): Node = {
    var head : Node = _head
    var prev : Node = null
    var curr : Node = head
    var next : Node = null

    var counter = 0
    // Reverse till the k-th element
    while(curr != null && counter < k){
      counter += 1
      next = curr.next
      curr.next = prev
      prev = curr
      curr = next
    }
    // If there are more than K elements, reverse them too
    if(next != null)
      head.next = reverseLinkedListInGroup(next, k)
    prev
  }

  def main(args: Array[String]): Unit = {
    // Create a LinkedList
    val arr = 1.to(10).reverse
    var head : Node = null
    for(elem <- arr){
      head = new Node(elem, head)
    }

    // Original State
    println(s"Original State")
    head.printList

    // Reverse the LinkedList in Group of K's
    val k = 3
    val newHead : Node = reverseLinkedListInGroup(head, k)
    newHead.printList
  }
}