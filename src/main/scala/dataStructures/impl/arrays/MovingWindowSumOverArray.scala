package dataStructures.impl.arrays

import dataStructures.impl.queues.QueueArray

object MovingWindowSumOverArray {

  def getMovingWindowSumArray(arr: Array[Int], win: Int) : Array[Int] = {
    if(arr.length < win || win == 0)
      throw new Exception("Error: Invalid values supplied!")
    else{
      val n = arr.length
      val res = Array.fill(n - win + 1){-1}

      val q = new QueueArray
      var currSum = 0
      var index = 0
      for(i <- 0 until n) {
        val elem = arr(i)
        if (q.length < win) {
          q.enqueue(elem)
          currSum += elem
          if(q.length == win) {
            res(index) = currSum
            index += 1
          }
        }
        else{
          currSum -= q.dequeue
          currSum += elem
          q.enqueue(elem)
          res(index) = currSum
          index += 1
        }
      }
      res
    }
  }

  def main(args: Array[String]): Unit = {
    val arr : Array[Int] = Array.fill(10){-1}
    val rand = scala.util.Random
    for(i <- 0 until 10)
      arr(i) = -20 + rand.nextInt((20 - (-20)) + 1)

    val wind = 4
    println(s"Original Array: ${arr.mkString(", ")}")
    println(s"Wind Sum Array: ${getMovingWindowSumArray(arr, wind).mkString(", ")}")
  }
}
