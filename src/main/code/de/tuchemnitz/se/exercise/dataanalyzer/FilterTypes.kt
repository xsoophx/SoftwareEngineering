package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.data.Gender
import javafx.scene.input.KeyCode

data class Filter<T>(
    val taken: Boolean,
    val value: T
)

data class Age(
    val minimumAge: Int?,
    val maximumAge: Int?
)

data class UserDataFilter(
    val firstName: Filter<String>,
    val lastName: Filter<String>,
    val age: Filter<Age?>,
    val gender: Filter<Gender?>
)

data class CodeChartsDataFilter(
    val pictureViewTime: Filter<Int>,
    val matrixViewTime: Filter<Int>,
    val imagePath: Filter<String>
)

data class ZoomMapsDataFilter(
    val keyCode: Filter<KeyCode>,
    val imagePath: Filter<String>
)
