package interviewPrep.sorting

object SelectionSort {

  def selectionSort(arr: Array[Int]): Array[Int] = {
    for(i <- 0.until(arr.length)){
      // Sub-array from index 0 to i-1 is sorted, and rest is unsorted.
      // Fix the position where you get the min value from the unsorted sub-array
      var minIndex = i
      for(j <- i+1 until(arr.length)){
        if(arr(j) < arr(minIndex))
          minIndex = j
      }
      // Swap the smallest element with the one at index i
      // So that index i contains the min from the sub-array i to n-1, where n is size of the array
      val temp = arr(minIndex)
      arr(minIndex) = arr(i)
      arr(i) = temp
    }
    arr
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(64,25,12,22,11)
    val arr1 : Array[Int] = selectionSort(arr)
    println(s"Original Array: [${arr.mkString(", ")}]")
    println(s"Sorted   Array: [${arr1.mkString(", ")}]")
  }
}
