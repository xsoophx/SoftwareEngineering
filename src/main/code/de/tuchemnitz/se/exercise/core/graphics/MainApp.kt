package de.tuchemnitz.se.exercise.core.graphics


import de.tuchemnitz.se.exercise.core.graphics.system.ToolSelectionView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class MainApp : App() {
    override val primaryView = ToolSelectionView::class

    companion object {
        const val MAIN_VIEW_TEMPLATE_PATH = "/views/MainViewTemplate.fxml"
    }

    override fun start(stage: Stage) {
        stage.isFullScreen = true
        stage.fullScreenExitHint = ""
        super.start(stage)
    }

    init {
        importStylesheet(Style::class)
    }
}
