package org.ardlema.parser

import org.scalatest.{FlatSpec, Matchers}

class CommandLineParserSpec extends FlatSpec with Matchers {

  "The command line parser" should "parse properly the command line parameters" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient1 = "manuela@gmail.com"
    val recipient2 = "ramona@yahoo.com"
    val dishesRootPath = "/home/pepe/templates/"
    val previousDishesRootPath = "/home/pepe/previous/"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""",
      s"""${CommandLineParser.passwordKey}=$passwordValue""",
      s"""${CommandLineParser.recipientsKey}=$recipient1,$recipient2""",
      s"""${CommandLineParser.rootPathKey}=$dishesRootPath""",
      s"""${CommandLineParser.previousRootPathKey}=$previousDishesRootPath"""
    )
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(true)
    commandLineParams.get.sender shouldBe(senderValue)
    commandLineParams.get.password shouldBe(passwordValue)
    commandLineParams.get.recipients should contain theSameElementsAs(List(recipient1, recipient2))
    commandLineParams.get.rootPath shouldBe(dishesRootPath)
    commandLineParams.get.previousRootPath shouldBe(previousDishesRootPath)
  }

  it should "parse properly the command line parameters when receiving just one recipient" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient = "manuela@gmail.com"
    val dishesRootPath = "/home/pepe/templates/"
    val previousDishesRootPath = "/home/pepe/previous/"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""",
      s"""${CommandLineParser.passwordKey}=$passwordValue""",
      s"""${CommandLineParser.recipientsKey}=$recipient""",
      s"""${CommandLineParser.rootPathKey}=$dishesRootPath""",
      s"""${CommandLineParser.previousRootPathKey}=$previousDishesRootPath""")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(true)
    commandLineParams.get.sender shouldBe(senderValue)
    commandLineParams.get.password shouldBe(passwordValue)
    commandLineParams.get.recipients should contain theSameElementsAs(List(recipient))
    commandLineParams.get.rootPath shouldBe(dishesRootPath)
    commandLineParams.get.previousRootPath shouldBe(previousDishesRootPath)
  }

  it should "return None when does not receive all the parameters" in {
    val senderValue = "amparo@gmail.com"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(false)
  }

  it should "return None when receives a parameter without equals" in {
    val expectedLunches: Array[String] = Array("param1")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(false)
  }
}