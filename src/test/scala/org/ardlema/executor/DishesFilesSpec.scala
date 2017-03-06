package org.ardlema.executor

import org.ardlema.parser.CommandLineParser
import org.scalatest.{FlatSpec, Matchers}

class DishesFilesSpec extends FlatSpec with Matchers {

  "The dishes file" should "return a LunchesFile class" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient1 = "manuela@gmail.com"
    val recipient2 = "ramona@yahoo.com"
    val dishesRootPath = "/home/arodriguez/dev/menu-planner/src/test/resources/"
    val previousDishesRootPath = "/home/arodriguez/dev/menu-planner/src/test/resources/previous/"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""",
      s"""${CommandLineParser.passwordKey}=$passwordValue""",
      s"""${CommandLineParser.recipientsKey}=$recipient1,$recipient2""",
      s"""${CommandLineParser.rootPathKey}=$dishesRootPath""",
      s"""${CommandLineParser.previousRootPathKey}=$previousDishesRootPath"""
    )
    val commandLineParams = CommandLineParser.parse(expectedLunches)
    val dishesFiles = DishesFiles.getLunchesFile(commandLineParams.get)

    dishesFiles.lunchesFile.isDefined should be(true)
    dishesFiles.dinnersFile.isDefined should be(true)
    dishesFiles.previousLunchesFile.isDefined should be(true)
    dishesFiles.previousDinnersFile.isDefined should be(true)
  }

  it should "return None when the previous files does not exist" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient1 = "manuela@gmail.com"
    val recipient2 = "ramona@yahoo.com"
    val dishesRootPath = "/home/arodriguez/dev/menu-planner/src/test/resources/"
    val previousDishesRootPath = "/home/arodriguez/dev/menu-planner/src/test/resources/previousfake/"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""",
      s"""${CommandLineParser.passwordKey}=$passwordValue""",
      s"""${CommandLineParser.recipientsKey}=$recipient1,$recipient2""",
      s"""${CommandLineParser.rootPathKey}=$dishesRootPath""",
      s"""${CommandLineParser.previousRootPathKey}=$previousDishesRootPath"""
    )
    val commandLineParams = CommandLineParser.parse(expectedLunches)
    val dishesFiles = DishesFiles.getLunchesFile(commandLineParams.get)

    dishesFiles.previousLunchesFile.isDefined should be(false)
    dishesFiles.previousDinnersFile.isDefined should be(false)
  }
}