package org.ardlema.executor

import org.ardlema.planner.{Domingo, _}

object LunchesPerDay {

  val lunchesPerDay = List(
    (Lunes, Legumbres),
    (Martes, Arroz),
    (Miercoles, Verduras),
    (Jueves, Legumbres),
    (Viernes, Pasta),
    (Sabado, Carne),
    (Domingo, Carne))

  val dinnersPerDay = List(
    (Lunes, Carne),
    (Martes, Verduras),
    (Miercoles, Pescado),
    (Jueves, Verduras),
    (Viernes, Otros),
    (Sabado, Otros),
    (Domingo, Detox))
}
