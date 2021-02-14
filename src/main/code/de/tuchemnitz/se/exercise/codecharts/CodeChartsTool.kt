package de.tuchemnitz.se.exercise.codecharts

import javafx.geometry.Rectangle2D
import javafx.scene.image.ImageView

/**
 * Dummy values that we will replace later.
 */
const val IMAGE_PATH = "/Chameleon.jpg"
const val GRID_WIDTH = 10
const val GRID_HEIGHT = 10
const val MATRIX_VIEW_TIME = 5.0
const val PICTURE_VIEW_TIME = 5.0

object CodeChartsTool {
    /**
     * [allowedCharacters] describes characters that can be used to generate random strings for grid
     */
    private val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)

    /**
     * [gridDimension] contains number of fields in height and width direction.
     */
    private val gridDimension = Dimension(x = GRID_WIDTH.toDouble(), y = GRID_HEIGHT.toDouble())

    /**
     * [codeChartsData] contains all needed values to execute CodeCharts.
     * More Information can be found in [CodeChartsValues]
     */
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

    /**
     * [codeChartsStringHandler]: StringHandler holds and generates Strings. Able to validate whether given String is element of generated Strings.
     */
    val codeChartsStringHandler = CodeChartsStringHandler().apply {
        /**
         * @param gridWidth contains number of grid cells in width dimension.
         * @param gridHeight contains number of grid cells in height dimension.
         * @param gridSize contains total number of grid cells in grid.
         */
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
        viewPort = Rectangle2D(
            0.0,
            0.0,
            0.0,
        0.0,
        ),
        recurseAt = 2,
        pictureImageView = ImageView()
    )
}
