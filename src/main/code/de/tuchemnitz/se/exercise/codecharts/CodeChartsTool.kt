package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CodeChartsTool(/*private val configManager: ConfigManager*/) : App() {
    // val setting = configManager.settings()
    override val primaryView = CodeChartsDialogView::class
    companion object {
        const val IMAGE_PATH = "/Fox.jpg"
        const val GRID_WIDTH = 10
        const val GRID_HEIGHT = 10
        const val M_VIEWTIME = 5.0
        val allowedCharacters = StringCharacters(upperCase = true, lowerCase = true, numbers = true)
        val ccData = CodeChartsDataValues()
        val handleStrings = CodeChartsStringHandler()
    }

    override fun start(stage: Stage) {
        super.start(stage)
        stage.isFullScreen = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""
        // stage.minHeight = 850.0
        // stage.minWidth = 1300.0

        editData()
    }

    override fun stop() {
        super.stop()
    }

    init {
        importStylesheet(Style::class)
    }

    private fun editData() {
        val gridDimension = Dimension(x = GRID_WIDTH.toDouble(), y = GRID_HEIGHT.toDouble())
        ccData.setGridDimension(gridDimension)
        ccData.setAllowedChars(allowedCharacters)
        ccData.setImagePath(IMAGE_PATH)
        ccData.setMatrixViewTime(M_VIEWTIME)
    }

    private fun saveData() {}
}

data class Dimension(
    val x: Double,
    val y: Double,
)

data class StringCharacters(
    val upperCase: Boolean,
    val lowerCase: Boolean,
    val numbers: Boolean
)
