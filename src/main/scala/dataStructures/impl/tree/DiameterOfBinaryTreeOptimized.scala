package dataStructures.impl.tree

object DiameterOfBinaryTreeOptimized {

  // Method to compute the diameter of Binary Tree in O(N)
  def diameter(root: Node) : Int = {
    var diameter : Int = 0

    // Helper method to calculate the diameter
    def getHeight(root: Node) : Int = {
      if(root == null)
        return 0
      val leftHeight = getHeight(root.left)
      val rightHeight = getHeight(root.right)
      val currHeight = 1 + leftHeight + rightHeight
      diameter = math.max(
        diameter,
        currHeight
      )
      currHeight
    }

    diameter
  }

  def main(args: Array[String]): Unit = {
    val tree = BinaryTree.createTree
    println(s"Diameter of Binary Tree: ${diameter(tree)}\n")


  }
}
