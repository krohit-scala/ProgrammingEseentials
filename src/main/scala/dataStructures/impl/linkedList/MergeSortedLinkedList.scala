package dataStructures.impl.linkedList

import dataStructures.impl.Node

object MergeSortedLinkedList {
  def main(args: Array[String]): Unit = {
    // Create 2 arrays
    val arr1 = Array[Int](10, 30, 50, 70)
    val arr2 = Array[Int](20, 40, 60)

    // Create 2 LinkedLists
    val ll1 = new LinkedList()
    val ll2 = new LinkedList()

    // Populate the LinkedLists
    ll1.appendArray(arr1)
    ll2.appendArray(arr2)

    // Get the heads
    var head1 = ll1.head
    var head2 = ll2.head

    // Print to confirm if heads are right
    // println(s"head1.next.data: ${head1.next.data}, head2.data: ${head2.next.data}")
    var mll : LinkedList = mergeSortedLinkedLists(head1, head2)
    mll.printList()
  }

  def mergeSortedLinkedLists(h1: Node, h2: Node) : LinkedList = {
    // Head and pointers for the merged LinkedList
    var mll : LinkedList = new LinkedList()
    var head = mll.head

    // Base check
    if(h1 == null && h2 != null){
      mll.head = h2
      return mll
    }
    if(h2 == null && h1 != null){
      mll.head = h1
      return mll
    }

    // Inputs are not null, lets merge before we send
    // Pointer to mll
    var p : Node = head

    // Managing pointers for both the LinkedLists
    var p1 : Node = h1
    var p2 : Node = h2

    // Loop through and do the sort-merge
    while(p1 != null && p2!= null){
      // Check if both LinkedLists have reached their ends
      if(p1 != null && p2 != null){
        // If not, check which is bigger
        if(p1.data < p2.data){
          // val newNode : Node = new Node(p1.data)
          mll.append(p1.data)
          p1 = p1.next
        }
        else{
          // val newNode : Node = new Node(p2.data)
          mll.append(p2.data)
          p2 = p2.next
        }
      }

      // If one of the LinkedList has reached the end
      // If LinkedList1 is empty => Push the remaining elements of LinkedList2
      // If LinkedList2 is empty => Push the remaining elements of LinkedList1
      if(p1 == null){
        while(p2 != null){
          // val newNode : Node = new Node(p2.data)
          mll.append(p2.data)
          p2 = p2.next
        }
      }
      else if(p2 == null){
        while(p1 != null){
          // val newNode : Node = new Node(p1.data)
          mll.append(p1.data)
          p1 = p1.next
        }
      }
    }
    mll
  }
}
