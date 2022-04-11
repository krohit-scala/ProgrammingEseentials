package dataStructures.impl.tree

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

// Make a Helper class with static method to get Binary Tree
class SerializeBinaryTree{
  // Empty
}

object SerializeBinaryTree {
  // Pre-prepared Binary Tree
  /*
                5
              /   \
             6      7
            / \    / \
          8    3 10   13
         / \    \    /  \
        1   4    11 2    14
           /
         15
   */
  def readTree : Node = {
    val ois = new ObjectInputStream(new FileInputStream("Resources/bin_tree1"))
    val tree : Node = ois.readObject.asInstanceOf[Node]
    ois.close
    tree
  }

  def printTree : Unit = {
    val msg : String =
      """
        |                5
        |              /   \
        |             6      7
        |            / \    / \
        |          8    3 10   13
        |         / \    \    /  \
        |        1   4    11 2    14
        |           /
        |         15
        |""".stripMargin
    println(msg)
  }
  def testSerDe(args: Array[String]): Unit = {
    val tree = BinaryTree.createTree
    val oos = new ObjectOutputStream(new FileOutputStream("Resources/bin_tree1"))
    oos.writeObject(tree)
    oos.close

    // Test if the Serialization-Deserialization works
    println(s"\nBEFORE: \n")
    BinaryTree.levelOrderTraversalNewLine(tree)

    val tree1 : Node = readTree
    println(s"\nAFTER: \n")
    BinaryTree.levelOrderTraversalNewLine(tree1)
  }
}
