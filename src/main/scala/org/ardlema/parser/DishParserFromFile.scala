package org.ardlema.parser

import java.io.File

import scala.io.Source

case class Dish(description: String, ingredients: String)

trait DishParserFromFile {

  def parseFile(file: File): List[Dish]
}

object DishParserFromTextFile extends DishParserFromFile {

  //TODO: Look for more functional ways on doing this and error handling!!!
  def parseFile(file: File) = {
    val lines = Source.fromFile(file)
    for { line <- lines.getLines().toList;
          dishElements = line.split('|')} yield Dish(dishElements(0),dishElements(1))
  }
}
