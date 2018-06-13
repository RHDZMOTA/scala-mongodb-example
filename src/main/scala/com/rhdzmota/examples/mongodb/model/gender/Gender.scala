package com.rhdzmota.examples.mongodb.model.gender

import com.rhdzmota.examples.mongodb.model.MongoModel
import org.mongodb.scala.bson.ObjectId

sealed class Gender

final case class Male(_id: ObjectId, gender: String) extends Gender with MongoModel
object Male {
  def apply(): Male = Male(new ObjectId(), "male")
}

final case class Female(_id: ObjectId, gender: String) extends Gender with MongoModel
object Female {
  def apply(): Female = Female(new ObjectId(), "female")
}
