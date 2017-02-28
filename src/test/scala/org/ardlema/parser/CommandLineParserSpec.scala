package org.ardlema.parser

import org.scalatest.{FlatSpec, Matchers}

class CommandLineParserSpec extends FlatSpec with Matchers {

  "The command line parser" should "parse properly the command line parameters" in {
    val senderValue = "amparo@gmail.com"
    val passwordValue = "12345"
    val recipient1 = "manuela@gmail.com"
    val recipient2 = "ramona@yahoo.com"
    val expectedLunches: Array[String] = Array(
      s"""sender=$senderValue""",
      s"""password=$senderValue""",
      s"""recipients=$recipient1,$recipient2""")
    val commandLineParams = CommandLineParser.parse(expectedLunches)

    commandLineParams.sender shouldBe(senderValue)
    commandLineParams.password shouldBe(passwordValue)
    commandLineParams.recipients should contain theSameElementsAs(List(recipient1, recipient2))
  }
}

case class CommandLineParams(sender: String, password: String, recipients: List[String])

object CommandLineParser {

  def parse(params: Array[String]): CommandLineParams = {
    CommandLineParams("", "", List())
  }
}
