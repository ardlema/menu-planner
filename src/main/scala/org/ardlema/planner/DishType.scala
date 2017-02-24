package org.ardlema.planner

sealed trait DishType {

  def identifier: String
}

object DishType {

  def fromString(identifier: String): DishType = identifier match {
    case "L" => Legumbres
    case "CE" => Cereales
    case "V" => Verduras
    case "C" => Carne
    case "P" => Pescado
    case "D" => Detox
    case "PA" => Pasta
    case "H" => Huevos
    case "O" => Otros
  }
}
case object Legumbres extends DishType { val identifier = "L" }
case object Cereales extends DishType { val identifier = "CE" }
case object Verduras extends DishType { val identifier = "V" }
case object Carne extends DishType { val identifier = "C" }
case object Pescado extends DishType { val identifier = "P" }
case object Detox extends DishType { val identifier = "D" }
case object Pasta extends DishType { val identifier = "PA" }
case object Huevos extends DishType { val identifier = "H" }
case object Otros extends DishType { val identifier = "O" }
