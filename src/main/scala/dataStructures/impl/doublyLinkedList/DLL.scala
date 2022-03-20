package dataStructures.impl.doublyLinkedList

object DLL {
  def main(args: Array[String]): Unit = {
    var dll = new DoublyLinkedList()

    // Push element at the beginning
    dll.push(3)

    // Delete the first element
    dll.deleteFirstElement

    // Append an element at the end
    dll.append(1)

    // Append the array at the end of the LinkedList
    val arr = 3.to(5).toArray
    dll.appendArray(arr)

    // Insert an item after a given element
    dll.insertAfter(1, 2)

    // Push 0 at the start of the LinkedList
    dll.push(0)
    dll.printList

    // Delete first element of the Doubly LinkedList
    dll.deleteFirstElement
    dll.printList

    dll.deleteLastElement
    dll.deleteLastElement
    dll.deleteLastElement
    dll.deleteLastElement
    dll.deleteLastElement
    dll.deleteLastElement
    dll.printList
    println(dll.length)


  }
}
