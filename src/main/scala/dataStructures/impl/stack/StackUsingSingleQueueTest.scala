package dataStructures.impl.stack

// Main program to call test the StackUsingQueues
object StackUsingSingleQueueTest {
  def main(args: Array[String]): Unit = {
    // Instantiate the Stack class
    val stack = new StackUsingSingleQueue()

    // Push to Stack
    for(elem <- 2.to(12).by(3)) {
      println(s"Element getting pushed: ${elem}")
      stack.push(elem)
    }

    // State of Stack after push
    println(s"\nLength after all push's: ${stack.length}\n")

    // Pop the Stack till empty
    while(stack.isNotEmpty){
      println(s"Element to be removed: ${stack.peek}, Length: ${stack.length}")
      stack.pop
    }

    // State of Stack after pop
    println(s"\nLength after all pops: ${stack.length}\n")
  }
}