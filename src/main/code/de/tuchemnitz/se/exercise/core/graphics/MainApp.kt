package de.tuchemnitz.se.exercise.core.graphics

import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsPictureView
// import de.tuchemnitz.se.exercise.core.graphics.system.LoginView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class MainApp : App() {
    override val primaryView = CodeChartsPictureView::class

    companion object {
        const val MAIN_VIEW_TEMPLATE_PATH = "/views/MainViewTemplate.fxml"
        const val IMAGE_PATH = "/penguin.png"
    }

    override fun start(stage: Stage) {
        super.start(stage)
        stage.isFullScreen = true
        stage.fullScreenExitHint = ""
        // stage.isMaximized = true
        // stage.isResizable = true
        // stage.minHeight = 850.0
        // stage.minWidth = 1300.0
    }

    init {
        importStylesheet(Style::class)
    }
}
