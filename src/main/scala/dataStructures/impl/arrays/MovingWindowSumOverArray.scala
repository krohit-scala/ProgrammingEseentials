package dataStructures.impl.arrays

import dataStructures.impl.queues.QueueArray

import scala.collection.mutable

object MovingWindowSumOverArray {
  def maxSlidingWindow(arr: Array[Int], k: Int): Array[Int] = {
    if(arr.length < k || k == 0)
      throw new Exception("Error: Invalid values supplied!")
    else{
      val n = arr.length
      val res = Array.fill(n - k + 1){-1}

      val q = new mutable.Queue[Int]()
      var currMax = Int.MinValue
      var currMin = Int.MaxValue
      var index = 0
      var counter = 0
      // TBD
      for(i <- 0 to (n - 1)){
        // TBD
      }
      res
    }
  }

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

    val nums = Array[Int](1,3,-1,-3,5,3,6,7)
    val k = 3
    var res = maxSlidingWindow(nums, k)
    println(s"\nOriginal Array: ${nums.mkString(", ")}")
    println(s"Wind Sum Array: ${res.mkString(", ")}")
  }
}
