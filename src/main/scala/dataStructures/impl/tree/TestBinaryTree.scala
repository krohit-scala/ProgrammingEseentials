package dataStructures.impl.tree

object TestBinaryTree {
  def main(args: Array[String]): Unit = {
    val root = BinaryTree.createTree
    BinaryTree.inOrderTraversal(root)
    println("\n")
    BinaryTree.preOrderTraversal(root)
    println("\n")
    BinaryTree.postOrderTraversal(root)
    println("\n")

    println(s"Height of Binary root: ${BinaryTree.heightOfTree(root)}")
    println(s"Size of Binary root: ${BinaryTree.sizeOfTree(root)}")
    println(s"Max of Binary root: ${BinaryTree.maxInTree(root)}")
    println(s"Min of Binary root: ${BinaryTree.minInTree(root)}")
  }
}
