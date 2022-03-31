package dataStructures.impl.queues

object QueueArrayTest {
  def main(args: Array[String]): Unit = {
    val q = new QueueArray()
    // Before enqueuing an array
    println(q.printQueue)

    // Enqueueing elements one at a time
    for(i <- 1 to 7 by 3){
      q.enqueue(i)
      println(s"Front element: ${q.getFront}, Rear element: ${q.getRear}, Size of Queue: ${q.getSize}")
    }
    // Enqueue array  in action
    q.enqueueArray(3.to(20).by(2).toArray)

    // After enqueuing an array
    println(q.printQueue)

    // Dequeue in action
    while(q.isNotEmpty) {
      println(s"Front element: ${q.getFront}, Rear element: ${q.getRear}, Dequeue gave: ${q.dequeue}, Size of Queue: ${q.getSize}")
    }

    // After de-queueing the Queue
    println(q.printQueue)
  }
}
