name := "menu-planner"

organization := "org.ardlema"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "javax.mail" % "mail" % "1.4.7",
  "org.apache.velocity" % "velocity" % "1.7",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)

coverageMinimum := 70

coverageFailOnMinimum := false

coverageHighlighting := true

