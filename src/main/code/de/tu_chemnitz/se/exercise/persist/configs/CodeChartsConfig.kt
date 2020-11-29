package de.tu_chemnitz.se.exercise.persist.configs

import de.tu_chemnitz.se.exercise.persist.IConfig
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class CodeChartsConfig(
  override val _id: Id<CodeChartsConfig> = newId(),
  val grid: Pair<Int, Int>,
  val pictureViewTime: Int,
  val matrixViewTime: Int
) : IConfig
