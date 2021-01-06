package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

/**
 * Starts a new stage for CodeCharts and configures it.
 * First view to be shown is CodeChartsDialogView.
 */
class CodeChartsTool : App() {
    override val primaryView = CodeChartsDialogView::class

    companion object {
        const val IMAGE_PATH = "/Chameleon.jpg"
        const val GRID_WIDTH = 20
        const val GRID_HEIGHT = 20
        const val P_VIEW_TIME = 5.0
        const val M_VIEW_TIME = 10.0
        val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)
        val codeChartsData = CodeChartsDataValues()
        val handleStrings = CodeChartsStringHandler()
    }

    /**
     * Starts [stage] used for CodeCharts and configures some settings.
     */
    override fun start(stage: Stage) {
        stage.title = "CodeCharts"
        stage.isFullScreen = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""

        editData()
        super.start(stage)
    }

    init {
        importStylesheet(Style::class)
    }

    // dummy data, will be replaced with data from configmanager
    private fun editData() {
        val gridDimension = Dimension(x = GRID_WIDTH.toDouble(), y = GRID_HEIGHT.toDouble())
        codeChartsData.setGridDimension(gridDimension)
        codeChartsData.setAllowedChars(allowedCharacters)
        codeChartsData.setImagePath(IMAGE_PATH)
        codeChartsData.setPictureViewTime(P_VIEW_TIME)
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
