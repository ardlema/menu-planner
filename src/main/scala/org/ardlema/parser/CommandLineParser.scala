package org.ardlema.parser

import com.typesafe.scalalogging.LazyLogging

case class CommandLineParams(
    sender: String,
    password: String,
    recipients: List[String],
    rootPath: String,
    previousRootPath: String)

object CommandLineParser extends LazyLogging {
  //TODO: Pick these values from properties?
  val senderKey = "sender"
  val passwordKey = "password"
  val recipientsKey = "recipients"
  val rootPathKey = "rootpath"
  val previousRootPathKey = "previousrootpath"

  def parse(params: Array[String]): Option[CommandLineParams] = {
    val paramsToMap = params.map(_.split("=") match {
      case Array(k, v) => k->Some(v)
      case _ => "" -> None} )
    val paramsFiltered = (for ((k, v) <- paramsToMap if (v.isDefined)) yield (k, v.get)).toMap
    for {
      sender <- paramsFiltered.get(senderKey)
      password <- paramsFiltered.get(passwordKey)
      recipients <- paramsFiltered.get(recipientsKey)
      rootPath <- paramsFiltered.get(rootPathKey)
      previousRootPath <- paramsFiltered.get(previousRootPathKey)
    } yield CommandLineParams(sender, password, recipients.split(",").toList, rootPath, previousRootPath)
  }
}

