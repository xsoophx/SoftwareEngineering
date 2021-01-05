package de.tuchemnitz.se.exercise.codecharts

/**
 * Contains all relevant data for CodeCharts classes and getter and setter methods.
 */
class CodeChartsDataValues {

    // picture
    private lateinit var imagePath: String
    private var pictureViewTime: Double = 0.0
    private lateinit var originalImageSize: Dimension
    private lateinit var scaledImageSize: Dimension
    private lateinit var screenSize: Dimension

    // grid
    private lateinit var gridDimension: Dimension
    private var matrixViewTime: Double = 0.0
    private var relative: Boolean = false
    private var recursionDepth: Int = 0

    // strings
    private lateinit var allowedChars: StringCharacters
    private var toOrder: Boolean = false

    // userInput
    private lateinit var eyePos: Interval2D

    fun setImagePath(path: String) {
        imagePath = path
    }

    fun getImagePath(): String {
        return imagePath
    }

    fun setPictureViewTime(viewTime: Double) {
        pictureViewTime = viewTime
    }

    fun getPictureViewTime(): Double {
        return pictureViewTime
    }

    fun setOriginalImageSize(size: Dimension) {
        originalImageSize = size
    }

    fun getOriginalImageSize(): Dimension {
        return originalImageSize
    }

    fun setScaledImageSize(size: Dimension) {
        scaledImageSize = size
    }

    fun getScaledImageSize(): Dimension {
        return scaledImageSize
    }

    fun setScreenSize(size: Dimension) {
        screenSize = size
    }

    fun getScreenSize(): Dimension {
        return screenSize
    }

    fun setGridDimension(dimension: Dimension) {
        gridDimension = dimension
    }

    fun getGridDimension(): Dimension {
        return gridDimension
    }

    fun setMatrixViewTime(viewTime: Double) {
        matrixViewTime = viewTime
    }

    fun getMatrixViewTime(): Double {
        return matrixViewTime
    }

    fun setRelative(rel: Boolean) {
        relative = rel
    }

    fun getRelative(): Boolean {
        return relative
    }

    fun setRecursionDepth(depth: Int) {
        recursionDepth = depth
    }

    fun getRecursionDepth(): Int {
        return recursionDepth
    }

    fun setAllowedChars(charset: StringCharacters) {
        allowedChars = charset
    }

    fun getAllowedChars(): StringCharacters {
        return allowedChars
    }

    fun setToOrder(orderStrings: Boolean) {
        toOrder = orderStrings
    }

    fun getToOrder(): Boolean {
        return toOrder
    }

    fun setEyePos(pos: Interval2D) {
        eyePos = pos
    }

    fun getEyePos(): Interval2D {
        return eyePos
    }
}

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
