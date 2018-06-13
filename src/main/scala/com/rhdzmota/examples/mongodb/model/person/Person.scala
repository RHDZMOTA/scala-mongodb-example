package com.rhdzmota.examples.mongodb.model.person

import org.mongodb.scala.bson.ObjectId
import com.rhdzmota.examples.mongodb.model.MongoModel
import com.rhdzmota.examples.mongodb.model.gender.{Female, Gender, Male}
import com.rhdzmota.examples.mongodb.model.language.{En, Es, Fr, Language}
import com.rhdzmota.examples.mongodb.model.shape.Shape
import com.rhdzmota.examples.mongodb.util.ListSyntax._

case class Person(_id: ObjectId, firstName: String, lastName: String, age: Int, gender: Option[Gender], languages: List[Language], favShape: Option[Shape]) extends MongoModel

object Person {
  val firstNames: List[String] = List("Casey", "Riley", "Jessie", "Jack", "Avery", "Jaime", "Kendall", "Skyler", "Harley")
  val lastNames: List[String] = List("Smith", "Hall", "Stewart", "Johnson", "Allen", "Wood", "Jones", "Adams", "Cooper", "Anderson")
  val genderCategories: List[Option[Gender]] = List(Some(Male()), Some(Female()), None)
  def langCategories(since: Int): List[Language] = List(En(since), Es(since), Fr(since))
  val langExperienceRange: (Int, Int) = (0, 15)
  val ageRange: (Int, Int) = (15, 99)

  def apply(firstName: String, lastName: String, age: Int, gender: Option[Gender], languages: List[Language], favShape: Option[Shape]): Person =
    Person(new ObjectId(), firstName, lastName, age, gender, languages, favShape)

  def random: Person = Person(
    firstName = firstNames.randomElement.get,
    lastName  = lastNames.randomElement.get,
    age       = (ageRange._1 to ageRange._2).toList.randomElement.get,
    gender    = genderCategories.randomElement.get,
    languages = langCategories((langExperienceRange._1 to langExperienceRange._2).toList.randomElement.get).randomChoice(3).get.distinct,
    favShape  = None
  )
}
