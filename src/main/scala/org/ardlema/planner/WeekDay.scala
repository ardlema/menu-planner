package org.ardlema.planner

sealed trait WeekDay { def name: String }
case object Lunes extends WeekDay { val name = "Lunes" }
case object Martes extends WeekDay { val name = "Martes" }
case object Miercoles extends WeekDay { val name = "Miercoles" }
case object Jueves extends WeekDay { val name = "Jueves" }
case object Viernes extends WeekDay { val name = "Viernes" }
case object Sabado extends WeekDay { val name = "Sabado" }
case object Domingo extends WeekDay { val name = "Domingo" }
