package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
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
    val lastName: Filter<String>,
    val ageRangeLower: Filter<Int>,
    val ageRangeUpper: Filter<Int>,
    val gender: Filter<String>
) : DataFilters

data class CodeChartsDataFilter(
    val originalImageSize: Filter<Dimension>,
    val scaledImageSize: Filter<Dimension>,
    val screenSize: Filter<Dimension>,
    val stringPosition: Filter<Interval2D>
) : DataFilters

data class CodeChartsConfigFilter(
    val minViewsToSubdivide: Filter<Int>,
    val stringCharacters: Filter<StringCharacters>,
    val pictures: Filter<PictureData>
): DataFilters

data class PictureDataFilter(
    val imagePath: Filter<String>,
    val grid: Filter<Grid>,
    val pictureViewTime: Filter<Int>,
    val matrixViewTime: Filter<Int>,
    val ordered: Filter<Boolean>,
    val relative: Filter<Boolean>,
    val maxRecursionDepth: Filter<Int>
): DataFilters

data class GridFilter(
    val width: Filter<Int>,
    val height: Filter<Int>
): DataFilters


