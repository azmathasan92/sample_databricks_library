package com.knoldus.example

import org.apache.spark.sql.AnalysisException
import org.slf4j.LoggerFactory

case class ExceptionHandler(clazz: Class[_]) {

  private val exceptionLogger = LoggerFactory.getLogger(clazz)

  def checkForException(a: () => Unit): Unit = {
    try {
      a()
    } catch {
      case exception: AnalysisException => {
        exceptionLogger.error(s"FAILED: ${exception.message}")
        System.exit(1)
      }
      case exception: Exception => {
        exceptionLogger.error(s"FAILED: ${exception.getMessage}")
        System.exit(1)
      }
    }
  }

}