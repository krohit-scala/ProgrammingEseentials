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
    BinaryTree.levelOrderTraversal(root)
    println("\n")
    BinaryTree.levelOrderTraversalNewLine(root)
    println("\n")

    println(s"Height of Binary Tree: ${BinaryTree.heightOfTree(root)}")
    println(s"Size of Binary Tree: ${BinaryTree.sizeOfTree(root)}")
    println(s"Max of Binary Tree: ${BinaryTree.maxInTree(root)}")
    println(s"Min of Binary Tree: ${BinaryTree.minInTree(root)}")
    BinaryTree.leftSideView(root)
    BinaryTree.rightSideView(root)
  }
}
