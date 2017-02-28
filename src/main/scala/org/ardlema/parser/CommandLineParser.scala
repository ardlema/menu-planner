package org.ardlema.parser

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

