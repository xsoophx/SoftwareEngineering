package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.scene.input.KeyCode
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class Filter<T>(
    val taken: Boolean,
    val value: T
)

enum class Gender {
    Male,
    Female,
    Other
}

enum class ActiveTools {
    CodeCharts,
    ZoomMaps
}

enum class Method {
    Heatmap,
    Diagram
}

data class Age(
    val minimumAge: Int,
    val maximumAge: Int
)

data class UserDataFilter(
    val firstName: Filter<String>,
    val lastName: Filter<String>,
    val age: Filter<Age?>,
    val gender: Filter<Gender?>
) : DataFilters

data class CodeChartsDataFilter(
    val pictureViewTime: Filter<Int>,
    val matrixViewTime: Filter<Int>,
)

data class PictureDataFilter(
    val imagePath: Filter<String>
) : DataFilters

data class ZoomMapsDataFilter(
    val keyCode: Filter<KeyCode>
)

