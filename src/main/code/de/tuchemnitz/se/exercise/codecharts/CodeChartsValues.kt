package de.tuchemnitz.se.exercise.codecharts

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import de.tuchemnitz.se.exercise.persist.Point2DSerializer
import javafx.geometry.Point2D
import kotlinx.serialization.Serializable

data class CodeChartsValues(
    /**
     * All picture values.
     * [imagePath] contains storage path of images to be loaded.
     * [originalImageSize] contains width and height of loaded image in pixels.
     * [scaledImageSize] contains width and height of image after scaling it to user's screen.
     * [screenSize] size of users' screen which is used to scale image.
     * [pictureViewTime] contains time the image is shown in milliseconds.
     */
    val imagePath: String,
    var originalImageSize: Dimension,
    var scaledImageSize: Dimension,
    var screenSize: Dimension,
    val pictureViewTime: Int,

    /**
     * All grid values.
     * [gridDimension] contains number of grid cells in width and height dimension.
     * [matrixViewTime] contains time the grid is shown to the user in milliseconds.
     * [relative] defines whether an area of the image will have to be requested more detailed.
     * [recursionDepth] defines how often the corresponding area will be resized.
     */
    val gridDimension: Dimension,
    val matrixViewTime: Int,
    val relative: Boolean = false,
    val recursionDepth: Int = 0,

    /**
     * [allowedChars] contains a bool representation of all characters that are allowed to generate random strings for grid.
     * [sorted] defines whether the strings will be shown sorted naturally in grid.
     */
    val allowedChars: StringCharacters,
    val sorted: Boolean = false,

    /**
     * [eyePos] contains calculated grid cell the user looked at.
     */
    var eyePos: Interval2D
)

/**
 * [Dimension] contains expanse in width and height dimension where x corresponds to width and y to height.
 */
@Serializable
data class Dimension(
    var x: Double,
    var y: Double
)

/**
 * [StringCharacters] is a bool representation of all characters that are allowed to be used in generated strings for grid.
 * True means that it is allowed to use characters of the corresponding group and false means it is not allowed.
 */
@Serializable
data class StringCharacters(
    val upperCase: Boolean,
    val lowerCase: Boolean,
    val numbers: Boolean
)

/**
 * [Interval2D] contains minimal and maximal width and height values to define a rectangle.
 */
@Serializable

data class Interval2D(
    @JsonDeserialize(using = Point2DSerializer::class)
    @Serializable(with = Point2DSerializer::class)
    val minimum: Point2D,
    @JsonDeserialize(using = Point2DSerializer::class)
    @Serializable(with = Point2DSerializer::class)
    val maximum: Point2D
)

@Serializable
data class ClickCounter(
    var clickList: MutableList<Int>,
    var recursionCounter: Int = 0,
    @Contextual
    var pictureImageView: ImageView,
    @Contextual
    var viewPort: Rectangle2D,
    val recurseAt: Int,
)
