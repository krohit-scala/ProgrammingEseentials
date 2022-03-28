package dataStructures.impl.arrays

object TrappingRainWater {
  // Given n non-negative integers representing an elevation map where the width
  // of each bar is 1, compute how much water it can trap after raining.
  def trappingRainWater(arr: Array[Int]): Int = {
    val n = arr.length
    val left = Array.fill(n){-1}
    val right = Array.fill(n){-1}

    // Temp variables
    var maxLeft = 0
    var maxRight = 0
    // Maximum on left
    for(i <- 0 until n){
      if(arr(i) > maxLeft)
        maxLeft = arr(i)
      left(i) = maxLeft
    }
    // Maximum on left
    for(i <- (n-1) to 0 by -1){
      if(arr(i) > maxRight)
        maxRight = arr(i)
      right(i) = maxRight
    }

    // println(s"Orig Arr : ${arr.mkString(", ")}")
    // println(s"Max Left : ${left.mkString(", ")}")
    // println(s"Max Right : ${right.mkString(", ")}")

    var res = 0
    for(i <- 0 until n)
      res += (math.min(right(i), left(i))) - arr(i)
    res
  }


  def main(args: Array[String]): Unit = {
    val arr : Array[Int] = Array[Int](4,2,0,3,2,5)
    val maxWater = trappingRainWater(arr)
    println(s"Units of water trapped: ${maxWater}")
  }
}
