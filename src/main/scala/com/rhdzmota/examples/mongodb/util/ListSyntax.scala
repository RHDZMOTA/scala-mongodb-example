package com.rhdzmota.examples.mongodb.util

case object ListSyntax {
  implicit class ListOps[A](list: List[A]) {
    def randomElement: Option[A] = list match {
      case Nil => None
      case _ => list.lift(scala.util.Random.nextInt(list.size))
    }
    def randomChoice(n: Int): Option[List[A]] =
      (1 to n).toList.foldLeft(Option(List[A]()))((acc, e) => randomElement.flatMap(r => acc.map(a => a :+ r)))
  }
}