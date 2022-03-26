package dataStructures.impl.stack

object TestStackLinkedList {
  def main(args: Array[String]): Unit = {
    // Get the Stack object
    var stack = new StackLinkedList()

    // Popping the empty Stack
    // NullPointerException: ERROR: Cannot pop an empty Stack!
    // println(s"Top of the empty Stack: ${stack.peek}")

    // Push element to the stack
    val arr = 0.to(4).toArray
    stack.pushArray(arr)

    println(s"Element at the top: ${stack.peek}")

    // Popping a non-empty Stack
    while(!stack.isEmpty)
      println("Element popped: " + stack.pop)

    // Pop an empty Stack
    // print(stack.pop) // NullPointerException: ERROR: Cannot pop an empty Stack!
    println(s"\nStackEmpty: ${stack.isEmpty}\n" + stack.ll.printList)
  }
}
