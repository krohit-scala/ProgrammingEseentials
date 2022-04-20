package platform.hackerrank.dataStructures.array.easy

import scala.io.StdIn

object ArrayManipulation {
  // Does that in O[N^2]
  def arrayManipulationOptimized(n: Int, queries: Array[Array[Int]]): Long = {
    // Write your code here
    val data : Array[Long] = Array.fill(n+1){0}
    var maxVal : Long = Int.MinValue
    for(arr <- queries) {
      // Query parameters
      val start = arr(0)
      val end = arr(1)
      val delta = arr(2)

      // Manage margins
      data(start-1) += delta
      data(end) -= delta
    }

    // Run prefix-sum algorithm
    var tempSum : Long = 0
    for(d <- data){
      tempSum += d
      if(tempSum > maxVal)
        maxVal = tempSum
    }
    maxVal
  }

  // Does that in O[N^2]
  def arrayManipulation(n: Int, queries: Array[Array[Int]]): Long = {
    // Write your code here
    val data : Array[Long] = Array.fill(n){0}
    var maxVal : Long = Int.MinValue

    var counter = 0
    // Execute the queries
    for(arr <- queries){
      counter += 1
      // Query parameters
      val start = arr(0)
      val end = arr(1)
      val delta = arr(2)

      // Apply queries
      for(i <- start to end) {
        data(i-1) = data(i-1) + delta
        if(maxVal < data(i-1))
          maxVal = data(i-1)
      }
    }

    maxVal
  }

  def main(args: Array[String]): Unit = {
    println("Set number of parameters:")
    val params = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val n = params(0)
    val m = params(1)

    val queries = Array.ofDim[Int](m, 3)
    println("Enter the parameters:")
    for (i <- 0 until m) {
      queries(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }
    println(arrayManipulation(n, queries))
    println(arrayManipulationOptimized(n, queries))
  }
}