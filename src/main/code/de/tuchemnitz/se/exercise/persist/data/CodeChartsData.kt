package de.tuchemnitz.se.exercise.persist.data

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.now
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class CodeChartsData(
    @Transient override val savedAt: Instant = now(),
    override val _id: Id<CodeChartsData> = newId(),
    val codeChartsConfig: CodeChartsConfig,
    val originalImageSize: Dimension,
    val scaledImageSize: Dimension,
    val screenSize: Dimension,
    val stringPosition: Interval2D,
    @Contextual val currentUser: UserData
) : IData
