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
}

case class CommandLineParams(sender: String, password: String, recipients: List[String])

object CommandLineParser {

  //TODO: Pick these values from properties?
  val senderKey = "sender"
  val passwordKey = "password"
  val recipientsKey = "recipients"

  def parse(params: Array[String]): Option[CommandLineParams] = {
    val paramsToMap = params.map(_.split("=") match { case Array(k, v) => k->v } ).toMap
    for {
      sender <- paramsToMap.get(senderKey)
      password <- paramsToMap.get(passwordKey)
      recipients <- paramsToMap.get(recipientsKey)
    } yield CommandLineParams(sender, password, recipients.split(",").toList)
  }
}
