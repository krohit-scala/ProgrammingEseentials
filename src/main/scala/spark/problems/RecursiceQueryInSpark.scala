package spark.problems

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.sql.functions.{col, lit, collect_list}

object SparkApp {
  // Get or create SparkSession
  def getSparkSession : SparkSession = {

    // Create the SparkConf object
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("Spark_Test")

    // Get or create the Spark Session object
    val spark = SparkSession.builder
      .config(conf)
      .getOrCreate

    spark.sparkContext.setLogLevel("WARN")

    spark.conf.set("spark.sql.shuffle.partitions", 10)
    spark
  }

  def getEmployeeHierarchy(spark: SparkSession, empDf: Dataset[Row]) : Dataset[Row] = {
    import spark.implicits._

    // Empty dataframe to hold results
    val schema = empDf.withColumn("level", lit(1)).schema
    var df = spark.createDataFrame(spark.sparkContext.emptyRDD[Row], schema)

   // Get the anchor (Highest level employee)
    val topEmp = empDf
      .filter($"mgr_id".isNull)
      .withColumn("level", lit(1))

    // Count the levels
    var level = 1

    df = df.union(topEmp)

    // Read the data to form levels
    while(level <= 10){
      val mgrIds = df.filter($"level" === level).select("emp_id").collect().map(_.getInt(0)).toSeq
      val tempDf = empDf.filter($"mgr_id".isin(mgrIds:_*)).withColumn("level", lit(level+1))
      df = df.union(tempDf)
      level += 1
    }

    // Return the output
    df
  }

  def main(args: Array[String]): Unit = {
    // Get spark session
    val spark = getSparkSession
    import spark.implicits._

    // Read data as  dataframe
    val df = spark
      .read
      .format("csv")
      .option("header", true)
      .option("inferSchema", true)
      .option("delimiter", ",")
      .load("emp_data.csv")
      .withColumn("mgr_id", $"mgr_id".cast("integer"))

    df.show()

    // Build hierarchy
    getEmployeeHierarchy(spark, df).show()
  }

}
