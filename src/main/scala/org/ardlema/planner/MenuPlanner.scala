package org.ardlema.planner

import org.ardlema.parser.Dish

import scala.util.Random

object MenuPlanner {

  def planAWeek(lunches: List[Dish], typesPerDay: List[(WeekDay, DishType)]) = {

    def dishesForAWeek(
                        lunchesToBeSelected: List[Dish],
                        typesPerDay: List[(WeekDay, DishType)],
                        lunchesSelected: List[(WeekDay, Dish)]): List[(WeekDay, Dish)] = {
      if (lunchesSelected.size == WeekDays.weekDays.size) lunchesSelected
      else {
        val random = Random
        val filteredDishes = lunchesToBeSelected.filter(d => d.dishType.identifier.equals(typesPerDay.head._2.identifier))
        val nextDish = random.nextInt(filteredDishes.size)
        val elementSelected = filteredDishes(nextDish)
        val newLunchesToBeSelected = lunchesToBeSelected.filterNot(d => d.description.equals(elementSelected.description))
        dishesForAWeek(newLunchesToBeSelected, typesPerDay.tail, ((typesPerDay.head._1, elementSelected) :: lunchesSelected))
      }
    }

    dishesForAWeek(lunches, typesPerDay, List())
  }
}
