package com.rhdzmota.examples.mongodb

import com.rhdzmota.examples.mongodb.model.config._
import com.rhdzmota.examples.mongodb.model.person._
import com.rhdzmota.examples.mongodb.model.MongoModel
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Populate extends LazyLogging {

  def main(args: Array[String]): Unit = {
    System.setProperty("org.mongodb.async.type", "netty")
    MongoConfig.fromEnv match {
      case None => logger.warn("Cannot create a MongoConfig instance from env.")
      case Some(mongoConfig) =>
        val client: MongoClient = MongoClient(mongoConfig.getUri)
        val db: MongoDatabase   = client.getDatabase(mongoConfig.database).withCodecRegistry(MongoModel.codecRegistry)
        val collection: MongoCollection[Person] = db.getCollection("people")
        collectionOps(client, collection)
    }
  }
  def collectionOps(client: MongoClient, collection: MongoCollection[Person]): Unit = {
    val people: List[Person] = (1 to 100).toList.map(i => Person.random)
    val peopleInsertResults = collection.insertMany(people).toFuture()
    Await.ready(peopleInsertResults, Duration.Inf).onComplete {
      case Success(result) =>
        println("\nPeople inserted into the collection.\n")
        println(result.toString)
        client.close()
      case Failure(e) =>
        println(s"\nFailure: ${e.toString}")
        client.close()
    }
  }
}
