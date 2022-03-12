package dataStructures.impl

class Node(_data: Int){
  var data = _data
  var next : Node = null

  // Function to traverse through the list
  // Starts from the pointer location on the list
  def printList(head: Node) : Unit = {
    var ptr = head
    var counter = 1
    var msg = ""
    while(ptr != null){
      if(counter == 1)
        msg = "head"
      else
        msg = msg + " ---> " + ptr.data
      counter += 1
      ptr = ptr.next
    }
    msg = msg + " ---> end"
    println(s"LIST OUTPUT: \n$msg\n")
  }
}
