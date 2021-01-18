package de.tuchemnitz.se.exercise.codecharts

import javafx.beans.Observable
import javafx.beans.value.ObservableValue
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image

const val IMAGE_PATH = "/Chameleon.jpg"
const val GRID_WIDTH = 10
const val GRID_HEIGHT = 10
const val MATRIX_VIEW_TIME = 5.0
const val PICTURE_VIEW_TIME = 5.0

object CodeChartsTool {

    private val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)
    private val gridDimension = Dimension(x = GRID_WIDTH.toDouble(), y = GRID_HEIGHT.toDouble())
    val codeChartsData = CodeChartsValues(
        gridDimension = gridDimension,
        allowedChars = allowedCharacters,
        imagePath = IMAGE_PATH,
        matrixViewTime = MATRIX_VIEW_TIME,
        pictureViewTime = PICTURE_VIEW_TIME,
        sorted = true,
        eyePos = Interval2D(0.0, 0.0, 0.0, 0.0),
        originalImageSize = Dimension(0.0, 0.0),
        scaledImageSize = Dimension(0.0, 0.0),
        screenSize = Dimension(0.0, 0.0),
        relative = true,
        recursionDepth = 2,
        neededClicks = 1,
    )
    val codeChartsStringHandler = CodeChartsStringHandler().apply {
        // generate needed number of Strings
        val gridWidth = codeChartsData.gridDimension.x
        val gridHeight = codeChartsData.gridDimension.y
        val gridSize = (gridWidth * gridHeight).toInt()
        setStrings(
            input = gridSize,
            allowedChars = codeChartsData.allowedChars
        )
        if (codeChartsData.sorted) {
            orderList()
        }
    }
    val codeChartsClickCounter = ClickCounter(
        clickList = MutableList((codeChartsData.gridDimension.x * codeChartsData.gridDimension.y).toInt()) { 0 },
        viewPort = Rectangle2D (
            0.0,
            0.0,
            1000.0,
            1000.0
            // Image(codeChartsData.imagePath).width,
            // Image(codeChartsData.imagePath).height
        )
    )
}
