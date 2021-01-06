package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class CodeChartsData(
    override val _id: Id<CodeChartsData> = newId(),
    val codeChartsConfig: CodeChartsConfig,
    val originalImageSize: Dimension,
    val scaledImageSize: Dimension,
    val screenSize: Dimension,
    val stringPosition: Interval2D
) : IData
