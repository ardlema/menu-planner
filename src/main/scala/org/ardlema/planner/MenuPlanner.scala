package org.ardlema.planner

import org.ardlema.parser.Dish

import scala.util.Random

object MenuPlanner {

  def planAWeek(dishes: List[Dish], typesPerDay: List[(WeekDay, DishType)]) = {

    def dishesForAWeek(
                        dishesToBeSelected: List[Dish],
                        typesPerDay: List[(WeekDay, DishType)],
                        dishesSelected: List[(WeekDay, Dish)]): List[(WeekDay, Dish)] = {
      if (dishesSelected.size == WeekDays.weekDays.size) dishesSelected
      else {
        val random = Random
        val filteredDishes = dishesToBeSelected.filter(d => d.dishType.identifier.equals(typesPerDay.head._2.identifier))
        val nextDish = random.nextInt(filteredDishes.size)
        val elementSelected = filteredDishes(nextDish)
        val newLunchesToBeSelected = dishesToBeSelected.filterNot(d => d.description.equals(elementSelected.description))
        dishesForAWeek(newLunchesToBeSelected, typesPerDay.tail, ((typesPerDay.head._1, elementSelected) :: dishesSelected))
      }
    }

    dishesForAWeek(dishes, typesPerDay, List())
  }
}
