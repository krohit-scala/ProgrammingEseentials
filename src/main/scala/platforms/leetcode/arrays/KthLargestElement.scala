package platforms.leetcode.arrays

// https://leetcode.com/problems/kth-largest-element-in-an-array/
object KthLargestElement {
  def findKthLargest(arr: Array[Int], k: Int): Int = {
    val n = arr.length
    val arr1 = arr.sorted
    arr1(n-k)
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(3,2,3,1,2,4,5,5,6)
    println(s"Kth highest element in the array is: ${findKthLargest(arr, 2)}")
  }
}
