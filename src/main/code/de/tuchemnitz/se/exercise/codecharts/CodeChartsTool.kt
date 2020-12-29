package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CodeChartsTool/*(private val configManager: ConfigManager)*/ : App() {
    // val setting = configManager.settings()
    override val primaryView = CodeChartsDialogView::class
    companion object {
        const val IMAGE_PATH = "/Chameleon.jpg"
        const val GRID_WIDTH = 50
        const val GRID_HEIGHT = 50
        const val M_VIEW_TIME = 5.0
        val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)
        val ccData = CodeChartsDataValues()
        val handleStrings = CodeChartsStringHandler()
    }

    override fun start(stage: Stage) {
        stage.title = "CodeCharts"
        stage.isFullScreen = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""
        // stage.minHeight = 850.0
        // stage.minWidth = 1300.0

        editData()
        super.start(stage)
    }

    init {
        importStylesheet(Style::class)
    }

    private fun editData() {
        val gridDimension = Dimension(x = GRID_WIDTH.toDouble(), y = GRID_HEIGHT.toDouble())
        ccData.setGridDimension(gridDimension)
        ccData.setAllowedChars(allowedCharacters)
        ccData.setImagePath(IMAGE_PATH)
        ccData.setMatrixViewTime(M_VIEW_TIME)
        ccData.setToOrder(true)

        // generate needed number of Strings
        val gridWidth = ccData.getGridDimension().x
        val gridHeight = ccData.getGridDimension().y
        val gridSize = (gridWidth * gridHeight).toInt()
        handleStrings.setStrings(input = gridSize, allowedChars = ccData.getAllowedChars())
        if (ccData.getToOrder()) {
            handleStrings.orderList()
        }
    }
}
