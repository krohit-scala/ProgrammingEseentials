package platforms.hackerrank.dataStructures.array.easy

// https://www.hackerrank.com/challenges/array-left-rotation/
object RotateLeft {
  // New Array approach
  // Time: O[n]; Space: O[n]
  def rotateLeft(d: Int, arr: Array[Int]): Array[Int] = {
    val n = arr.length
    val arr1 : Array[Int] = Array.fill(n){-1}
    for(i <- 0 to (n -1)){
      // Target index
      val newI = (i - d + n) % n
      arr1(newI) = arr(i)
    }
    arr1
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5)
    println(s"Before: [${arr.mkString(", ")}]")
    println(s"After:  [${rotateLeft(2, arr).mkString(", ")}]")
  }
}