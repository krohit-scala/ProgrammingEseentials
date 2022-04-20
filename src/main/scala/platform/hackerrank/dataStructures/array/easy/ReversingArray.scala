package platform.hackerrank.dataStructures.array.easy

// https://www.hackerrank.com/challenges/arrays-ds/
object ReversingArray {
  def reverseArray(arr: Array[Int]): Array[Int] = {
    val n = arr.length
    for (i <- 0 to (n - 1) / 2) {
      val temp = arr(i)
      arr(i) = arr(n - 1 - i)
      arr(n - 1 - i) = temp
    }
    arr
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5)
    println(s"Before: [${arr.mkString(", ")}]")
    println(s"After:  [${reverseArray(arr).mkString(", ")}]")
  }
}
