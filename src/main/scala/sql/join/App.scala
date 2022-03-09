package sql.join

//import scala.util.parsing.json.JSON
import scala.collection.mutable.ArrayBuffer
import scala.io._
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object App {

  def parseClicks(parsedJson : Array[Map[String, String]]): Map[String, ArrayBuffer[String]] = {
    var clicksMap = Map[String, ArrayBuffer[String]]()

    // Read JSON objects one by one
    for(data <- parsedJson){
      val key = data.getOrElse("impression_id", null)
      val value = data.getOrElse("revenue", 0.toString)

      var prevArr = clicksMap.getOrElse(key, null)
      if(prevArr == null){
        prevArr = ArrayBuffer[String]()
      }
      prevArr.append(value)
      clicksMap = clicksMap + (key -> prevArr)
    }
    // Testing
    // println(clicksMap.mkString(", "))
    // println()
    clicksMap
  }

  def parseImpressions(parsedJson: Array[Map[String, String]]): Map[String, ArrayBuffer[String]] = {
    var impressionsMap = Map[String, ArrayBuffer[String]]()

    // Read JSON objects one by one
    for(data <- parsedJson){

      val key = data.getOrElse("id", null)
      val appId : String = data.getOrElse("app_id", null)
      val advertiserId : String = data.getOrElse("advertiser_id", null)
      val countryCode : String = data.getOrElse("country_code", null)
      val value = s"${appId}, ${advertiserId}, ${countryCode}"

      var prevArr = impressionsMap.getOrElse(key, null)
      if(prevArr == null){
        prevArr = ArrayBuffer[String]()
      }
      prevArr.append(value)
      impressionsMap = impressionsMap + (key -> prevArr)
    }
    // Testing
    // println(impressionsMap.mkString(", "))
    // println()
    impressionsMap
  }

  def readJsonFromFile(path : String, className : String) : Map[String, ArrayBuffer[String]] = {
    println(s"reading JSON from: ${path}")
    val json = Source.fromFile(path)
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val parsedJson = mapper.readValue[Array[Map[String, String]]](json.reader)

    // Contains the data from JSON file
    var data : Map[String, ArrayBuffer[String]] = null

    // Check for type of data to be read
    if (className == "clicks"){
       data = parseClicks(parsedJson)
    }
    if(className == "impressions"){
      data = parseImpressions(parsedJson)
    }
    // Return the dataset
    data
  }

  // d1 LEFT OUTER JOIN d2
  def showJoinedData(d1: Map[String, ArrayBuffer[String]], d2: Map[String, ArrayBuffer[String]]): Unit = {
    // Read 1st dataset
    for(key <- d1.keySet){
      val impressionId = key
      val dataArr1 = d1.getOrElse(key, null) // .asInstanceOf[ArrayBuffer[String]]
      if(! dataArr1.isEmpty){
        for(data1 <- dataArr1){
          // Read the second dataset
          val dataArr2 = d2.getOrElse(key, null) // .asInstanceOf[ArrayBuffer[String]]
          if(dataArr2 != null && !dataArr2.isEmpty){
            for(data2 <- dataArr2){
              // Temp variable to hold current record
              var result = ArrayBuffer[String]()
              result.append(key)
              result.append(data1)
              result.append(data2)

              // Print the joined dataset
              println(result.mkString(", "))
            }
          }
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println("Lets start...\n\n")
    val path1 = "clicks.json"
    val path2 = "impressions.json"
    val clicks = readJsonFromFile(path1, "clicks")
    val impressions = readJsonFromFile(path2, "impressions")

    println("showJoinedData(clicks, impressions)")
    showJoinedData(clicks, impressions)
    println("\nshowJoinedData(impressions, clicks)")
    showJoinedData(impressions, clicks)
  }
}
