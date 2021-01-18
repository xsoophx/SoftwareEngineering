package de.tuchemnitz.se.exercise.codecharts

import javafx.beans.Observable
import javafx.beans.property.Property
import javafx.geometry.Rectangle2D
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf

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
    val neededClicks: Int,

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

@Serializable
data class ClickCounter(
    val clickList: MutableList<Int>,
    val recursionCounter: Int = 0,
    @Contextual
    var viewPort: Rectangle2D
)
