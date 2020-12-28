package de.tuchemnitz.se.exercise.codecharts

class CodeChartsDataValues {
    private lateinit var imagePath: String
    private lateinit var originalImageSize: Dimension
    private lateinit var scaledImageSize: Dimension
    private lateinit var screenSize: Dimension
    private lateinit var gridDimension: Dimension
    private lateinit var allowedChars: StringCharacters

    fun setImagePath(path: String) {
        imagePath = path
    }

    fun getImagePath(): String {
        return imagePath
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

    fun setAllowedChars(charset: StringCharacters) {
        allowedChars = charset
    }

    fun getAllowedChars(): StringCharacters{
        return allowedChars
    }
}
