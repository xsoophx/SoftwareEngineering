package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension

/**
 * Holds the information for depicting data from the database, which was created by data of the tools.
 */
data class Coordinates(
    val xStart: Double,
    val yStart: Double,
    val xEnd: Double,
    val yEnd: Double,
    val picturePath: String,
    val scaledImageSize: Dimension
)
