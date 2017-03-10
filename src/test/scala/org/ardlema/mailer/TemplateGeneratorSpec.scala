package org.ardlema.mailer

import java.io.File

import org.ardlema.executor.LunchesPerDay
import org.ardlema.parser.DishParserFromTextFile
import org.ardlema.planner.MenuPlanner
import org.scalatest.{FlatSpec, Matchers}

class TemplateGeneratorSpec extends FlatSpec with Matchers {

  "The template generator" should "fill the template properly" in {
    val lunchesFilePath = "/templatelunches.txt"
    val dinnersFilePath = "/templatedinners.txt"
    val lunchesPath = getClass.getResource(lunchesFilePath).getPath
    val dinnersPath = getClass.getResource(dinnersFilePath).getPath
    val lunches = DishParserFromTextFile.parse(new File(lunchesPath))
    val dinners = DishParserFromTextFile.parse(new File(dinnersPath))
    val plannedLunches = MenuPlanner.planAWeek(lunches, LunchesPerDay.lunchesPerDay)
    val plannedDinners = MenuPlanner.planAWeek(dinners, LunchesPerDay.dinnersPerDay)
    val templateBody = TemplateGenerator.generateTemplate(plannedLunches, plannedDinners)

    templateBody.contains(plannedLunches(0)._2.description) shouldBe(true)
    templateBody.contains(plannedLunches(0)._2.ingredients) shouldBe(true)
  }
}
