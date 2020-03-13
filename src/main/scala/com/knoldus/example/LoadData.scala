package com.knoldus.example

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql._
//import org.slf4j.event.Level
//import org.slf4j.{Logger, LoggerFactory}
import org.apache.log4j._

object LoadData {

  private val LOGGER: Logger = Logger.getLogger(this.getClass)
  LOGGER.setLevel(Level.DEBUG)
  private val exceptionHandlerObj = ExceptionHandlerFactory.getExceptionHandler(this.getClass)

  private def getCurrentMethodName: String = Thread.currentThread.getStackTrace()(2).getMethodName

  def main(args: Array[String]): Unit = {

    LOGGER.info(s"$getCurrentMethodName Start of LoadData")

    LOGGER.debug("Creating SparkSession")
    val spark = SparkSession.builder()
      .appName("DEMO")
      .master("local[1]")
      .getOrCreate()

    LOGGER.debug("Creating ExceptionHandler")


    exceptionHandlerObj.checkForException(() => {
      val df = spark.read.format("csv")
        .option("header", true)
        .load("students.csv")

      val df2 = spark.read.format("csv")
        .option("header", true)
        .load("students2.csv")

      val genData = genStoreData(df, df2)
      genData.write.csv("result.csv")
      spark.stop()
    })

    def genStoreData(input_table: DataFrame, table: DataFrame): DataFrame = {
      LOGGER.info(s"$getCurrentMethodName Start of LoadData")
      val process_table = input_table
      var x = process_table.select("id", "first_name")
        .distinct
      x = x.join(table, Seq("id"))
      x = x.withColumnRenamed("id", "day")
      aggregateData(x, table)
    }

    def aggregateData(input_table: DataFrame, table: DataFrame): DataFrame = {
      LOGGER.info(s"$getCurrentMethodName Start of LoadData")
      input_table.select("id")
    }
  }
}