package org.ardlema.executor

import java.io.File

import com.typesafe.scalalogging.LazyLogging
import org.ardlema.mailer.{MenuMailer, TemplateGenerator}
import org.ardlema.parser.{CommandLineParser, DishParserFromTextFile}
import org.ardlema.planner.MenuPlanner

object Executor extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info("Starting the menu planner execution...")
    logger.info("Parsing command line parameters")
    val commandLineParams = CommandLineParser.parse(args)
    if (commandLineParams.isDefined) {
      logger.info("Command line parameters parsed fine")
      logger.info("Starting the menu planner execution...")
      val lunchesFilePath = s"""${commandLineParams.get.rootPath}${DishParserFromTextFile.lunchesTextFile}"""
      val dinnersFilePath = s"""${commandLineParams.get.rootPath}${DishParserFromTextFile.dinnersTextFile}"""
      val lunches = DishParserFromTextFile.parse(new File(lunchesFilePath))
      val dinners = DishParserFromTextFile.parse(new File(dinnersFilePath))
      val plannedLunches = MenuPlanner.planAWeek(lunches, LunchesPerDay.lunchesPerDay)
      val plannedDinners = MenuPlanner.planAWeek(dinners, LunchesPerDay.dinnersPerDay)
      logger.info("Generating email template...")
      val mailBody = TemplateGenerator.generateTemplate(plannedLunches, plannedDinners)

      MenuMailer.sendMessage(mailBody, commandLineParams.get)
    } else {
      logger.error("Wrong command line parameters. Please review the list of required params!!")
    }
  }
}
