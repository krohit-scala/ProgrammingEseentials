package dataStructures.impl.tree

import java.util.Scanner
import scala.collection.immutable.HashMap
import scala.collection.mutable

class BinaryTree {
  var root : Node = null

  // Inserting elements into a Binary Tree
  def createTree : Node = {
    print(s"Enter data for root: ")
    val data = scala.io.StdIn.readInt()
    this.root = new Node(data)
    println(s"Data at the root: ${this.root.data}")

    this.root
  }
}

object BinaryTree {
  // Inserting elements into a Binary Tree
  def createTree : Node = {
    var root : Node = null
    print(s"Enter data for root: ")
    val data = scala.io.StdIn.readInt()

    // Edge case; assuming -1 is used to input no node
    if(data == -1)
      return null

    // Set data at root
    root = new Node(data)

    // Create left subtree
    println(s"\nEnter left for data: ${data}")
    root.left = createTree
    // Create left subtree
    println(s"\nEnter right for data: ${data}")
    root.right = createTree

    // Return the Binary Tree object
    root
  }

  // Binary Tree Traversal:
  // In-Order => L-N-R
  def inOrderTraversal(root: Node) : Unit = {
    if(root == null)
      return

    inOrderTraversal(root.left)
    print(s" ${root.data} ")
    inOrderTraversal(root.right)
  }

  // Binary Tree Traversal:
  // Pre-Order => N-L-R
  def preOrderTraversal(root: Node) : Unit = {
    if(root == null)
      return

    print(s" ${root.data} ")
    preOrderTraversal(root.left)
    preOrderTraversal(root.right)
  }

  // Binary Tree Traversal:
  // Post-Order => L-R-N
  def postOrderTraversal(root: Node) : Unit = {
    if(root == null)
      return

    postOrderTraversal(root.left)
    postOrderTraversal(root.right)
    print(s" ${root.data} ")
  }

  // Height of a Binary Tree
  def heightOfTree(root: Node) : Int = {
    // Base case
    if(root == null)
      return 0

    math.max(heightOfTree(root.left), heightOfTree(root.right)) + 1
  }

  // Size of a Binary Tree
  def sizeOfTree(root: Node) : Int = {
    // Base case
    if(root == null)
      return 0

    sizeOfTree(root.left) + sizeOfTree(root.right) + 1
  }

  // Max of a Binary Tree
  def maxInTree(root: Node) : Int = {
    if(root == null)
      return Int.MinValue
    math.max(
      math.max(
        root.data,
        maxInTree(root.left)
      ),
      maxInTree(root.right)
    )
  }

  // Min of a Binary Tree
  def minInTree(root: Node) : Int = {
    if(root == null)
      return Int.MaxValue
    math.min(
      math.min(
        root.data,
        minInTree(root.left)
      ),
      minInTree(root.right)
    )
  }

  // Left side view of a Binary Tree
  def leftSideView(root: Node) : Unit = {
    var levelViewMap : HashMap[Int, Int] = HashMap[Int, Int]()

    var level = 0
    getLeftSideView(root, level)

    def getLeftSideView(root: Node, level: Int) : Unit = {
      if(root == null)
        return

      if(levelViewMap.getOrElse(level, null) == null)
        levelViewMap += level-> root.data
      // Traverse left
      getLeftSideView(root.left, level+1)
      getLeftSideView(root.right, level+1)
    }

    val keys = levelViewMap.keySet.toArray.sorted
    println()
    for(level <- keys)
      println(s">> Level: ${level}, Element: ${levelViewMap.getOrElse(level, -1)}")
  }

  // Right side view of a Binary Tree
  def rightSideView(root: Node) : Unit = {
    var levelViewMap : HashMap[Int, Int] = HashMap[Int, Int]()

    var level = 0
    getRightSideView(root, level)

    def getRightSideView(root: Node, level: Int) : Unit = {
      if(root == null)
        return

      if(levelViewMap.getOrElse(level, null) == null)
        levelViewMap += level-> root.data
      // Traverse right
      getRightSideView(root.right, level+1)
      getRightSideView(root.left, level+1)
    }

    val keys = levelViewMap.keySet.toArray.sorted
    println()
    for(level <- keys)
      println(s">> Level: ${level}, Element: ${levelViewMap.getOrElse(level, -1)}")
  }
}
