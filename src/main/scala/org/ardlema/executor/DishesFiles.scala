package org.ardlema.executor

import java.io.File

import org.ardlema.parser.{CommandLineParams, DishParserFromTextFile}

case class DishesFiles(
                        lunchesFile: Option[File],
                        dinnersFile: Option[File],
                        previousLunchesFile: Option[File],
                        previousDinnersFile: Option[File])

object DishesFiles {

  def getLunchesFile(commandLineParams: CommandLineParams): DishesFiles = {
    val lunchesFilePath = s"""${commandLineParams.rootPath}${DishParserFromTextFile.lunchesTextFile}"""
    val previousLunchesFilePath = s"""${commandLineParams.previousRootPath}${DishParserFromTextFile.lunchesTextFile}"""
    val dinnersFilePath = s"""${commandLineParams.rootPath}${DishParserFromTextFile.dinnersTextFile}"""
    val previousDinnersFilePath = s"""${commandLineParams.previousRootPath}${DishParserFromTextFile.dinnersTextFile}"""
    val lunchesFile = SafeFileHandler.getFile(lunchesFilePath)
    val dinnersFile = SafeFileHandler.getFile(dinnersFilePath)
    val previousLunchesFile = SafeFileHandler.getFile(previousLunchesFilePath)
    val previousDinnersFile = SafeFileHandler.getFile(previousDinnersFilePath)
    DishesFiles(lunchesFile, dinnersFile, previousLunchesFile, previousDinnersFile)
  }
}
