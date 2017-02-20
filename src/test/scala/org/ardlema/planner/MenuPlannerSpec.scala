package org.ardlema.planner

import scala.util._

import java.io.File

import org.ardlema.parser.{Dish, DishParserFromTextFile}
import org.scalatest.{FlatSpec, Matchers}

class MenuPlannerSpec extends FlatSpec with Matchers {
  "The menu planner" should "create a plan for a whole week" in {
    val path = getClass.getResource("/lunchesforaweek.txt").getPath
    val lunchesFile = new File(path)
    val lunches = DishParserFromTextFile.parse(lunchesFile)
    val plannedLunches = MenuPlanner.planAWeek(lunches)

    plannedLunches should contain(Dish("Garbanzos","Tomate,Garbanzos"))
  }

  //TODO: Wrong path tests!!
}

object MenuPlanner {

  def planAWeek(lunches: List[Dish]) = {

    def dishesForAWeek(lunchesToBeSelected: List[Dish], lunchesSelected: List[Dish]): List[Dish] = {
      if (lunchesSelected.size == WeekDays.weekDays.size) lunchesSelected
      else {
        val random = Random
        val nextDish = random.nextInt(lunchesToBeSelected.size)
        val elementSelected = lunchesToBeSelected(nextDish)
        val newLunchesToBeSelected = lunchesToBeSelected.take(nextDish - 1) ++ lunchesToBeSelected.drop(nextDish)
        dishesForAWeek(newLunchesToBeSelected, elementSelected :: lunchesSelected)
      }

    }


    dishesForAWeek(lunches, List())
  }
}



