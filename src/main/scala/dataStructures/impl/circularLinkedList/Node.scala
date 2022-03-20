package dataStructures.impl.circularLinkedList

class Node(_data : Int, _next : Node){
  var data : Int = _data
  var next : Node = _next

  // Auxiliary Constructor
  def this(_data : Int) {
    this(_data, null)
    this.data = _data
  }

}

