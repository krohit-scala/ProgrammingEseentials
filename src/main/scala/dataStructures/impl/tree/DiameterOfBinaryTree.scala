package dataStructures.impl.tree

import scala.collection.mutable.HashMap

object DiameterOfBinaryTree {
  // To keep the height of each Node
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

    // Choose the method to compute the height
    // val currHeight = getHeight(root.left) + getHeight(root.right) + 1                  // Time complexity : O[n^2]
    val currHeight = getHeightOptimized(root.left) + getHeightOptimized(root.right) + 1   // Time complexity : O[nlogn]

    math.max(
      currHeight,
      math.max(leftDia, rightDia)
    )
  }

  // Get the height of a given node
  // Example:
  //        3
  //      /   \
  //    2       4
  //          /   \
  //        1       5
  // Node: 4, Height: 2
  // Node: 2, Height: 1
  // Node: 1, Height: 1
  // Node: 5, Height: 1
  // Height of Node [2] is relatively 1, but absolutely 2
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
    println(s"Diameter of Binary Tree: ${diameter(tree)}\n")

    val nodes = hashMap.keySet
    for(node <- nodes)
      println(s"Node: ${node.data}, Height: ${hashMap.get(node).get}")

  }

}
