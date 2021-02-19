package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.ImageView
import tornadofx.Controller

const val IMAGE_PATH = "/Chameleon.jpg"
const val GRID_WIDTH = 10
const val GRID_HEIGHT = 10
const val MATRIX_VIEW_TIME = 5000
const val PICTURE_VIEW_TIME = 5000
const val MIN_VIEWS_TO_SUBDIVIDE = 5

object CodeChartsTool : Controller() {
    private val configManager: ConfigManager by inject()

    private val codeChartsSettings = configManager.decodeConfig()?.codeChartsConfig ?: CodeChartsConfig(
        minViewsToSubdivide = 5,
        stringCharacters = StringCharacters(lowerCase = true, upperCase = true, numbers = true),
        pictures = listOf(
            PictureData(
                imagePath = IMAGE_PATH,
                grid = Grid(width = GRID_WIDTH, height = GRID_HEIGHT),
                pictureViewTime = PICTURE_VIEW_TIME,
                relative = false,
                maxRecursionDepth = 10
            )
        ),
        matrixViewTime = MATRIX_VIEW_TIME,
        ordered = true
    )

    /**
     * [codeChartsData] contains all needed values to execute CodeCharts.
     * More Information can be found in [CodeChartsValues]
     */
    val codeChartsData = CodeChartsValues(
        gridDimension = Dimension(
            x = codeChartsSettings.pictures[0].grid.width.toDouble(),
            y = codeChartsSettings.pictures[0].grid.height.toDouble()
        ),
        allowedChars = codeChartsSettings.stringCharacters,
        imagePath = codeChartsSettings.pictures[0].imagePath,
        matrixViewTime = codeChartsSettings.matrixViewTime,
        pictureViewTime = codeChartsSettings.pictures[0].pictureViewTime,
        sorted = codeChartsSettings.ordered,
        eyePos = Interval2D(minimum = Point2D(0.0, 0.0), Point2D(0.0, 0.0)),
        originalImageSize = Dimension(0.0, 0.0),
        scaledImageSize = Dimension(0.0, 0.0),
        screenSize = Dimension(0.0, 0.0),
        relative = codeChartsSettings.pictures[0].relative,
        recursionDepth = codeChartsSettings.pictures[0].maxRecursionDepth
    )

    /**
     * [codeChartsStringHandler]: StringHandler holds and generates Strings. Able to validate whether given String is element of generated Strings.
     */
    val codeChartsStringHandler = CodeChartsStringHandler().apply {
        /**
         * gridWidth contains number of grid cells in width dimension.
         * gridHeight contains number of grid cells in height dimension.
         * gridSize contains total number of grid cells in grid.
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
        recurseAt = codeChartsSettings.minViewsToSubdivide,
        pictureImageView = ImageView()
    )
}
