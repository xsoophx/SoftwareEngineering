package de.tuchemnitz.se.exercise.dataanalyzer

import java.nio.file.Path

data class Coordinates(
    val xStart: Double,
    val yStart: Double,
    val xEnd: Double,
    val yEnd: Double,
    //val picturePath: Path,
    val picturePath: String,
    val scaledImageSize: Dimension

)

class CodeCharts : ITool

class ZoomMaps : ITool


