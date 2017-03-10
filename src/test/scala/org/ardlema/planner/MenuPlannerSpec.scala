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
      (Martes, Cereales),
      (Miercoles, Verduras),
      (Jueves, Legumbres),
      (Viernes, Verduras),
      (Sabado, Carne),
      (Domingo, Carne))
    val plannedLunches: List[(WeekDay, Dish)] = MenuPlanner.planAWeek(lunches, typesPerDay)

    plannedLunches should contain oneOf(
      (Lunes, Dish("Garbanzos", "Tomate,Garbanzos", Legumbres)),
      (Lunes, Dish("Lentejas", "Lentejas,Zanahorias", Legumbres)))
    plannedLunches should contain(Martes, Dish("Arroz", "Arroz", Cereales))
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
      (Martes, Cereales),
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

  it should "not include dishes from the previous week" in {
    val previousLunchesForAWeekPath = getClass.getResource("/lunchesforaweek.txt").getPath
    val lunchesWithTwoDishesPath = getClass.getResource("/luncheswithtwodishes.txt").getPath
    val previousLunchesFile = new File(previousLunchesForAWeekPath)
    val lunchesFile = new File(lunchesWithTwoDishesPath)
    val previousLunches = DishParserFromTextFile.parse(previousLunchesFile)
    val lunches = DishParserFromTextFile.parse(lunchesFile)
    val typesPerDay = List(
      (Lunes, Legumbres),
      (Martes, Cereales),
      (Miercoles, Verduras),
      (Jueves, Legumbres),
      (Viernes, Verduras),
      (Sabado, Carne),
      (Domingo, Carne))
    val plannedLunches = MenuPlanner.planAWeek(lunches, typesPerDay, Some(previousLunches))

    plannedLunches should contain noneOf (
      (Lunes, Dish("Garbanzos","Tomate,Garbanzos", Legumbres)),
      (Jueves, Dish("Lentejas","Lentejas,Zanahorias", Legumbres)))
    plannedLunches should contain oneOf (
      (Lunes, Dish("Quinoa","Quinoa, tomate", Legumbres)),
      (Jueves, Dish("Quinoa","Quinoa, tomate", Legumbres)))
    plannedLunches should not contain (Martes, Dish("Arroz","Arroz", Cereales))
    plannedLunches should contain (Martes, Dish("Cereal raro","Raruno", Cereales))
    plannedLunches should contain noneOf (
      (Miercoles, Dish("Pisto","Tomate,Calabacin,Pimiento Rojo", Verduras)),
      (Miercoles, Dish("Pure de calabaza","Calabaza,Patatas", Verduras)))
    plannedLunches should contain oneOf (
      (Miercoles, Dish("Menestra","Pimiento rojo, pimiento verde", Verduras)),
      (Miercoles, Dish("Guisantes","Guisantes, jamon", Verduras)))
    plannedLunches should contain oneOf (
      (Lunes, Dish("Judias","Judias,chorizo", Legumbres)),
      (Jueves, Dish("Judias","Judias,chorizo", Legumbres)))
    plannedLunches should contain oneOf (
      (Miercoles, Dish("Guisantes","Guisantes, jamon", Verduras)),
      (Viernes, Dish("Guisantes","Guisantes, jamon", Verduras)))
    plannedLunches should contain oneOf (
      (Sabado, Dish("Filetes","Filetes de ternera", Carne)),
      (Domingo, Dish("Filetes","Filetes de ternera", Carne)))
    plannedLunches should contain oneOf (
      (Sabado, Dish("Chuletas","Chuletas", Carne)),
      (Domingo, Dish("Chuletas","Chuletas", Carne)))
  }
  //TODO: Wrong path tests!!
}