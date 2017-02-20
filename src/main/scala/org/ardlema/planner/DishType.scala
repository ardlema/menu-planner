package org.ardlema.planner

sealed trait DishType {

  def identifier: String
}

object DishType {

  def fromString(identifier: String): DishType = identifier match {
    case "L" => Legumbres
    case "A" => Arroz
    case "V" => Verduras
    case "C" => Carne
  }
}
case object Legumbres extends DishType { val identifier = "L" }
case object Arroz extends DishType { val identifier = "A" }
case object Verduras extends DishType { val identifier = "V" }
case object Carne extends DishType { val identifier = "C" }
