package de.tuchemnitz.se.exercise.persist.configs

import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class CodeChartsConfig(
    @Transient override val _id: Id<CodeChartsConfig> = newId(),
    @Transient override val savedAt: Instant = now(),
    val matrixViewTime: Int,
    val minViewsToSubdivide: Int,
    val ordered: Boolean,
    val stringCharacters: StringCharacters,
    val pictures: List<PictureData>
) : IConfig

@Serializable
data class PictureData(
    @SerialName("name") val imagePath: String,
    val grid: Grid,
    val pictureViewTime: Int,
    val relative: Boolean,
    val maxRecursionDepth: Int
)

@Serializable
data class Grid(
    val width: Int,
    val height: Int
)
