package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class CodeChartsConfig(
    @Transient override val _id: Id<CodeChartsConfig> = newId(),
    @Transient override val savedAt: Instant = now(),
    val grid: Pair<Int, Int>,
    val pictureViewTime: Int,
    val matrixViewTime: Int
) : IConfig
