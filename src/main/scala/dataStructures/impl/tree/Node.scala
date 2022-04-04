package dataStructures.impl.tree

class Node(_data : Int) {
  var data : Int = _data
  var left : Node = null
  var right : Node = null

  // Auxiliary Constructor
  def this(_data : Int, _left : Node, _right: Node) {
    this(_data)
    this.left = _left
    this.right = _right
  }
}
