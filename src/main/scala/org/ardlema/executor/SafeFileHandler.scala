package org.ardlema.executor

import java.io.File

object SafeFileHandler {

  def getFile(path: String): Option[File] = {
    val file = new File(path)
    if (file.exists()) Some(file) else None
  }
}