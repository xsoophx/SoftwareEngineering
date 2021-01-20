package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension

data class Coordinates(
    val xStart: Double,
    val yStart: Double,
    val width: Double,
    val height: Double,
    // val picturePath: Path,
    val picturePath: String,
    val scaledImageSize: Dimension

)

data class CoordinatesZoomMaps(
    // val picturePath: String,
    // val scaledImageSize: Dimension,
    val zoomPosition: javafx.geometry.Point2D,
    val radius: Int
)

class CodeCharts : ITool

class ZoomMaps : ITool
