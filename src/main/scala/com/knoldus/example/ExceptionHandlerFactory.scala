package com.knoldus.example

object ExceptionHandlerFactory {

  def getExceptionHandler(clazz: Class[_]) = ExceptionHandler(clazz)

}