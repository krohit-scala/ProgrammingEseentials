package dataStructures.impl.tree

import scala.collection.mutable.HashMap

object DiameterOfBinaryTree {
  var hashMap : HashMap[Node, Int] = HashMap[Node, Int]()

  // Get the height of a given node
  def getHeight(root: Node) : Int = {
    if(root == null)
      return 0
    val leftHeight = getHeight(root.left)
    val rightHeight = getHeight(root.right)
    1 + math.max(leftHeight, rightHeight)
  }

  // Get Diameter of a Binary Tree
  def diameter(root: Node) : Int = {
    if(root == null)
      return 0

    val leftDia = diameter(root.left)
    val rightDia = diameter(root.right)
    val currHeight = getHeight(root.left) + getHeight(root.right) + 1

    math.max(
      currHeight,
      math.max(leftDia, rightDia)
    )
  }

  // Get the height of a given node
  def getHeightOptimized(root: Node) : Int = {
    if(root == null)
      return 0
    if(hashMap.contains(root))
      hashMap.get(root).get
    else{
      val leftHeight = getHeightOptimized(root.left)
      val rightHeight = getHeightOptimized(root.right)
      val currHeight = math.max(leftHeight, rightHeight) + 1
      hashMap += root -> currHeight
      currHeight
    }
  }

  def main(args: Array[String]): Unit = {
    val tree = BinaryTree.createTree

  }

}
