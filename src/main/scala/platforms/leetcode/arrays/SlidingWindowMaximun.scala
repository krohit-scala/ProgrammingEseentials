//package platforms.leetcode.arrays
//
//// https://leetcode.com/problems/sliding-window-maximum/
//object SlidingWindowMaximun {
//  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
//    // Length of input array
//    val n = nums.length
//
//    // Base conditions
//    if(k > n)
//      return null
//
//    // Number of iterations
//    val numWindows = n - k + 1
//
//    // Holds current max for the current window
//    var currMax = nums(0)
//    val maxStack = scala.collection.mutable.ArrayDeque[Int]()
//
//    // Hold the final output
//    val result = Array.fill {Integer.MIN_VALUE}(numWindows)
//
//    // Main algorithm
//    for(i <- 0 until(numWindows)){
//      // Let the window get to size k
//      if(i < k){
//        if(currMax < nums(i))
//          currMax = nums(i)
//      }
//      else{
//        if(maxStack.isEmpty)
//          maxStack.push(currMax)
//        else{
//          if(nums(i-k-1) == maxStack.top)
//        }
//      }
//    }
//    result
//  }
//
//  def main(args: Array[String]): Unit = {
//    val arr = Array(1,3,-1,-3,5,3,6,7)
//    val result = maxSlidingWindow(arr)
//    println(result.mkString(", "))
//  }
//}
