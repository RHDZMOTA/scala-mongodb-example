import Dependencies._
import sbt.util

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.rhdzmota",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "scala-mongodb-example",
    logLevel := Level.Warn,
    libraryDependencies ++= Seq(
      // Mongo Dependencies
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.3.0",
      "io.netty" % "netty-all" % "4.1.17.Final",
      "org.mongodb" % "bson" % "2.3",
      // Logging Backend and Scala Logging
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
      // Tests
      scalaTest % Test
    )
  )
