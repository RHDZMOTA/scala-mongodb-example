package com.rhdzmota.examples.mongodb.model.shape

import com.rhdzmota.examples.mongodb.model.MongoModel
import org.mongodb.scala.bson.ObjectId

sealed class Shape

sealed trait ShapeOps {
  def area: Double
  def shape: String
}

final case class Square(_id: ObjectId, side: Double, shape: String) extends Shape with ShapeOps with MongoModel {
  def area: Double = side * side
}
object Square {
  def apply(side: Double): Square = Square(new ObjectId(), side, "square")
}

final case class Rectangle(_id: ObjectId, width: Double, height: Double, shape: String) extends Shape with ShapeOps with MongoModel {
  def area: Double = width * height
}
object Rectangle {
  def apply(width: Double, height: Double): Rectangle = Rectangle(new ObjectId(), width, height, "rectangle")
}

final case class Triangle(_id: ObjectId, base: Double, height: Double, shape: String) extends Shape with ShapeOps with MongoModel {
  def area: Double = base * height / 2
}
object Triangle {
  def apply(base: Double, height: Double): Triangle = Triangle(new ObjectId(), base, height, "triangle")
}
