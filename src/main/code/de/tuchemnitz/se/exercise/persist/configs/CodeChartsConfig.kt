package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class CodeChartsConfig(
    override val _id: Id<CodeChartsConfig> = newId(),
    val grid: Pair<Int, Int>,
    val pictureViewTime: Int,
    val matrixViewTime: Int,
    @ContextualSerialization override val savedAt: Instant = now()
) : IConfig
