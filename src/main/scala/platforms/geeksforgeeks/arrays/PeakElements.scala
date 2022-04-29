package platforms.geeksforgeeks.arrays

// https://practice.geeksforgeeks.org/problems/peak-element/1#
// https://leetcode.com/problems/find-peak-element/
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

  // Return the index of largest number
  def findPeakElement1(nums: Array[Int]): Int = {
    var maxVal = Int.MinValue
    var index = 0
    for(i <- 0 until nums.length){
      if(nums(i) > maxVal){
        maxVal = nums(i)
        index = i
      }
    }
    index
  }
    def main(args: Array[String]): Unit = {
    val arr = Array(1,2,1,3,5,6,4)
    val index = findPeakElement(arr)
    println(s"Peak Element at: ${index}")
  }
}
