package org.ardlema.planner

import org.ardlema.parser.Dish

import scala.annotation.tailrec
import scala.util.Random

object MenuPlanner {

  def planAWeek(dishes: List[Dish], typesPerDay: List[(WeekDay, DishType)], previousDishes: Option[List[Dish]] = None) = {

    @tailrec
    def dishesForAWeek(
                        dishesToBeSelected: List[Dish],
                        typesPerDay: List[(WeekDay, DishType)],
                        dishesSelected: List[(WeekDay, Dish)]): List[(WeekDay, Dish)] = {
      if (dishesSelected.size == WeekDays.weekDays.size) dishesSelected.reverse
      else {
        val random = Random
        val filteredDishes = filterDishes(dishesToBeSelected, previousDishes, typesPerDay.head._2)
        val nextDish = random.nextInt(filteredDishes.size)
        val elementSelected = filteredDishes(nextDish)
        val newLunchesToBeSelected = dishesToBeSelected.filterNot(d => d.description.equals(elementSelected.description))
        dishesForAWeek(
          newLunchesToBeSelected,
          typesPerDay.tail,
          ((typesPerDay.head._1, elementSelected) :: dishesSelected))
      }
    }

    def filterDishes(initialDishes: List[Dish], previousDishes: Option[List[Dish]], dishType: DishType) = {
      val filteredDishes = initialDishes.filter(d => d.dishType.identifier.equals(dishType.identifier))
      if (previousDishes.isDefined) {
        val previousDishesPerType = previousDishes.get.filter(d => d.dishType.equals(dishType))
        val filtered = for {
          filteredDish <- filteredDishes;
          if (!previousDishesPerType.contains(filteredDish))
        } yield (filteredDish)
        filtered
      }
      else {
        filteredDishes
      }
    }

    dishesForAWeek(dishes, typesPerDay, List())
  }
}
