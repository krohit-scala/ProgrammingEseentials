package dataStructures.impl.tree

object BurnBinaryTree {
  def main(args: Array[String]): Unit = {
    val tree = SerializeBinaryTree.readTree
    SerializeBinaryTree.printTree

    BinaryTree.levelOrderTraversalNewLine(tree)
  }
}
