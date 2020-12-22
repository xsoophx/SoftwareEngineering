package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.codecharts.graphics.CodeChartsPictureView
import de.tuchemnitz.se.exercise.codecharts.graphics.CodeChartsStyle
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CodeChartsTool(/*private val configManager: ConfigManager*/) : App() {
    // val setting = configManager.settings()
    override val primaryView = CodeChartsPictureView::class

    override fun start(stage: Stage) {
        super.start(stage)
        // stage.isMaximized = true
        stage.isMaximized = true
        stage.isResizable = false
    }

    init {
        importStylesheet(CodeChartsStyle::class)
    }

    private fun saveData() {}
}
