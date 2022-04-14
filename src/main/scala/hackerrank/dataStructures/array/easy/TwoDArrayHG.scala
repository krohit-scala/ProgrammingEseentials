package hackerrank.dataStructures.array.easy

// https://www.hackerrank.com/challenges/2d-array/
object TwoDArrayHG {
  def hourglassSum(arr: Array[Array[Int]]): Int = {
    var n = 0
    var m = 0
    if(arr.isEmpty)
      return -1
    if(arr(0).isEmpty)
      return -1

    n = arr.length
    m = arr(0).length

    // Variable to store final result
    var ans = Int.MinValue

    // Iterate to form hourglass and sum
    for(row <- 1 to n-2){
      for(col <- 1 to m-2){
        val tempSum = (  arr(row-1)(col-1) + arr(row-1)(col) + arr(row-1)(col+1) // Top row
                      + arr(row)(col)                                           // Middle row
                      + arr(row+1)(col-1) + arr(row+1)(col) + arr(row+1)(col+1) // Bottom row
        )
        if(tempSum > ans)
          ans = tempSum
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    val n = 7
    val m = 6
    val arr = Array.ofDim[Int](n, m)
    for(row <- 0 until n){
      for(col <- 0 until m){
        arr(row)(col) = row + col
      }
    }

    for(row <- 0 until n)
      println(s"Row ${row}: [${arr(row).mkString(", ")}]")

    // Max sum of hourglass
    println(hourglassSum(arr))
  }

}
