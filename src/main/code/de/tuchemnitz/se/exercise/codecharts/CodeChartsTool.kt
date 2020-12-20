package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.codecharts.graphics.CCGridView
import de.tuchemnitz.se.exercise.codecharts.graphics.CCPictureView
import de.tuchemnitz.se.exercise.codecharts.graphics.CCStyle
import javafx.stage.Stage
import tornadofx.App
import tornadofx.getProperty
import tornadofx.importStylesheet

class CodeChartsTool(/*private val configManager: ConfigManager*/) : App() {
    // val setting = configManager.settings()
    var scaledImageWidth = 0.0
    var scaledImageHeight = 0.0
    override val primaryView = CCPictureView::class
    override fun start(stage: Stage) {
        super.start(stage)
        // stage.isMaximized = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""
        stage.isFullScreen = true
    }

    init {
        importStylesheet(CCStyle::class)
    }

    private fun saveData() {}

    private fun setScaledImageSize() {
        scaledImageWidth = primaryView.getScaledImageWidth()
        scaledImageHeight = primaryView.getScaledImageHeight()
    }
}
