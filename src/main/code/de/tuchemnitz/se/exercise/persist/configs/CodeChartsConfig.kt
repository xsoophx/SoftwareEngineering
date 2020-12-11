package de.tuchemnitz.se.exercise.persist.configs

import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

data class CodeChartsConfig(
    override val _id: Id<CodeChartsConfig> = newId(),
    val grid: Pair<Int, Int>,
    val pictureViewTime: Int,
    val matrixViewTime: Int,
    override val savedAt: Instant = Instant.now()
) : IConfig
