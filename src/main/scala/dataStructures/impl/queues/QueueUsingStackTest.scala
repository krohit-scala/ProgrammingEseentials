package dataStructures.impl.queues

object QueueUsingStackTest {
  def main(args: Array[String]): Unit = {
    val q : QueueUsingStack = new QueueUsingStack

    // Enqueue in Queue
    for(elem <- 1 to 5) {
      q.enqueue(elem)
      println(q.printQueue)
    }

    // Queue state after enqueue
    println(q.printQueue)

    while(q.isNotEmpty) {
      println(s"Element at the front: ${q.getFront}")
      q.dequeue
    }

    // Queue state after de-queue
    // Will give Exception
    println(q.printQueue)
  }
}
