package com.rhdzmota.examples.mongodb

import com.rhdzmota.examples.mongodb.model.config._
import com.rhdzmota.examples.mongodb.model.person._
import com.rhdzmota.examples.mongodb.model.MongoModel
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.model.Filters._

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Delete extends LazyLogging {
  def main(args: Array[String]): Unit = {
    System.setProperty("org.mongodb.async.type", "netty")
    MongoConfig.fromEnv match {
      case None => logger.warn("Cannot create a MongoConfig instance from env.")
      case Some(mongoConfig) =>
        val client: MongoClient = MongoClient(mongoConfig.getUri)
        val db: MongoDatabase   = client.getDatabase(mongoConfig.database).withCodecRegistry(MongoModel.codecRegistry)
        val collection: MongoCollection[Person] = db.getCollection("people")
        deletePeopleAgeLessThan(100, client, collection)
    }
  }
  def deletePeopleAgeLessThan(value: Int, client: MongoClient, collection: MongoCollection[Person]): Unit = {
    val d = collection.deleteMany(lt("age", value)).toFuture()

    Await.ready(d, Duration.Inf).onComplete {
      case Success(result) =>
        println(s"\nDeleted all people with age < $value.\n")
        println(result)
        client.close()
      case Failure(e) =>
        println(s"\nFailure: ${e.toString}")
        client.close()
    }
  }
}
