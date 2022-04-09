package dataStructures.impl.tree

import dataStructures.impl.tree.BinaryTree.Pair

import scala.collection.immutable.TreeMap
import scala.collection.mutable

object TopViewOfBinaryTree {

  // Get the top View of the Binary Tree
  def topView(root: Node) : Unit = {
    // For Level-Order Traversal over the Binary Tree
    var q : mutable.Queue[Pair]= mutable.Queue[Pair]()

    // For keeping horizontal distance and corresponding data
    var hdMap = TreeMap[Int, Int]()

    // Inserting the root element in the Queue for Level-Order Traversal
    q.enqueue(new Pair(0, root))

    // Level-Order Traversal to populate the TreeMap for TopView
    while(!q.isEmpty){
      val temp = q.dequeue
      if(hdMap.getOrElse(temp.hd, null) == null)
        hdMap += temp.hd -> temp.node.data

      // Add left node
      if(temp.node.left != null)
        q.enqueue(new Pair(temp.hd-1, temp.node.left))
      // Add right node
      if(temp.node.right != null)
        q.enqueue(new Pair(temp.hd+1, temp.node.right))
    }

    // Read the data from the TreeMap
    val keys = hdMap.keySet
    // Print the view
    println(s"\nTop View:")
    for(key <- keys)
      println(s"HD: ${key}, Data: ${hdMap.get(key).get}")
  }

  // Get the Bottom View of the Binary Tree
  def bottomView(root: Node) : Unit = {
    // For Level-Order Traversal over the Binary Tree
    var q : mutable.Queue[Pair]= mutable.Queue[Pair]()

    // For keeping horizontal distance and corresponding data
    var hdMap = TreeMap[Int, Int]()

    // Inserting the root element in the Queue for Level-Order Traversal
    q.enqueue(new Pair(0, root))

    // Level-Order Traversal to populate the TreeMap for TopView
    while(!q.isEmpty){
      val temp = q.dequeue
      hdMap += temp.hd -> temp.node.data

      // Add left node
      if(temp.node.left != null)
        q.enqueue(new Pair(temp.hd-1, temp.node.left))
      // Add right node
      if(temp.node.right != null)
        q.enqueue(new Pair(temp.hd+1, temp.node.right))
    }

    // Read the data from the TreeMap
    val keys = hdMap.keySet
    // Print the view
    println(s"\nBottom View:")
    for(key <- keys)
      println(s"HD: ${key}, Data: ${hdMap.get(key).get}")
  }

  def main(args: Array[String]): Unit = {
    val tree = BinaryTree.createTree
    topView(tree)
    bottomView(tree)
  }
}
