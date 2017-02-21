package org.ardlema.planner

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

  it should "maintain the order of the dishes" in {
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

    plannedLunches(0)._1 should be(Lunes)
    plannedLunches(1)._1 should be(Martes)
    plannedLunches(2)._1 should be(Miercoles)
    plannedLunches(3)._1 should be(Jueves)
    plannedLunches(4)._1 should be(Viernes)
    plannedLunches(5)._1 should be(Sabado)
    plannedLunches(6)._1 should be(Domingo)
  }
  //TODO: Wrong path tests!!
}