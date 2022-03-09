package sql.join

//import scala.util.parsing.json.JSON
import scala.collection.mutable.ArrayBuffer
import scala.io._
import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.google.gson.{Gson, GsonBuilder}

import java.io.Writer
import scala.collection.mutable.SortedSet

case class Click(impressionId : String, revenue: Float)
case class Impression(Id : String, appId: Int, advertiserId: Int, countryCode: String)
case class JoinedObject(impressionId : String, appId: Int, advertiserId: Int, countryCode: String, revenue: Float){
  def showData : Unit = {
    println(s"Impression_Id: ${impressionId}, App_Id: ${appId}, Advertiser_Id: ${advertiserId}, Country_Code: ${countryCode}, Revenue: ${revenue}")
  }
}
case class MetricsObjects(appId: Int, countryCode: String, impressions: Int, clicks: Int, revenue: Float){
  def showData = {
    println(s"App_Id: ${appId}, Country_Code: ${countryCode}, Count_Impressions: ${impressions}, Count_Clicks: ${clicks}, Sum_Revenue: ${revenue}")
  }
}
case class AdvertiserVsRevenue(advertiserId: Int, revenue: Float) extends Ordered [AdvertiserVsRevenue]{
  def compare (that: AdvertiserVsRevenue): Int = {
    if (this.revenue == that.revenue)
      0
    else if (this.revenue > that.revenue)
      1
    else
      -1
  }
}
case class TopFiveCombinations(app_id: Int, country_code: String, recommended_advertiser_ids: Array[Int], revenue_values: Array[Float]){
  def showData = {
    println(s"app_id: ${app_id}, country_code: ${country_code}, recommended_advertiser_ids: [${recommended_advertiser_ids.mkString(", ")}], Revenues: [${revenue_values.mkString(", ")}]")
  }
}

object App1 {
  def readJsonFromFile(path : String) : Array[Map[String, String]] = {
    println(s"reading JSON from: ${path}")
    val json = Source.fromFile(path)
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val parsedJson = mapper.readValue[Array[Map[String, String]]](json.reader)
    parsedJson
  }

  def parseClicks(path: String): Map[String, ArrayBuffer[Click]] = {
    // Variable that contains the JSON dataset
    var clicksMap = Map[String, ArrayBuffer[Click]]()

    // Read JSON objects one by one
    val parsedJson = readJsonFromFile(path)
    for(data <- parsedJson){
      val impressionId = data.getOrElse("impression_id", null)
      val value = data.getOrElse("revenue", 0.toString)

      val click : Click = new Click(impressionId, value.toFloat)
      var prevArr : ArrayBuffer[Click]= clicksMap.getOrElse(impressionId, null)
      if(prevArr == null){
        prevArr = ArrayBuffer[Click]()
      }
      prevArr.append(click)
      clicksMap = clicksMap + (impressionId -> prevArr)
    }

    // Testing
    // println(clicksMap.mkString(", "))
    // println()
    clicksMap
  }

  def parseImpressions(path: String): Map[String, ArrayBuffer[Impression]] = {
    // Variable that contains the JSON dataset
    var impressionsMap = Map[String, ArrayBuffer[Impression]]()

    // Read JSON objects one by one
    val parsedJson = readJsonFromFile(path)
    for(data <- parsedJson){
      val nullInt = Int.MinValue.toString
      val id : String = data.getOrElse("id", null)
      val appId : Int = data.getOrElse("app_id", nullInt).toInt
      val advertiserId : Int = data.getOrElse("advertiser_id", nullInt).toInt
      val countryCode : String = data.getOrElse("country_code", null)
      // val value = s"${appId}, ${advertiserId}, ${countryCode}"

      val impression : Impression = new Impression(id, appId, advertiserId, countryCode)
      var prevArr = impressionsMap.getOrElse(id, null)
      if(prevArr == null){
        prevArr = ArrayBuffer[Impression]()
      }
      prevArr.append(impression)
      impressionsMap = impressionsMap + (id -> prevArr)
    }

    // Testing
    // println(impressionsMap.mkString(", "))
    // println()
    impressionsMap
  }

  // d1 LEFT OUTER JOIN d2
  def joinDatasets(d1: Map[String, ArrayBuffer[Impression]], d2: Map[String, ArrayBuffer[Click]]): ArrayBuffer[JoinedObject] = {
    // TODO: DONE: It should be Impressions joined Clicks to support the Goal 2
    // Scope to implement delegation pattern
    // val d1 = (if(obj1.isInstanceOf[Map[String, Array[Click]]]) obj1 else obj2).asInstanceOf[Map[String, Array[Click]]]
    // val d2 = (if(obj1.isInstanceOf[Map[String, Array[Impression]]]) obj1 else obj2).asInstanceOf[Map[String, Array[Impression]]]

    // Contains the result of joined operations
    var result = ArrayBuffer[JoinedObject]()

    // Read 1st dataset
    for(key <- d1.keySet){
      val impressionId = key
      val dataArr1 = d1.getOrElse(key, null) // .asInstanceOf[ArrayBuffer[String]]
      if(! dataArr1.isEmpty){
        for(data1 <- dataArr1){
          val appId = data1.appId
          val countryCode = data1.countryCode
          val advertiserId = data1.advertiserId

          // Read the second dataset
          val dataArr2 = d2.getOrElse(key, null) // .asInstanceOf[ArrayBuffer[String]]
          if(dataArr2 != null && !dataArr2.isEmpty){
            for(data2 <- dataArr2){
              // Temp variable to hold current record
              val revenue = data2.revenue
              val joinedObject = JoinedObject(impressionId, appId, advertiserId, countryCode, revenue)
              result.append(joinedObject)
            }
          }else{
            val joinedObject = JoinedObject(impressionId, appId, advertiserId, countryCode, Float.NaN)
            result.append(joinedObject)
          }
        }
      }
    }
    result
  }

  def computeMetrics(joinedDatasets: ArrayBuffer[JoinedObject]) : ArrayBuffer[MetricsObjects] = {
    var result = ArrayBuffer[MetricsObjects]()

    // Hashmap tp store intermediate results
    // (Int, String) means (AppId, CountryCode), (Int, int, Float) means (CountImpressions, CountClicks, SumRevenues)
    // We implemented LEFT OUTER JOIN, means, Revenue == null => No Click and null revenue
    var metricsMap = Map[(Int, String), (Int, Int, Float)]()
    for(obj <- joinedDatasets){
      val appId = obj.appId
      val countryCode = obj.countryCode
      val revenue = obj.revenue

      val key = (appId, countryCode)
      var prevValue : (Int, Int, Float)= metricsMap.getOrElse(key, (0, 0, 0.0f))
      val newValue = (
        prevValue._1 + 1,
        if(!revenue.isNaN) prevValue._2 + 1 else prevValue._2,
        if(!revenue.isNaN) prevValue._3 + revenue else prevValue._3
      )

      metricsMap = metricsMap + (key -> newValue)
    }
    // case class MetricsObjects(appId: Int, countryCode: String, impressions: Int, clicks: Int, revenue: Float)
    // Populate the result
    for(key <- metricsMap.keySet){
      result.append(MetricsObjects(
        key._1,
        key._2,
        metricsMap.getOrElse(key, (0, 0, 0))._1,
        metricsMap.getOrElse(key, (0, 0, 0))._2,
        metricsMap.getOrElse(key, (0, 0, 0.0f))._3
      ))
    }
    println(s"Metrics array has ${result.size} records!")
    result
  }

  def filterTopRevenueRecords(joinedDatasets: ArrayBuffer[JoinedObject]) = {
    // val sortedByRevenue = joinedDatasets.sortWith(_.revenue > _.revenue)
    var output = ArrayBuffer[TopFiveCombinations]()

    // Hold temporary data
    var revenueMap = Map[(Int, String), SortedSet[AdvertiserVsRevenue]]()
    println(s"\n\nThe size of joinedDatasets is: ${joinedDatasets.size}")

    for(obj <- joinedDatasets){
      val appId = obj.appId
      val countryCode = obj.countryCode
      val advertiserId = obj.advertiserId
      val revenue = obj.revenue
      if(!revenue.isNaN) {
        val key = (appId, countryCode)
        val currValue = AdvertiserVsRevenue(advertiserId, revenue)
        var prevValue = revenueMap.getOrElse(key, SortedSet[AdvertiserVsRevenue]())
        prevValue += (currValue)
        revenueMap = revenueMap + (key -> prevValue)
      }
    }

    // Take top 5 revenue generating advertiser ids
    for(key <- revenueMap.keySet){
      revenueMap = revenueMap + (key -> revenueMap(key).take(5))
    }
    println(s"\n\nThe size of revenueMap is: ${revenueMap.size}")

    // Data is ready. Make them as object.
    for(key <- revenueMap.keySet){
      val appId = key._1
      val countryCode = key._2
      val listValues = revenueMap(key)
      var recommended_advertiser_ids = ArrayBuffer[Int]()
      var revenue_values = ArrayBuffer[Float]()
      for(data <- listValues){
        recommended_advertiser_ids.append(data.advertiserId)
        revenue_values.append(data.revenue)
      }
      val outputObject = TopFiveCombinations(appId, countryCode, recommended_advertiser_ids.toArray, revenue_values.toArray)
      output.append(outputObject)
    }

    // Return the output
    println(s"\n\nThe size of Goal 3 output is: ${output.size}")
    output
  }

  def toApnaJson(path: String, obj: ArrayBuffer[TopFiveCombinations]) = {
    val gson = new GsonBuilder().setPrettyPrinting().create()
    import java.io.FileWriter
    val writer : Writer = new FileWriter(path)
    gson.toJson(obj.toArray, writer)
    writer.close()
  }

  def main(args: Array[String]): Unit = {
    println("Lets start...\n\n")
    val path1 = "clicks.json"
    val path2 = "impressions.json"

    // Goal 1.1 : Reading and Parsing JSON File
    val clicks = parseClicks(path1)
    val impressions = parseImpressions(path2)
    // Goal 1.1: Testing
    // println(clicks.mkString(", "))
    // println()
    // println(impressions.mkString(", "))

    // Goal 1.2: Join the JSON Datasets
    val joinedDatasets = joinDatasets(impressions, clicks)
    // Goal 1.2: Testing the join
    // println("Samples from joinData(clicks, impressions)")
    // for(obj <- joinedDatasets){
    //   obj.showData
    // }

    var counter = 0
    // while(counter < 51){
    //   joinedDatasets(counter).showData
    //   counter += 1
    // }

    // Goal 2.0: Compute the metrics
    // Equivalent of df.groupBy("app_id", "country_code").agg(count("impressions"), count("clicks"), sum("revenue"))
    val computedMetrics = computeMetrics(joinedDatasets)
    counter = 0
    while(counter < 10){
      computedMetrics(counter).showData
      counter += 1
    }

    // Goal 3: Recommendation for the Top 5
    // println("\n\nTop 5 Revenue generating data")
    // Filters for top 5
    val filteredRecords = filterTopRevenueRecords(joinedDatasets)
//    for(i <- 0 to filteredRecords.size-1){
//      filteredRecords(i).showData
//    }

    toApnaJson("target/top_five_output.json", filteredRecords)
  }
}
