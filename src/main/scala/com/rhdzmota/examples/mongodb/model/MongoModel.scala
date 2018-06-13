package com.rhdzmota.examples.mongodb.model

import com.rhdzmota.examples.mongodb.model.shape._
import com.rhdzmota.examples.mongodb.model.gender._
import com.rhdzmota.examples.mongodb.model.language._
import com.rhdzmota.examples.mongodb.model.person.Person
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.{DEFAULT_CODEC_REGISTRY, Macros}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

trait MongoModel {
  def _id: ObjectId
}

object MongoModel {
  val codecRegistry = fromRegistries(
    fromProviders(
      classOf[Person],
      classOf[Gender],
      classOf[Language],
      classOf[Shape]
    ),
    DEFAULT_CODEC_REGISTRY
  )
}
