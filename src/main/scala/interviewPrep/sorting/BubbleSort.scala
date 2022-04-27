package interviewPrep.sorting

object BubbleSort {

  def bubbleSort(arr: Array[Int]): Array[Int] = {
    var last = arr.length - 1
    for(i <- 0 until(arr.length)){
      for(j <- 0 until(last)) {
        if (arr(j) > arr(j + 1)) {
          val temp = arr(j)
          arr(j) = arr(j + 1)
          arr(j+1) = temp
        }
      }
      last -= 1
    }
    arr
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(64,25,12,22,11)
    val arr1 : Array[Int] = bubbleSort(arr)
    println(s"Original Array: [${arr.mkString(", ")}]")
    println(s"Sorted   Array: [${arr1.mkString(", ")}]")
  }
}
