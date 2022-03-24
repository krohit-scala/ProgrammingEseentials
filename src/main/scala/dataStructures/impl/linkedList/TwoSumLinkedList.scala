package dataStructures.impl.linkedList

object TwoSumLinkedList {
  // Given, 2 numbers represented as LinkedList
  // WAP to add the numbers represented by two LinkedLists
  // and return the sum as LinkedList
  var carry = 0
  var globalLinkedList : LinkedList = new LinkedList()

  def getRandInt : Int = {
    val start = 0
    val end = 9
    val rnd = new scala.util.Random
    val temp = start + rnd.nextInt( (end - start) + 1 )
    temp
  }

  def main(args: Array[String]): Unit = {
    // Create and populate LinkedLists
    val l1 = new LinkedList()
    val l2 = new LinkedList()
    val n1 = 10
    val n2 = 5
    for(i <- 0.until(n1))
      l1.append(getRandInt)
    for(i <- 0.until(n2))
      l2.append(getRandInt)

  }
}
