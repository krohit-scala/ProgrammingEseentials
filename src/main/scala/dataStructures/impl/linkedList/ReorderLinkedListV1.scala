package dataStructures.impl.linkedList

// Given a LinkedList, L1 = {A1, A2, A3, ..., An}
// Modify it, without using extra space as, L1 = {A1, An, A2, An-1, A3, ...}
object ReorderLinkedListV1 {

  def divideInHalves(list: LinkedList): (LinkedList, LinkedList) = {
    var head = list.head
    var slow : Node = head
    var fast : Node = head

    // Reach the middle of the LinkedList
    while(fast.next != null){
      slow = slow.next
      fast = fast.next
      if(fast.next != null)
        fast = fast.next
    }
    val newHead : Node = slow.next
    slow.next = null

    // Return the output
    val ll1 = new LinkedList()
    ll1.head = head
    val ll2 = new LinkedList()
    ll2.head = newHead

    (ll1, ll2)
  }

  def main(args: Array[String]): Unit = {
    var l1 = new LinkedList
    l1.appendArray(1.to(9).toArray)

    // Split the LinkedLIst into two halves
    val twoHalves : (LinkedList, LinkedList) = divideInHalves(l1)
    var h1 = twoHalves._1
    var h2 = twoHalves._2
    h2.reverse()

    var p1 = h1.head
    var p2 = h2.head
    var p1Next : Node = null
    var p2Next : Node = null
    while(p2 != null && p2.next != null){
      println(s"P1.data: ${p1.data}, ${p2.data}")
      // Keep track of next nodes
      p1Next = p1.next
      p2Next = p2.next

      p1.next = p2
      p2.next = p1Next

      p2 = p2Next
      p1 = p1Next
    }
    h1.printList
  }
}
