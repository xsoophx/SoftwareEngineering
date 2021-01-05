package de.tuchemnitz.se.exercise.codecharts


/**
 * Two dimensional values, used for picture, screen and grid.
 */
data class Dimension(
    val x: Double,
    val y: Double,
)

/**
 * Three boolean values used for allowedCharacters where true is enabled and false is not enabled.
 */
data class StringCharacters(
    val upperCase: Boolean,
    val lowerCase: Boolean,
    val numbers: Boolean
)

/**
 * Two dimensional interval used to save area of screen (in pixels) the user looked at.
 */
data class Interval2D(
    val xMin: Double,
    val xMax: Double,
    val yMin: Double,
    val yMax: Double,
)