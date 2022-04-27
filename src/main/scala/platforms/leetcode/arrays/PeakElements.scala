package platforms.leetcode.arrays

object PeakElements {
  // Method to find peak numbers
  // A peak element is an element that is strictly greater than its neighbors.
  def findPeakElement(nums: Array[Int]): Int = {
    var leftMax = Int.MinValue
    var rightMax = Int.MinValue

    val leftArr = Array.fill(nums.length)(Int.MinValue)
    val rightArr = Array.fill(nums.length)(Int.MinValue)

    for(i <- 0.until(nums.length)){
      if(nums(i) > leftMax)
        leftMax = nums(i)
      leftArr(i) = leftMax
    }

    for(i <- (nums.length-1).to(0).by(-1)){
      if(rightMax < nums(i))
        rightMax = nums(i)
      rightArr(i) = rightMax
    }

    // println(s"LeftArr : ${leftArr.mkString(", ")}")
    // println(s"MainArr : ${nums.mkString(", ")}")
    // println(s"RightArr: ${rightArr.mkString(", ")}")

    for(i <- 0.until(nums.length)){
      if(nums(i) == leftArr(i) && nums(i) == rightArr(i))
        return i
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,1,3,5,6,4)
    val index = findPeakElement(arr)
    println(s"Peak Element at: ${index}")
  }
}
