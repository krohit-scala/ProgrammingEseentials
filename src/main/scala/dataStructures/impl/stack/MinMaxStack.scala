package dataStructures.impl.stack

object MinMaxStack {
  // Generate random numbers between range
  def getRandInt(start: Int, end: Int) : Int = {
    val rnd = new scala.util.Random
    val temp = start + rnd.nextInt((end - start) + 1)
    temp
  }

  def main(args: Array[String]): Unit = {
    val stack = new StackLinkedList()
    val minStack = new StackLinkedList()
    val maxStack = new StackLinkedList()

    // Vars for holding current min/max
    var min = Int.MaxValue
    var max = Int.MinValue

    // Generate 15 random numbers and push into stack
    for(num <- Array[Int](6, 3, 22, 20, 2, 2, 0)){
      // val num = getRandInt(1, 50)
      stack.push(num)
      if(num <= min) {
        minStack.push(num)
        min = num
      }
      if(num >= max) {
        maxStack.push(num)
        max = num
      }
    }

    minStack.printStack
    maxStack.printStack
    stack.printStack


    while(!stack.isEmpty){
      val curr = stack.pop
      if(minStack.peek == curr){
        minStack.pop
        // println("Popping MinStack...")
      }
      if(maxStack.peek == curr){
        maxStack.pop
        // println("Popping MaxStack...")
      }
    }

    minStack.printStack
    maxStack.printStack
    stack.printStack
  }
}
