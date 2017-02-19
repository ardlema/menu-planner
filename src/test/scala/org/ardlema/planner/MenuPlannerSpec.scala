package org.ardlema.planner

import java.io.File

import org.ardlema.parser.{Dish, DishParserFromTextFile}
import org.scalatest.{FlatSpec, Matchers}

class MenuPlannerSpec extends FlatSpec with Matchers {
  "The menu planner" should "create a plan for a whole week" in {
    val path = getClass.getResource("/lunchesforaweek.txt").getPath
    val lunchesFile = new File(path)
    val lunches = DishParserFromTextFile.parse(lunchesFile)
    val plannedLunches: Map[WeekDay, Dish] = MenuPlanner.planAWeek(lunches)
    plannedLunches.values should contain(Dish("Garbanzos","Tomate,Garbanzos"))
  }

  //TODO: Wrong path tests!!
}

object MenuPlanner {

  def planAWeek(lunches: List[Dish]): Map[WeekDay, Dish] = {
    Map()
  }
}



