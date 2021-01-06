package de.tuchemnitz.se.exercise.codecharts

import kotlinx.serialization.Serializable

data class CodeChartsValues(
    // picture
    val imagePath: String,
    var originalImageSize: Dimension,
    var scaledImageSize: Dimension,
    var screenSize: Dimension,

    // grid
    val gridDimension: Dimension,
    val matrixViewTime: Double,
    val pictureViewTime: Double,
    val relative: Boolean = false,
    val recursionDepth: Int = 0,

    // strings
    val allowedChars: StringCharacters,
    val sorted: Boolean = false,

    // userInput
    var eyePos: Interval2D
)

@Serializable
data class Dimension(
    var x: Double,
    var y: Double
)

@Serializable
data class StringCharacters(
    val upperCase: Boolean,
    val lowerCase: Boolean,
    val numbers: Boolean
)

@Serializable
data class Interval2D(
    val xMin: Double,
    val xMax: Double,
    val yMin: Double,
    val yMax: Double
)
