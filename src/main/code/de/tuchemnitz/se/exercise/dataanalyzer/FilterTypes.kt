package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.UserData
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class Filter<T>(
    val taken : Boolean,
    val value: T
)
data class UserDataFilter(
    val firstName: Filter<String>,
    val surName: Filter<String>,
    val age: Filter<Int>
) : DataFilters

data class CodeChartsDataFilter(
    val codeChartsConfig: Filter<CodeChartsConfig>,
    val originalImageSize: Filter<Dimension>,
    val scaledImageSize: Filter<Dimension>,
    val screenSize: Filter<Dimension>,
    val stringPosition: Filter<Interval2D>
) : DataFilters

