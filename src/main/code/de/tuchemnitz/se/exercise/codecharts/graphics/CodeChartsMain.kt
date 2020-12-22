package de.tuchemnitz.se.exercise.codecharts.graphics

import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CodeChartsMain : App() {
    override val primaryView = CodeChartsGridView::class

    override fun start(stage: Stage) {
        super.start(stage)
        // stage.isMaximized = true
        stage.isMaximized = true
        stage.isResizable = true
        stage.minHeight = 850.0
        stage.minWidth = 1300.0
    }
    init {
        importStylesheet(CodeChartsStyle::class)
    }
}
