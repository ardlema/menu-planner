package org.ardlema.parser

case class CommandLineParams(sender: String, password: String, recipients: List[String], rootPath: String)

object CommandLineParser {
  //TODO: Pick these values from properties?
  val senderKey = "sender"
  val passwordKey = "password"
  val recipientsKey = "recipients"
  val rootPathKey = "rootpath"

  def parse(params: Array[String]): Option[CommandLineParams] = {
    val paramsToMap = params.map(_.split("=") match { case Array(k, v) => k->v } ).toMap
    for {
      sender <- paramsToMap.get(senderKey)
      password <- paramsToMap.get(passwordKey)
      recipients <- paramsToMap.get(recipientsKey)
      rootPath <- paramsToMap.get(rootPathKey)
    } yield CommandLineParams(sender, password, recipients.split(",").toList, rootPath)
  }
}

