package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import de.tuchemnitz.se.exercise.persist.Database
import javafx.stage.Stage
import tornadofx.set

class CodeChartsTool : AbstractTool(primaryView = CodeChartsDialogView::class) {
    private val db = Database("prod")
    private val cfgMan = ConfigManager(database = db.database)
    init {
        scope.set(db, cfgMan)
    }

    companion object {
        const val IMAGE_PATH = "/Chameleon.jpg"
        const val GRID_WIDTH = 50
        const val GRID_HEIGHT = 50
        const val M_VIEW_TIME = 10.0
        val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)
        val codeChartsData = CodeChartsValues()
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
        codeChartsStringHandler.setStrings(input = gridSize, allowedChars = codeChartsData.getAllowedChars())
        if (codeChartsData.getToOrder()) {
            codeChartsStringHandler.orderList()
        }
    }
}
