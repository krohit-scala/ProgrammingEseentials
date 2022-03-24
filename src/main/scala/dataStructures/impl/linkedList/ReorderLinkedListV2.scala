package dataStructures.impl.linkedList

// Given a LinkedList, L1 = {A1, A2, A3, ..., An}
// Modify it, without using extra space as, L1 = {A1, An, A2, An-1, A3, ...}

object ReorderLinkedListV2 {
  // Get new LinkedList of size N
  // Nodes values from 1 to N
  def getLinkedList(n: Int) : LinkedList = {
    // Base check
    if(n < 1)
      return null

    val arr = 1.to(n).toArray
    val ll = new LinkedList()

    // Populate the LinkedList
    ll.appendArray(arr)
    ll
  }

  // Brute force I feel that's cheating
  // Swapping data (not Nodes) in pairs
  def reverseInPairsSwappingValues: Unit = {
    val ll = this.getLinkedList(5)
    val head : Node = ll.head
    var curr : Node = head
    while(curr != null && curr.next != null){
      val next : Node = curr.next
      val temp = curr.data
      curr.data = next.data
      next.data = temp

      curr = curr.next.next
    }
    ll.printList
  }

  // Swapping Nodes in pairs in a LinkedList
  def reverseInPairsSwappingNodes(_head: Node): Node = {
    // Base checks
    if(_head == null || _head.next == null)
      return _head

    // References preparation
    var head : Node = _head         // Points to the head of the LinkedList
    val left : Node = _head         // 1st Node of the LinkedList
    val right : Node = _head.next   // 2nd Node of the LinkedList
    val tail : Node = right.next    // Rest of the LinkedList Nodes after 2nd Node

    head = right        // Now head points the 2nd Node
    right.next = left   // Now 2nd Node points the 1st Node

    // Make the 1st Node point to the tail (rest of the reversed in pair LinkedList)
    left.next = reverseInPairsSwappingNodes(tail)
    head
  }

  // Swaps the Nodes of the LinkedList in pairs
  def swapNodesInPairs : Unit = {
    // Populate LinkedList
    val ll = this.getLinkedList(9)
    println(s"Before swapping...")
    ll.printList
    val head = ll.head
    ll.head = reverseInPairsSwappingNodes(head)
    println(s"After swapping...")
    ll.printList
  }

  // Given a LinkedList, L1 = {A1, A2, A3, ..., An}
  // Modify it, without using extra space as, L1 = {A1, An, A2, An-1, A3, ...}
  def reorderLinkedList : Unit = {
    // Get LinkedList
    val ll : LinkedList = getLinkedList(5)

    // Step-1: Divide in halves
    val temp = ll.divideInHalves
    val l1 = temp._1
    val l2 = temp._2

    // Testing
    // l1.printList
    // l2.printList
    // println(s"L length: ${ll.length}, L1 length: ${l1.length}, L2 length: ${l2.length}")

    // Step-2: Do the Reordering
    var h1 = l1.head
    var h2 = l2.head

    // Pointers for both LinkedList
    var p1 = h1
    var p2 = h2

    while(p2 != null){
      // Next Node Pointers
      val p1Next = p1.next
      val p2Next = p2.next

      // Pointer reassignments
      p1.next = p2
      p2.next = p1Next
      p2 = p2Next
    }
    p1.printList

    val newLL : LinkedList = new LinkedList()
    newLL.head = h1
    newLL.printList
  }


  def main(args: Array[String]): Unit = {
    // A1: Reordering a LinkedList
    // this.reorderLinkedList

    // A2: Reverse in pairs
    // this.reverseInPairsSwappingValues
    this.swapNodesInPairs
  }
}
