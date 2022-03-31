package dataStructures.impl.queues

object QueueArrayTest {
  def main(args: Array[String]): Unit = {
    val q = new QueueArray()
    // Before enqueuing an array
    println(q.printQueue)

    // Enqueue in action
    q.enqueueArray(3.to(20).by(2).toArray)

    // After enqueuing an array
    println(q.printQueue)

    // Dequeue in action
    while(q.isNotEmpty) {
      println(s"Dequeue gave: ${q.dequeue}, Size of Queue: ${q.getSize}")
    }

    // After dequeuing the Queue
    println(q.printQueue)
  }
}
