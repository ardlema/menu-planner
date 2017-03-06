package org.ardlema.executor

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
      val dishes = DishesFiles.getLunchesFile(commandLineParams.get)
        if (bothDinnersAndLunchesFileExist(dishes)) {
          val lunches = DishParserFromTextFile.parse(dishes.lunchesFile.get)
          val dinners = DishParserFromTextFile.parse(dishes.dinnersFile.get)
          val previousLunches = if (dishes.previousLunchesFile.isDefined) {
            Some(DishParserFromTextFile.parse(dishes.previousLunchesFile.get))
          } else None
          val previousDinners = if (dishes.previousDinnersFile.isDefined) {
            Some(DishParserFromTextFile.parse(dishes.previousDinnersFile.get))
          } else None
          val plannedLunches = MenuPlanner.planAWeek(lunches, LunchesPerDay.lunchesPerDay, previousLunches)
          val plannedDinners = MenuPlanner.planAWeek(dinners, LunchesPerDay.dinnersPerDay, previousDinners)
          logger.info("Generating email template...")
          val mailBody = TemplateGenerator.generateTemplate(plannedLunches, plannedDinners)
          MenuMailer.sendMessage(mailBody, commandLineParams.get)
      } else {
          logger.error("Dinners and lunches files not found!!")
      }
    } else {
      logger.error("Wrong command line parameters. Please review the list of required params!!")
    }
  }

  def bothDinnersAndLunchesFileExist(dishes: DishesFiles): Boolean = {
    dishes.lunchesFile.isDefined && dishes.dinnersFile.isDefined
  }
}
