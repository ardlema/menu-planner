package org.ardlema.parser

import java.io.File

import org.ardlema.planner.Legumbres
import org.scalatest.{FlatSpec, Matchers}

class DishParserSpec extends FlatSpec with Matchers {
  "The dish parser" should "extract lunches from a text file" in {
    val expectedLunches = List(Dish("Garbanzos", "Tomate,Garbanzos", Legumbres))

    val path = getClass.getResource("/lunches.txt").getPath
    val lunchesFile = new File(path)
    val parsedLunches = DishParserFromTextFile.parse(lunchesFile)
    parsedLunches shouldBe(expectedLunches)
  }

  //TODO: Wrong path tests!! 1. Empty lines!!
}


