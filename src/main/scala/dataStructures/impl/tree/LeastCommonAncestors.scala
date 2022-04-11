package dataStructures.impl.tree

object LeastCommonAncestors {

  // Node to look for Least Common Ancestors recursively
  def getLeastCommonAncestor(root: Node, n1: Int, n2: Int): Int = {
    // Helper method to find nodes recursively
    // Assumption: either both nodes exists or none do
    def getLCA(root: Node, n1: Int, n2: Int) : Node = {
      // Case-0: Base check
      if(root == null)
        return null

      // Case-1: Either N1 or N2 are the root
      if(root.data == n1 || root.data == n2)
        return root

      val left = getLCA(root.left, n1, n2)
      val right = getLCA(root.right, n1, n2)

      // Case-2: Either Left or Right subtree is null
      if(left == null)
        return right
      if(right == null)
        return left

      // Case-3: Current Node is common ancestor for both data
      root
    }

    // Find Least Common Ancestor for N1 and N2
    val lcaNode = getLCA(root, n1, n2)
    if(lcaNode != null)
      lcaNode.data
    else
      -1
  }

  def main(args: Array[String]): Unit = {
    // val tree = BinaryTree.createTree
    val tree = SerializeBinaryTree.readTree
    SerializeBinaryTree.printTree
    val n1 = 1
    val n2 = 11
    println(s"LCA for nodes [${n1}, ${n2}] is: ${getLeastCommonAncestor(tree, n1, n2)}")
  }
}
