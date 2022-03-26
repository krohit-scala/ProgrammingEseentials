package dataStructures.impl.stack

object TestStack {
  def main(args: Array[String]): Unit = {
    // Get the Stack object
    var stack = new StackArray()

    // Top of the empty Stack
    var msg = stack.peek match{
      case Some(x) => x
      case None => "Error: Stack is empty!"
    }
    println(s"Element at the top: ${msg}")

    // Popping the empty Stack
    // NullPointerException: ERROR: Cannot pop an empty Stack!
    // println(s"Top of the empty Stack: ${stack.pop}")

    // Push element to the stack
    val arr = 0.to(11).toArray
    stack.pushArray(arr)

    println(s"Element at the top: ${stack.peek}")

    // Popping a non-empty Stack
    while(!stack.isEmpty)
      stack.pop

    println(s"\nStackEmpty: ${stack.isEmpty}, StackFull: ${stack.isFull}\n" + stack.printStack)
  }
}
