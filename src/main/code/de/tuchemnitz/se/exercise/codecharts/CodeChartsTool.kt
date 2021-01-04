package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import de.tuchemnitz.se.exercise.persist.Database
import javafx.stage.Stage
import tornadofx.set

const val IMAGE_PATH = "/Chameleon.jpg"
const val GRID_WIDTH = 50
const val GRID_HEIGHT = 50
const val MATRIX_VIEW_TIME = 10.0
const val PICTURE_VIEW_TIME = 0.0

class CodeChartsTool : AbstractTool(primaryView = CodeChartsDialogView::class) {
    private val db = Database("prod")
    private val cfgMan = ConfigManager(database = db.database)

    init {
        scope.set(db, cfgMan)
    }

    companion object {
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
        val codeChartsStringHandler = CodeChartsStringHandler()
    }

    override fun start(stage: Stage) {
        codeChartsDataSetup()
        stage.title = "CodeCharts"
        stage.isFullScreen = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""
        super.start(stage)
    }

    private fun codeChartsDataSetup() {

        // generate needed number of Strings
        val gridWidth = codeChartsData.gridDimension.x
        val gridHeight = codeChartsData.gridDimension.y
        val gridSize = (gridWidth * gridHeight).toInt()
        codeChartsStringHandler.setStrings(input = gridSize, allowedChars = codeChartsData.allowedChars)
        if (codeChartsData.sorted) {
            codeChartsStringHandler.orderList()
        }
    }
}
