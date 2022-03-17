package dataStructures.impl.linkedList

import dataStructures.impl.Node

object ReverseLinkedListInGroup {
  // Reverse the LinkedList in groups of K
  def reverseLinkedListInGroup(_head: Node, k: Int): Node = {
    var head : Node = _head
    var prev : Node = null
    var curr : Node = head
    var temp : Node = null

    var counter = 0
    // Reverse till the k-th element
    while(curr != null && counter < k){
      counter += 1
      temp = curr.next
      curr.next = prev
      prev = curr
      curr = temp
    }
    // If there are more than K elements, reverse them too
    if(temp != null)
      head.next = reverseLinkedListInGroup(temp, k)
    prev
  }

  def main(args: Array[String]): Unit = {
    var arr = Array[Int](1,2,3,4,5,6,7,8,9,10,11).reverse
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