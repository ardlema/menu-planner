package org.ardlema.parser

import org.scalatest.{FlatSpec, Matchers}

class CommandLineParserSpec extends FlatSpec with Matchers {

  "The command line parser" should "parse properly the command line parameters" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient1 = "manuela@gmail.com"
    val recipient2 = "ramona@yahoo.com"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""",
      s"""${CommandLineParser.passwordKey}=$passwordValue""",
      s"""${CommandLineParser.recipientsKey}=$recipient1,$recipient2""")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(true)
    commandLineParams.get.sender shouldBe(senderValue)
    commandLineParams.get.password shouldBe(passwordValue)
    commandLineParams.get.recipients should contain theSameElementsAs(List(recipient1, recipient2))
  }

  it should "parse properly the command line parameters when receiving just one recipient" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient = "manuela@gmail.com"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""",
      s"""${CommandLineParser.passwordKey}=$passwordValue""",
      s"""${CommandLineParser.recipientsKey}=$recipient""")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(true)
    commandLineParams.get.sender shouldBe(senderValue)
    commandLineParams.get.password shouldBe(passwordValue)
    commandLineParams.get.recipients should contain theSameElementsAs(List(recipient))
  }

  it should "return None when does not receive all the parameters" in {
    val senderValue = "amparo@gmail.com"
    val expectedLunches: Array[String] = Array(
      s"""${CommandLineParser.senderKey}=$senderValue""")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.isDefined shouldBe(false)
  }
}