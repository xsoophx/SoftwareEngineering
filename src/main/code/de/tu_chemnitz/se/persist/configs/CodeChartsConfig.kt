package de.tu_chemnitz.se.persist.configs

import de.tu_chemnitz.se.persist.IConfig

data class CodeChartsConfig(
  val grid: Pair<Int, Int>,
  val pictureViewTime: Int,
  val matrixViewTime: Int
) : IConfig
