package interviewPrep.searching

object LinearSearch {
  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,9,6,8,4,3,5,7,0)
    val item = 10
    println(linearSearch(arr, item))
  }

  def linearSearch(arr : Array[Int], item: Int) : Int = {
    for (i <- 0 until arr.length){
      if(arr(i) == item)
        return i
    }
    -1
  }
}
