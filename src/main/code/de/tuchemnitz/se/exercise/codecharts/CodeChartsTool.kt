package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsGridView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CodeChartsTool(private val configManager: ConfigManager) :
    AbstractTool(primaryView = CodeChartsDialogView::class) {
    val recentSettings = configManager.getConfig(this)

    companion object {
        const val IMAGE_PATH = "/Chameleon.jpg"
        const val GRID_WIDTH = 50
        const val GRID_HEIGHT = 50
        const val M_VIEW_TIME = 10.0
        val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)
        val codeChartsData = CodeChartsDataValues()
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
        codeChartsData.setGridDimension(gridDimension)
        codeChartsData.setAllowedChars(allowedCharacters)
        codeChartsData.setImagePath(IMAGE_PATH)
        codeChartsData.setMatrixViewTime(M_VIEW_TIME)
        codeChartsData.setToOrder(true)

        // generate needed number of Strings
        val gridWidth = codeChartsData.getGridDimension().x
        val gridHeight = codeChartsData.getGridDimension().y
        val gridSize = (gridWidth * gridHeight).toInt()
        handleStrings.setStrings(input = gridSize, allowedChars = codeChartsData.getAllowedChars())
        if (codeChartsData.getToOrder()) {
            handleStrings.orderList()
        }
    }
}
