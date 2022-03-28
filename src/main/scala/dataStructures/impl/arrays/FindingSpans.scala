package dataStructures.impl.arrays

// Given an array A, the span S[i] of Array A[i] is the maximum number of consecutive
// elements A[j] immediately preceding A[i] such that A[j] < A[i]
object FindingSpans {

  def main(args: Array[String]): Unit = {
    val arr = Array[Int](6, 3, 4, 5, 2)
    val s: Array[Int] = Array.fill(arr.length) {
      -1
    }
    for (i <- 0 until arr.length) {
      var j = 1
      while (j <= i && arr(j) < arr(i))
        j += 1

      s(i) = j
    }

    println(s"Input array: ${arr.mkString(", ")}")
    println(s"Span array: ${s.mkString(", ")}")
  }
}
