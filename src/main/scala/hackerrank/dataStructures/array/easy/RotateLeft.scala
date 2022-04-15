package hackerrank.dataStructures.array.easy

// https://www.hackerrank.com/challenges/array-left-rotation/
object RotateLeft {
  // WIP
  def rotateLeft(d: Int, arr: Array[Int]): Array[Int] = {
    val n = arr.length
    for(i <- 0 to n -1){
      // Target index
      val newI = if(i - d >= 0) (i - d) else (i - d + n)%n

      // Swap
      val temp = arr(newI)
      arr(newI) = arr(i)
      arr(i) = temp
      // println(s"Temp shift :  [${arr.mkString(", ")}]")
    }
    arr
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5)
    println(s"Before: [${arr.mkString(", ")}]")
    println(s"After:  [${rotateLeft(2, arr).mkString(", ")}]")
//    println(s"After:  [${rotateLeft(1, arr).mkString(", ")}]")
//    println(s"After:  [${rotateLeft(1, arr).mkString(", ")}]")
    //println(s"After:  [${rotateLeft(3, arr).mkString(", ")}]")
  }
}
