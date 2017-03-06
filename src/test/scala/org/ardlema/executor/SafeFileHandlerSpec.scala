package org.ardlema.executor

import org.scalatest.{FlatSpec, Matchers}

class SafeFileHandlerSpec extends FlatSpec with Matchers {

  "The safe file handler" should "return a Some(file) when the file exists" in {
    val lunchesFilePath = getClass.getResource("/lunches.txt").getPath
    val file = SafeFileHandler.getFile(lunchesFilePath)

    file.isDefined should be(true)
  }

  "it" should "return None when the file does not exist" in {
    val file = SafeFileHandler.getFile("/lunchesfake.txt")

    file.isDefined should be(false)
  }
}