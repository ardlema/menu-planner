package org.ardlema.executor

import org.ardlema.mailer.MenuMailer

object Executor {

  def main(args: Array[String]): Unit = {
    MenuMailer.sendMessage()
  }
}
