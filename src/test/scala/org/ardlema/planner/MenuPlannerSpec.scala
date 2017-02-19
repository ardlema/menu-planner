package org.ardlema.planner

import java.io.File

import org.ardlema.parser.Dish
import org.scalatest.{FlatSpec, Matchers}

class MenuPlannerSpec extends FlatSpec with Matchers {
  "The menu planner" should "create a plan for a whole week" in {
    val path = getClass.getResource("/lunchesforaweek.txt").getPath
    val lunchesFile = new File(path)
    val plannedLunches: Map[WeekDay, Dish] = MenuPlanner.planAWeek(lunchesFile)
    plannedLunches.values should contain(Dish("Garbanzos","Tomate,Garbanzos"))
  }

  //TODO: Wrong path tests!!
}

object MenuPlanner {

  def planAWeek(file: File): Map[WeekDay, Dish] = {
    Map()
  }
}



