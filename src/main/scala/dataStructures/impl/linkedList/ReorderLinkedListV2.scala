package dataStructures.impl.linkedList

// Given a LinkedList, L1 = {A1, A2, A3, ..., An}
// Modify it, without using extra space as, L1 = {A1, An, A2, An-1, A3, ...}

object ReorderLinkedListV2 {

  def reorderLinkedList : Unit = {
    val arr = 1.to(10).toArray
    val ll = new LinkedList()

    // Populate the LinkedList
    ll.appendArray(arr)

    // Step-1: Divide in halves
    val temp = ll.divideInHalves
    var l1 = temp._1
    var l2 = temp._2

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

    val newLL = new LinkedList()
    newLL.head = h1
    newLL.printList
  }


  def main(args: Array[String]): Unit = {
    // A1: Reordering a LinkedList
    this.reorderLinkedList

  }
}
