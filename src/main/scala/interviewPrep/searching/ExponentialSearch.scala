package interviewPrep.searching

object ExponentialSearch {
  def main(args: Array[String]): Unit = {
    val arr = Array(2, 3, 4, 10, 25, 36, 40, 67)
    for (item <- arr) {
      println(s"item: ${item}, index: ${exponentialSearch(arr, item)}")
    }
    println(s"item: ${110}, index: ${exponentialSearch(arr, 110)}")
    println(s"item: ${1}, index: ${exponentialSearch(arr, 1)}")
  }

  // Helper Method
  def binarySearch(arr: Array[Int], l: Int, r: Int, item: Int) : Int = {
    var left = l
    var right = r

    // Base check
    if(right-left == 0)
      return -1
    if(arr(left) == item)
      return left
    if(arr(arr.length-1) == item)
      return right

    // Binary Search
    while(right >= left){
      var mid = if((left + right)% 2 == 0)
          (left+right)/2
        else
          (left+right)/2 + 1

      if(arr(mid) == item)
        return mid
      else if(item < arr(mid)) {
        right = mid - 1
      }
      else{
        left = mid + 1
      }
      mid = if((left + right% 2) == 0)
        (left+right)/2
      else
        (left+right)/2 + 1
    }

    // Not found
    -1
  }

  def exponentialSearch(arr : Array[Int], item: Int) : Int = {
    var i = 1
    val n = arr.length

    // Base check
    if(item > arr(n-1))
      return -1
    if(arr(0) == item)
      return 0

    // Exponential increase of i
    while(i < n && arr(i) <= item)
      i = i * 2

    binarySearch(arr, i/2, math.min(i, n-1), item)
  }
}
