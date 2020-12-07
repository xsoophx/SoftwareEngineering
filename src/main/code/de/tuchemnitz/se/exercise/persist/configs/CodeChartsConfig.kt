package de.tuchemnitz.se.exercise.persist.configs

import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class CodeChartsConfig(
  override val _id: Id<CodeChartsConfig> = newId(),
  val grid: Pair<Int, Int>,
  val pictureViewTime: Int,
  val matrixViewTime: Int
) : IConfig
