package dataStructures.impl.tree

object FlatteningBinaryTree {
  var head : Node = null
  var prev : Node = null

  // Flattening a Binary Tree in In-Order Traversal Order
  // Left should point to previous, right to next
  def flatten(root: Node) : Unit = {
    // Base check
    if(root == null)
      return

    // Process Left
    flatten(root.left)

    // Process Node
    // Check if current node is the 1st Node
    if(prev == null)
      head = root
    else{
      root.left = prev
      prev.right = root
    }
    prev = root

    // Process Right
    flatten(root.right)
  }

  def main(args: Array[String]): Unit = {
    val tree = BinaryTree.createTree
    flatten(tree)

    var pointer : Node = this.head
    var msg = "head --> "
    while(pointer != null){
      msg += s"${pointer.data} --> "
      pointer = pointer.right
    }
    msg += " null"
    println(s"\n${msg}")
  }
}
