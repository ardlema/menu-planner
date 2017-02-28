package org.ardlema.parser

import scala.io.Source
import java.io.File
import org.ardlema.planner.DishType

case class Dish(description: String, ingredients: String, dishType: DishType)

trait DishParser[T, A] {

  def parse(t: T): List[A]
}

trait DishParserFromFile extends DishParser[File, Dish] {

  def parse(file: File): List[Dish]
}

object DishParserFromTextFile extends DishParserFromFile {

  val lunchesTextFile = "lunches.txt"
  val dinnersTextFile = "dinners.txt"

  //TODO: Look for more functional ways on doing this and error handling!!!
  def parse(file: File) = {
    val lines = Source.fromFile(file)
    for { line <- lines.getLines().toList;
          dishElements = line.split('|')} yield Dish(
            dishElements(0),
            dishElements(1),
            DishType.fromString(dishElements(2)))
  }
}
