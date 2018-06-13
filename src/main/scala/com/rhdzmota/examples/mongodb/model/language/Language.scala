package com.rhdzmota.examples.mongodb.model.language

import com.rhdzmota.examples.mongodb.model.MongoModel
import org.mongodb.scala.bson.ObjectId

sealed class Language

final case class En(_id: ObjectId, since: Int, lang: String) extends Language with MongoModel
object En {
  def apply(since: Int): En = En(new ObjectId(), since, "en")
}

final case class Es(_id: ObjectId, since: Int, lang: String) extends Language with MongoModel
object Es {
  def apply(since: Int): Es = Es(new ObjectId(), since, "es")
}

final case class Fr(_id: ObjectId, since: Int, lang: String) extends Language with MongoModel
object Fr {
  def apply(since: Int): Fr = Fr(new ObjectId(), since, "fr")
}
