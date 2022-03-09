package interviewPrep.searching

object BinarySearch {

  // Assuming ascending sort
  def binarySearch(arr: Array[Int], item: Int) : Int = {
    val n = arr.length
    // Base cases
    if(arr(0) > item)
      return -1
    if(arr(n-1) < item)
      return -1
    if(n == 0)
      return -1

    var left = 0
    var right = n - 1
    var mid = 0

    while(right >= left){
      mid = if((left+right)%2 == 0)
        (left + right)/2
      else
        (left + right)/2 + 1
      if(arr(mid) == item)
        return mid
      if(arr(mid) > item)
        right = mid - 1
      if(arr(mid) < item)
        left = mid + 1
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2, 3, 4, 10, 25, 36, 40, 43)
    for (item <- arr) {
      println(s"item: ${item}, index: ${binarySearch(arr, item)}")
    }
    println(s"item: ${500}, index: ${binarySearch(arr, 500)}")
    println(s"item: ${1}, index: ${binarySearch(arr, 1)}")
  }
}
