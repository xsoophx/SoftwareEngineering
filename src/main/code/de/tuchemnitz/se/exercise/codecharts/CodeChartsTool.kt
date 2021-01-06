package de.tuchemnitz.se.exercise.codecharts

const val IMAGE_PATH = "/Chameleon.jpg"
const val GRID_WIDTH = 50
const val GRID_HEIGHT = 50
const val MATRIX_VIEW_TIME = 2.0
const val PICTURE_VIEW_TIME = 2.0

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
        screenSize = Dimension(0.0, 0.0)
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
}
