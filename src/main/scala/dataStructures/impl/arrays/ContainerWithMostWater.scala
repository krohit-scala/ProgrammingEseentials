package dataStructures.impl.arrays

object ContainerWithMostWater {

  def containerWithMostWater(arr: Array[Int]): Int = {
    var left = 0
    var right = arr.length - 1
    var maxArea = 0
    while(left < right){
      val currArea = math.min(arr(left), arr(right)) * (right - left)
      if(maxArea < currArea)
        maxArea = currArea
      if(arr(left) < arr(right))
        left += 1
      else
        right -= 1
    }
    maxArea
  }

  def main(args: Array[String]): Unit = {
    val arr = Array[Int](1,8,6,2,5,4,8,3,7)
    println(s"${containerWithMostWater(arr)}")
  }
}
