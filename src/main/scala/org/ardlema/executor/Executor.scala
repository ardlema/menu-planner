package org.ardlema.executor

import java.io.StringWriter
import java.util.{ArrayList, HashMap}

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.ardlema.mailer.MenuMailer


object Executor {

  def main(args: Array[String]): Unit = {

    val ve = new VelocityEngine()
    ve.init()
    val lunchesList = new ArrayList[HashMap[String, String]]()
    val context = new VelocityContext()
    context.put("lunchesList", lunchesList)

    val map1 = new HashMap[String, String]()
    map1.put("description", "Garbanzos con tomate")
    map1.put("ingredients", "Tomate,Garbanzos")
    lunchesList.add(map1)

    val map2 = new HashMap[String, String]()
    map2.put("description", "Arroz con pollo")
    map2.put("ingredients", "Arroz,Pollo")
    lunchesList.add(map2)

    val writer = new StringWriter()
    val template = ve.getTemplate("/src/main/resources/email.vm")
    template.merge(context, writer)
    val body = writer.toString

    MenuMailer.sendMessage(body)
  }
}
