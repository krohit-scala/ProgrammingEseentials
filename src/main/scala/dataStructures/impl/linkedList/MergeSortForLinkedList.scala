package dataStructures.impl.linkedList

object MergeSortForLinkedList {

  // Takes sorted LinkedList as arguments and returns merged LinkedList
  def mergeSortInternals(left: Node, right: Node) : Node = {
    // Base cases
    if(left == null)
      return right
    if(right == null)
      return left

    var head : Node = null
    if (left.data <= right.data) {
      head = left
      head.next = mergeSortInternals(left.next, right)
    }
    else {
      head = right
      head.next = mergeSortInternals(left, right.next)
    }

    // Return the merged output
    head
  }

  // Takes a LinkedList as argument and break it down from mid point
  // More optimized - O(n)
  def divideInHalves(head: Node) : (Node, Node) = {
    // Base case
    if(head == null || head.next == null)
      return (head, null)

    // Create fast and slow pointers
    // Fast pointer moves at twice fast as Slow pointer
    var fast = head
    var slow = head

    while(fast.next != null){
      fast = fast.next
      if(fast.next != null){
        fast = fast.next
        slow = slow.next
      }
    }
    val right = slow.next
    slow.next = null

    // Return the two halves
    (head, right)
  }

  // Takes a LinkedList as argument and break it down from mid point
  def divideInHalvesBruteForce(head: Node) : (Node, Node) = {
    // Find mid point and divide
    val h1 : Node = head
    var h2 : Node = null

    // Length of the LinkedList
    // Get the position of the middle element and break at the mid point
    var len = 0
    var pointer = head
    while(pointer != null){
      len += 1
      pointer = pointer.next
    }
    val mid = if(len%2 == 0) len/2 else (len+1)/2

    var counter = 1
    pointer = head
    while(pointer != null && counter < mid){
      pointer = pointer.next
      counter += 1
    }
    h2 = pointer.next
    pointer.next = null

    // Return the halves
    (h1, h2)
  }

  // Method to apply merge sort
  def mergeSort(head: Node) : Node = {
    // Check for base cases
    if(head == null || head.next == null)
      return head

    // Divide in halves
    val newHalves = divideInHalves(head)
    var left : Node = newHalves._1
    var right : Node = newHalves._2

    // Sort left and right
    left = mergeSort(left)
    right = mergeSort(right)

    // Merge the sorted left and right
    mergeSortInternals(left, right)
  }

  def main(args: Array[String]) : Unit = {
    // Create the head of the LinkedList
    var head : Node = null

    // Populate the LinkedList
    val arr = Array[Int](10, 80, 20, 30, 50, 70, 40, 90, 100, 60)
    for(elem <- arr)
      head = new Node(elem, head)

    // Original State - Before sorting
    println(s"Original State - Before sorting")
    head.printList

    // New state - After sorting
    println((s"New state - After sorting"))
    val newHead : Node = mergeSort(head)
    newHead.printList
  }
}