package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsGridView
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsPictureView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CodeChartsTool(/*private val configManager: ConfigManager*/) : App() {
    // val setting = configManager.settings()
    override val primaryView = CodeChartsDialogView::class

    companion object {
        const val IMAGE_PATH = "/penguin.png"
    }

    override fun start(stage: Stage) {
        super.start(stage)
        stage.isFullScreen = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""
        // stage.minHeight = 850.0
        // stage.minWidth = 1300.0
    }

    init {
        importStylesheet(Style::class)
    }

    private fun saveData() {}
}

data class ImageSize(
    val x: Double,
    val y: Double,
)
