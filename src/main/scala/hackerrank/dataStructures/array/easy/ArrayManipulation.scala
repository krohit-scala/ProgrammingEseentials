package hackerrank.dataStructures.array.easy

import scala.io.StdIn

object ArrayManipulation {
  // Does that in O[N^2]
  def arrayManipulation(n: Int, queries: Array[Array[Int]]): Long = {
    // Write your code here
    val data : Array[Long] = Array.fill(n){0}

    var counter = 0
    // Execute the queries
    for(arr <- queries){
      counter += 1
      // Query parameters
      val start = arr(0)
      val end = arr(1)
      val delta = arr(2)

      // Apply queries
      for(i <- start to end)
        data(i-1) = data(i-1) + delta
    }

    // Find max val
    var maxVal : Long = Int.MinValue
    for(d <- data)
      if(maxVal < d) maxVal = d

    maxVal
  }

  def main(args: Array[String]): Unit = {
    val m = 3
    val queries = Array.ofDim[Int](m, 3)
    println("Enter the parameters:")
    for (i <- 0 until m) {
      queries(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }
    println(arrayManipulation(5, queries))
  }
}
