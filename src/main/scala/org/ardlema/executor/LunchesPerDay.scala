package org.ardlema.executor

import org.ardlema.planner.{Domingo, _}

object LunchesPerDay {

  val lunchesPerDay = List(
    (Lunes, Legumbres),
    (Martes, Verduras),
    (Miercoles, Cereales),
    (Jueves, Verduras),
    (Viernes, Cereales),
    (Sabado, Otros),
    (Domingo, Carne))

  val dinnersPerDay = List(
    (Lunes, Verduras),
    (Martes, Huevos),
    (Miercoles, Pescado),
    (Jueves, Carne),
    (Viernes, Otros),
    (Sabado, Otros),
    (Domingo, Detox))
}
