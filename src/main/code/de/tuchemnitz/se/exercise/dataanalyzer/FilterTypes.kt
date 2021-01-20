package de.tuchemnitz.se.exercise.dataanalyzer

import javafx.scene.input.KeyCode

data class Filter<T>(
    val taken: Boolean,
    val value: T
)

data class Gender(
    val male: Boolean,
    val female: Boolean,
    val other: Boolean
)

data class Age(
    val minimumAge: Int,
    val maximumAge: Int
)
data class UserDataFilter(
    val firstName: Filter<String>,
    val lastName: Filter<String>,
    val age: Filter<Age>,
    val gender: Filter<Gender>
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
