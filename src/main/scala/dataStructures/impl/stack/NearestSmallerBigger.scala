package dataStructures.impl.stack

object NearestSmallerBigger {

  // Prints array having nearest smaller element from left
  // for each element of the original arr
  def getNearestSmallerLeft(arr: Array[Int]): Unit = {
    val res : Array[Int] = Array.fill(arr.length){-1}
    val stack = new StackLinkedList()
    var index = 0
    for(elem <- arr){
      if(stack.isEmpty){
        stack.push(elem)
        res(index) = -1
      }
      else{
        // Remove all items from stack greater than elem
        while(!stack.isEmpty && stack.peek > elem)
          stack.pop
        // If stack is empty, res(index) = -1 and add elem to stack
        // else res(index) = stack.peek and push elem to top of the stack
        if(stack.isEmpty){
          res(index) = -1
          stack.push(elem)
        }
        else{
          res(index) = stack.peek
          stack.push(elem)
        }
      }
      index +=1 
    }
    println(arr.mkString(", "))
    println(res.mkString(", ") + "\n")
  }

  // Prints array having nearest smaller element from right
  // for each element of the original arr
  def getNearestSmallerRight(arr: Array[Int]): Unit = {
    val res : Array[Int] = Array.fill(arr.length){-1}
    val stack = new StackLinkedList()
    val n = arr.length
    var index = n - 1
    for(i <- (n-1).to(0).by(-1)){
      val elem = arr(i)
      if(stack.isEmpty){
        stack.push(elem)
        res(i) = -1
      }
      else{
        // Remove all elem from stack greater than current elem
        while(!stack.isEmpty && stack.peek > elem)
          stack.pop

        // Check if stack has become empty
        if(stack.isEmpty){
          res(i) = -1
          stack.push(elem)
        }
        else{
          res(i) = stack.peek
          stack.push(elem)
        }
      }
    }
    println(arr.mkString(", "))
    println(res.mkString(", ") + "\n")
  }

  def main(args: Array[String]): Unit = {
    val arr = Array[Int](4, 10, 5, 8, 20, 15, 2, 23, 12)
    getNearestSmallerLeft(arr)
    getNearestSmallerRight(arr)
  }
}
