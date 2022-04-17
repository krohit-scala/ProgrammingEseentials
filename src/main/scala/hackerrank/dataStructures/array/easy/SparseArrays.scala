package hackerrank.dataStructures.array.easy

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// https://www.hackerrank.com/challenges/sparse-arrays/
object SparseArrays {

  def matchingStrings(strings: Array[String], queries: Array[String]): Array[Int] = {
    // Write your code here
    // Base case check
    if(strings == null || queries == null)
      return null

    // Hashmap to hold data from strings array
    val map = mutable.HashMap[String, Int]()

    // Populate the HashMap with data
    for(s <- strings)
      map += s -> (map.getOrElse(s, 0) + 1)

    // Iterate over query
    val res = ArrayBuffer[Int]()
    for(q <- queries)
      res.append(map.getOrElse(q, 0))

    res.toArray
  }

  def main(args: Array[String]): Unit = {
    val strings : Array[String] = Array[String]("aa", "bab", "aa", "baba")
    val queries : Array[String] = Array[String]("aa", "xyz", "baba", "bab")
    println(s"Output : [${matchingStrings(strings, queries).mkString(", ")}]")
  }
}