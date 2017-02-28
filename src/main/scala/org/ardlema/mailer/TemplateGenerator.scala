package org.ardlema.mailer

import java.io.StringWriter
import java.util.{ArrayList, HashMap}

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.ardlema.parser.Dish
import org.ardlema.planner.WeekDay

object TemplateGenerator {

  def generateTemplate(lunches: List[(WeekDay, Dish)], dinners: List[(WeekDay, Dish)]): String = {
    val ve = new VelocityEngine()
    ve.init()
    val context = new VelocityContext()
    val lunchesList = new ArrayList[HashMap[String, String]]()
    val dinnersList = new ArrayList[HashMap[String, String]]()
    context.put("lunchesList", lunchesList)
    context.put("dinnersList", dinnersList)

    for (lunch <- lunches) {
      val lunchesMap = new HashMap[String, String]()
      lunchesMap.put("description", lunch._2.description)
      lunchesMap.put("ingredients", lunch._2.ingredients)
      lunchesList.add(lunchesMap)
    }

    for (dinner <- dinners) {
      val dinnersMap = new HashMap[String, String]()
      dinnersMap.put("description", dinner._2.description)
      dinnersMap.put("ingredients", dinner._2.ingredients)
      dinnersList.add(dinnersMap)
    }

    val writer = new StringWriter()
    val template = ve.getTemplate("/src/main/resources/niceemail.vm")
    template.merge(context, writer)
    writer.toString
  }
}