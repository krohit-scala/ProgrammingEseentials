package dataStructures.impl.tree

object TestBinaryTree {
  def main(args: Array[String]): Unit = {
    val tree = BinaryTree.createTree
    BinaryTree.inOrderTraversal(tree)
    println("\n\n")
    BinaryTree.preOrderTraversal(tree)
    println("\n\n")
    BinaryTree.postOrderTraversal(tree)
  }
}
