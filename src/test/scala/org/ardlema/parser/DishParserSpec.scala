package org.ardlema.parser

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class DishParserSpec extends FlatSpec with Matchers {
  "The dish parser" should "extract lunches from a text file" in {
    val expectedLunches = List(Dish("Garbanzos", "Tomate,Garbanzos"))

    val path = getClass.getResource("/lunches.txt").getPath
    val lunchesFile = new File(path)
    val parsedLunches = DishParserFromTextFile.parseFile(lunchesFile)
    parsedLunches shouldBe(expectedLunches)
  }

  //TODO: Wrong path tests!!
}


