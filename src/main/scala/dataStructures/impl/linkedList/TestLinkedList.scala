package dataStructures.impl.linkedList

object TestLinkedList {
  def main(args: Array[String]): Unit = {
    val ll = new LinkedList()
    // ll.head = new Node(-1)  // #1: Not needed anymore
    val head = ll.head

    // Insert data in LinkedList
    val arr = Array(5, 10, 15, 20, 25, 35)
    ll.appendArray(arr)

    // Insert at the beginning of LinkedList
    ll.push(0)

    // Insert data at the end
    ll.append(40)

    // Insert after 25
    ll.insertAfter(31, 25)

    // Delete the item
    ll.delete(31)

    // Insert after 25
    ll.insertAfter(30, 25)

    // Delete nth item from LinkedList
    ll.deleteAtIndex(9)

    // Length of LinkedList
    println(s"Length of LinkedList is: ${ll.length}\n")

    // Delete nth item from LinkedList
    ll.reverse()
  }
}
