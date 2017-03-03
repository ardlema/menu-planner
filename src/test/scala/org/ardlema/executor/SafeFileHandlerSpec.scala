package org.ardlema.executor

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class SafeFileHandlerSpec extends FlatSpec with Matchers {

  "The safe file handler" should "return a Some(file) when the file exists" in {
    val lunchesFilePath = "/home/arodriguez/dev/menu-planner/src/main/resources/lunches.txt"
    val file = SafeFileHandler.getFile(lunchesFilePath)

    file.isDefined should be(true)
  }

  "it" should "return None when the file does not exist" in {
    val lunchesFilePath = "/fakedirectory"
    val file = SafeFileHandler.getFile(lunchesFilePath)

    val file2 = new File(lunchesFilePath).exists()
    file.isDefined should be(false)
  }
}

object SafeFileHandler {

  def getFile(path: String): Option[File] = {
    val file = new File(path)
    if (file.exists()) Some(file) else None
  }
}