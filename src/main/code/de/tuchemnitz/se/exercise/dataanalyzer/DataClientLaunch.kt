package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.dataanalyzer.DataClientInitialView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class DataClientLaunch : App() {

    override val primaryView = DataClientInitialView::class

    override fun start(stage: Stage) {
        stage.title = "DataClient"
        stage.isFullScreen = true
        stage.isResizable = false
        stage.fullScreenExitHint = ""

        super.start(stage)
    }

    init {
        importStylesheet(Style::class)
    }
}
