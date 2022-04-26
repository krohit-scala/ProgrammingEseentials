package spark.problems

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, lag}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

object EmployeeGenerator {

  def fixEmployee(spark: SparkSession, empDf: Dataset[Row]) : Dataset[Row] = {
    val winSpec = Window.partitionBy("dept_id")
    var df = empDf.withColumn(
      "Seniority",
      dense_rank()over(winSpec.orderBy(col("doj").asc))
    ).withColumn(
      "manager_id",
      lag(col("id"), 1, null).over(winSpec.orderBy(col("seniority").asc))
    )

    // df.filter("dept_id == 101").show()
    df.drop("seniority")
  }

  def main(args: Array[String]): Unit = {
    val spark = SparkApp.getSparkSession

    val empDf = spark.read
      .format("csv")
      .option("header", "true")
      .option("delimiter", "\t")
      .load("employee.tsv")

    val fixedEmployee = fixEmployee(spark, empDf)
    // Write the output as TSV for export
//    fixedEmployee.coalesce(1)
//      .write
//      .format("csv")
//      .option("header", "true")
//      .option("delimiter", ",")
//      .save("output/employee_data_fixed/")

  }
}
