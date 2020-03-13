version := "1.0"

scalaVersion := "2.11.8"

sbtVersion := "1.1.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"

mainClass in run:=Some("com.knoldus.example.LoadData")