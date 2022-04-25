package platforms.leetcode.arrays

import scala.collection.mutable

// https://leetcode.com/problems/two-sum/
object TwoSum {
  // Solution in time: O[N^2]
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    for(i <- 0 until nums.length){
      for(j<- i+1 until nums.length){
        if(nums(i) + nums(j) == target)
          return Array(i, j)
      }
    }
    null
  }

  // Solution in time: O[N], space: O[N]
  def twoSumOptimized(nums: Array[Int], target: Int): Array[Int] = {
    val map = mutable.HashMap[Int, Int]()

    // Iterate through
    for(i <- 0 until nums.length){
      // Check if the element exists in the map
      if(map.getOrElse(nums(i), null) == null)
        map += nums(i) -> i

      // Check if target exists
      if(map.getOrElse(target - nums(i), null) != null  && map.get(target - nums(i)).get != i )
        return Array(map.get(target - nums(i)).get, i)
    }
    null
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2,7,11,15)
    val target = 9
    println(twoSum(arr, target).mkString(", "))
    println(twoSumOptimized(arr, target).mkString(", "))
  }
}
