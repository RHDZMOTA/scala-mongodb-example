package com.rhdzmota.examples.mongodb

import com.rhdzmota.examples.mongodb.model.config._
import com.rhdzmota.examples.mongodb.model.person._
import com.rhdzmota.examples.mongodb.model.MongoModel
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Sorts._

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Querying extends LazyLogging {

  def main(args: Array[String]): Unit = {
    System.setProperty("org.mongodb.async.type", "netty")
    MongoConfig.fromEnv match {
      case None => logger.warn("Cannot create a MongoConfig instance from env.")
      case Some(mongoConfig) =>
        val client: MongoClient = MongoClient(mongoConfig.getUri)
        val db: MongoDatabase = client.getDatabase(mongoConfig.database).withCodecRegistry(MongoModel.codecRegistry)
        val collection: MongoCollection[Person] = db.getCollection("people")
        queryOps(client, collection)
    }
  }
  def queryOps(client: MongoClient, collection: MongoCollection[Person]): Unit = {
    val q = collection
      .find(and(gte("age", 20), lt("age", 30)))
      .sort(ascending("age"))
      .toFuture()
    Await.ready(q, Duration.Inf).onComplete {
      case Success(result) =>
        println(s"\nQuery results: \n\t${result.mkString("\n\t")}")
        client.close()
      case Failure(e) =>
        println("\nFailure: " + e.toString)
        client.close()
    }
  }
}
