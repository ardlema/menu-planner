package org.ardlema.menuplanner

import scala.io.Source

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class DishParserSpec extends FlatSpec with Matchers {
  "The dish parser" should "extract lunches from a text file" in {
    val expectedLunches = List(Dish("Garbanzos", "Tomate,Garbanzos"))

    val path = getClass.getResource("/lunches.txt").getPath
    val lunchesFile = new File(path)
    val parsedLunches = DishParserFromTextFile.parseLunches(lunchesFile)
    parsedLunches shouldBe(expectedLunches)
  }

  /*it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }*/
}

trait DishParserFromFile {

  def parseLunches(file: File): List[Dish]
}

object DishParserFromTextFile extends DishParserFromFile {

  def parseLunches(file: File) = {
    val lines = Source.fromFile(file)
    for { line <- lines.getLines().toList;
      dishElements = line.split('|')} yield Dish(dishElements(0),dishElements(1))
  }
}

sealed trait WeekDay { def name: String }
case object Lunes extends WeekDay { val name = "Lunes" }
case object Martes extends WeekDay { val name = "Martes" }
case object Miercoles extends WeekDay { val name = "Miercoles" }
case object Jueves extends WeekDay { val name = "Jueves" }
case object Viernes extends WeekDay { val name = "Viernes" }
case object Sabado extends WeekDay { val name = "Sabado" }
case object Domingo extends WeekDay { val name = "Domingo" }

case class Dish(description: String, ingredients: String)


