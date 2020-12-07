package de.tuchemnitz.se.exercise.core.graphics

import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class MainApp : App() {
    override val primaryView = MainView::class

    override fun start(stage: Stage) {
        super.start(stage)
        stage.isFullScreen = true
    }

    init {
        importStylesheet(Style::class)
    }
}
