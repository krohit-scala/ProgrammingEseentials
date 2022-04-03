package sql.join

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.expressions.Window
// import org.apache.spark.sql.functions.{col, concat_ws, count, countDistinct, expr, lit, lpad, weekofyear, when}
import org.apache.spark.sql.functions._

object Demo {
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

  def main(args: Array[String]): Unit = {
    val spark = getSparkSession

    val _transactionsDf = spark.read.format(
      "csv"
    ).option(
      "header",
      "true"
    ).option(
      "inferSchema",
      "true"
    ).option(
      "delimiter",
      "\t"
    ).load("/home/krohit/Desktop/Transaction.tsv")

    val _statsDf = spark.read.format(
      "csv"
    ).option(
      "header",
      "true"
    ).option(
      "inferSchema",
      "true"
    ).option(
      "delimiter",
      "\t"
    ).load("/home/krohit/Desktop/Stats.tsv")

    // Transaction Data
    val winSpec = Window.partitionBy("ClientNo")
    val transactionsDf = _transactionsDf.withColumn(
      "TransactionDate",
      col("TransactionDate").substr(0, 10)
    ).withColumn(
      "StartDate",
      min("TransactionDate").over(winSpec)
    ).withColumn(
      "EndDate",
      max("TransactionDate").over(winSpec)
    ).where("NOT (Clientno = 'C1001' AND TransactionDate BETWEEN '2020-01-02' AND '2020-01-04') ")

    // .where("Clientno = 'C1002'")
    // .where("NOT (Clientno = 'C1001' AND TransactionDate BETWEEN '2020-01-02' AND '2020-01-04') ")

    transactionsDf.show(10, false)

    // Stats Data
    val statsDf = _statsDf.withColumn(
      "IsHoliday",
      when(
        expr("Slno % 25 == 0"),
        lit(true)
      ).otherwise(lit(false))
    ).withColumn(
      "Date",
      col("Date").substr(0, 10)
    )

    // statsDf.filter("isHoliday == true").show(10, false)

    import spark.implicits._

    //val cond = col("TransactionDate") === col("Date")
    // val cond = expr("1 = 1")
    val cond = expr("TransactionDate = `Date` AND `Date` BETWEEN StartDate AND EndDate")
    // Join the datasets
    val joinedDf = statsDf.join(
      transactionsDf,
      cond,
      "cross"
    ).where(
      "`Date` BETWEEN StartDate AND EndDate"
    ).withColumn(
      "isWorkingDay",
      when(($"isWeekend" === false && $"IsHoliday" === false), true).otherwise(false)
    ).withColumn(
      "Date",
      col("Date").cast("timestamp")
    ).withColumn(
      "WeekNumber",
      concat_ws("-", col("year"), lpad(weekofyear(col("Date")), 2, "0"))
    )

    val testDf1 = joinedDf.groupBy(
      "ClientNo"
    ).agg(
      count($"Date").alias("NumberOfWorkingDays"),
      countDistinct($"TransactionDate").alias("DaysTransacted"),
      countDistinct($"WeekNumber").alias("NumberOfWorkingWeeks"),
      countDistinct($"Month").alias("NumberOfWorkingMonths")
    )

    joinedDf.show()
    testDf1.show()


  }

}
