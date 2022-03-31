package dataStructures.impl.queues

object QueueCircularArrayTest {
  def main(args: Array[String]): Unit = {
    val q = new QueueCircularArray()
    // Before enqueuing an array
    println(q.printQueue)

    // Enqueueing elements one at a time
    for(i <- 1 to 5 by 2){
      q.enqueue(i)
      println(s"ENQUEUE => Front element (${q.front}): ${q.getFront}, Rear element (${q.rear}) : ${q.getRear}, Size of Queue: ${q.getSize}")
    }
    // Enqueue array  in action
    q.enqueueArray(6.to(20).by(4).toArray)

    // After enqueuing an array
    println(q.printQueue)

    // Dequeue in action
    while(q.isNotEmpty) {
      println(s"DEQUEUE => Front element: ${q.getFront}, Rear element: ${q.getRear}, Dequeue gave: ${q.dequeue}, Size of Queue: ${q.getSize}")
    }

    // After de-queueing the Queue
    println(q.printQueue)
  }
}