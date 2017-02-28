name := "menu-planner"

organization := "org.ardlema"

version := "0.0.1"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "javax.mail" % "mail" % "1.4.7",
  "org.apache.velocity" % "velocity" % "1.7",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)

initialCommands := "import org.ardlema.menuplanner._"

