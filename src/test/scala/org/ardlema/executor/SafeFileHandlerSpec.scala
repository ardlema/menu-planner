package org.ardlema.executor

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class SafeFileHandlerSpec extends FlatSpec with Matchers {

  "The safe file handler" should "return a Some(file) when the file exists" in {
    val lunchesFilePath = getClass.getResource("/lunches.txt").getPath
    val file = SafeFileHandler.getFile(lunchesFilePath)

    file.isDefined should be(true)
  }

  "it" should "return None when the file does not exist" in {
    val lunchesFilePath = getClass.getResource("/lunchesfake.txt").getPath
    val file = SafeFileHandler.getFile(lunchesFilePath)

    val file2 = new File(lunchesFilePath).exists()
    file.isDefined should be(false)
  }
}