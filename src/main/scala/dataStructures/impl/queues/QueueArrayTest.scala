package dataStructures.impl.queues

object QueueArrayTest {
  def main(args: Array[String]): Unit = {
    val q = new QueuesCircularArray()
    q.enqueueArray(3.to(20).by(2).toArray)
    q.printQueue


  }
}
