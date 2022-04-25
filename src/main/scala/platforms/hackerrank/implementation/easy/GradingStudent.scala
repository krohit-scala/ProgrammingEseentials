package platforms.hackerrank.implementation.easy

object GradingStudent {
  def gradingStudents(grades: Array[Int]): Array[Int] = {
    // Write your code here
    var result = scala.collection.mutable.ArrayBuffer[Int]()
    for (grade <- grades){
      if(grade < 38)
        result.append(grade)
      else{
        val factor = grade / 5
        if((factor + 1) * 5 - grade < 3)
          result.append(5 * (factor + 1))
        else
          result.append(grade)
      }
    }
    result.toArray
  }

  def main(args: Array[String]): Unit = {
    val grades = Array(73, 67, 38, 33)
    val result = gradingStudents(grades)
    println(result.mkString(", "))
  }
}
