package dataStructures.impl.linkedList

object ReverseLinkedList {
  def main(args: Array[String]): Unit = {
    var head = new Node(1)
    head.next = new Node(2)
    head.next.next = new Node(3)
    head.next.next.next = new Node(4)

    // Reverse the LinkedList
    var curr : Node = head
    var prev : Node = null
    var temp : Node = null

    while(curr != null){
      temp = curr.next
      curr.next = prev
      prev = curr
      curr = temp
    }
    head = prev

    // Print the reversed LinkedList
    var pointer : Node = head
    while(pointer != null) {
      println(pointer.data)
      pointer = pointer.next
    }

  }
}
