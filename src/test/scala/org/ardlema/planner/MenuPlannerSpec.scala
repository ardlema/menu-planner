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
    val typesPerDay = List(
      (Lunes, Legumbres),
      (Martes, Arroz),
      (Miercoles, Verduras),
      (Jueves, Legumbres),
      (Viernes, Verduras),
      (Sabado, Carne),
      (Domingo, Carne))
    val plannedLunches: List[(WeekDay, Dish)] = MenuPlanner.planAWeek(lunches, typesPerDay)

    plannedLunches should contain oneOf(
      (Lunes, Dish("Garbanzos", "Tomate,Garbanzos", Legumbres)),
      (Lunes, Dish("Lentejas", "Lentejas,Zanahorias", Legumbres)))
    plannedLunches should contain(Martes, Dish("Arroz", "Arroz", Arroz))
    plannedLunches should contain oneOf(
      (Miercoles, Dish("Pisto", "Tomate,Calabacin,Pimiento Rojo", Verduras)),
      (Miercoles, Dish("Pure de calabaza", "Calabaza,Patatas", Verduras)))
    plannedLunches should contain oneOf(
      (Jueves, Dish("Garbanzos", "Tomate,Garbanzos", Legumbres)),
      (Jueves, Dish("Lentejas", "Lentejas,Zanahorias", Legumbres)))
    plannedLunches should contain oneOf(
      (Viernes, Dish("Pisto", "Tomate,Calabacin,Pimiento Rojo", Verduras)),
      (Viernes, Dish("Pure de calabaza", "Calabaza,Patatas", Verduras)))
    plannedLunches should contain oneOf (
      (Sabado, Dish("Carne estofada", "Carne,Patatas", Carne)),
      (Sabado, Dish("Costillas", "Costillas", Carne)))
    plannedLunches should contain oneOf(
      (Domingo, Dish("Carne estofada", "Carne,Patatas", Carne)),
      (Domingo, Dish("Costillas", "Costillas", Carne)))
  }

  //TODO: Wrong path tests!!
}

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



