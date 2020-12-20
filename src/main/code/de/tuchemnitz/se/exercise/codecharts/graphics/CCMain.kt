package de.tuchemnitz.se.exercise.codecharts.graphics

import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class CCMain : App() {
    override val primaryView = CCGridView::class

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
}
