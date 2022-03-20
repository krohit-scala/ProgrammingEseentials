package dataStructures.impl.doublyLinkedList

class Node(_data: Int, _next: Node, _prev: Node) {
  var data : Int = _data
  var next : Node = _next
  var prev : Node = _prev

  // Auxiliary constructor
  def this(_data: Int) {
    this(_data, null, null)
    this.data = _data
  }

}
