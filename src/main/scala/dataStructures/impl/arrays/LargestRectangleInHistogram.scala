package dataStructures.impl.arrays

import dataStructures.impl.stack.StackLinkedList

object LargestRectangleInHistogram {

  def getPrevSmaller(arr: Array[Int]): Array[Int] = {
    val n = arr.length
    val stack = new StackLinkedList()
    val res = Array.fill(n) {
      -1
    }

    for (i <- 0.until(n)) {
      while (stack.isNotEmpty && arr(stack.peek) >= arr(i))
        stack.pop

      if (stack.isEmpty)
        res(i) = -1
      else
        res(i) = stack.peek

      stack.push(i)
    }
    res
  }

  def getNextSmaller(arr: Array[Int]): Array[Int] = {
    val n = arr.length
    val stack = new StackLinkedList()
    val res = Array.fill(n) {
      -1
    }

    for (i <- (n - 1).to(0).by(-1)) {
      while (stack.isNotEmpty && arr(stack.peek) >= arr(i))
        stack.pop

      if (stack.isEmpty)
        res(i) = n
      else
        res(i) = stack.peek

      stack.push(i)
    }
    res
  }

  def main(args: Array[String]): Unit = {
    val arr = Array[Int](4, 2, 1, 5, 6, 3, 2, 4, 2)
    val prevSmaller: Array[Int] = getPrevSmaller(arr)
    val nextSmaller: Array[Int] = getNextSmaller(arr)
    println(arr.mkString(", "))
    println(prevSmaller.mkString(", "))
    println(nextSmaller.mkString(", "))

    var maxArea = Int.MinValue
    for (i <- 0.until(arr.length)) {
      val tempArea = (nextSmaller(i) - prevSmaller(i) - 1) * arr(i)
      maxArea = scala.math.max(maxArea, tempArea)
    }

    println(s"Max possible area: ${maxArea}")
  }
}
