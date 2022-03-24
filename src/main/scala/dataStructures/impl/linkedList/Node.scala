package dataStructures.impl.linkedList

class Node(_data: Int){
  var data : Int = _data
  var next : Node = null

  // Auxiliary Constructor
  def this(_data : Int, _next : Node) {
    this(_data)
    // this.data = _data
    this.next = _next
  }

  // Function to traverse through the list
  // Starts from the pointer location on the list
  def printList : Unit = {
    var pointer = this
    val arr1 = scala.collection.mutable.ArrayBuffer[Int]()
    while(pointer != null){
      arr1.append(pointer.data)
      pointer = pointer.next
    }
    println(s"LinkedList: ${arr1.mkString(" ---> ")}")
  }
}
