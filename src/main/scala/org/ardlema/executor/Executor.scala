package org.ardlema.executor

import java.io.{File, StringWriter}
import java.util.{ArrayList, HashMap}

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.ardlema.mailer.MenuMailer
import org.ardlema.parser.DishParserFromTextFile
import org.ardlema.planner.MenuPlanner


object Executor {

  def main(args: Array[String]): Unit = {

    val ve = new VelocityEngine()
    ve.init()
    val lunches = DishParserFromTextFile.parse(getResourceFile("/lunches.txt"))
    val dinners = DishParserFromTextFile.parse(getResourceFile("/dinners.txt"))

    val plannedLunches = MenuPlanner.planAWeek(lunches, LunchesPerDay.lunchesPerDay)
    val plannedDinners = MenuPlanner.planAWeek(dinners, LunchesPerDay.dinnersPerDay)

    val context = new VelocityContext()
    val lunchesList = new ArrayList[HashMap[String, String]]()
    val dinnersList = new ArrayList[HashMap[String, String]]()
    context.put("lunchesList", lunchesList)
    context.put("dinnersList", dinnersList)

    for (lunch <- plannedLunches) {
      val lunchesMap = new HashMap[String, String]()
      lunchesMap.put("description", lunch._2.description)
      lunchesMap.put("ingredients", lunch._2.ingredients)
      lunchesList.add(lunchesMap)
    }

    for (dinner <- plannedDinners) {
      val dinnersMap = new HashMap[String, String]()
      dinnersMap.put("description", dinner._2.description)
      dinnersMap.put("ingredients", dinner._2.ingredients)
      dinnersList.add(dinnersMap)
    }

    val writer = new StringWriter()
    val template = ve.getTemplate("/src/main/resources/email.vm")
    template.merge(context, writer)
    val body = writer.toString

    MenuMailer.sendMessage(body)
  }

  def getResourceFile(path: String) = {
    val resourcePath = getClass.getResource(path).getPath
    new File(resourcePath)
  }
}
