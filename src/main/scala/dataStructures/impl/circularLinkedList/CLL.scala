package dataStructures.impl.circularLinkedList

object CLL {
  def main(args: Array[String]): Unit = {
    val cll : CircularLinkedList = new CircularLinkedList()
    cll.printList
    val arr = 1.to(9).toArray

    // Push and append into the CircularLinkedList
    for(elem <- arr){
      cll.append(elem)
      cll.push(elem)
    }
    cll.printList

    // Clear the CircularLinkedList
    cll.clear
    cll.printList

    // Append array to the CircularLinkedList
    cll.appendArray(0.to(3).toArray)
    cll.printList
    println(cll.length)

    // Remove element at the beginning of CircularLinkedList
    cll.deleteFirstElement
    cll.printList
    println(cll.length)

    // Remove element at the end of CircularLinkedList
    cll.deleteLastElement
    cll.printList
    println(cll.length)

    // Delete an element at a given index
    cll.removeAt(3)
    cll.printList
    // Delete an element at a given index
    cll.removeAt(1)
    cll.printList
    println(cll.length)
  }
}
