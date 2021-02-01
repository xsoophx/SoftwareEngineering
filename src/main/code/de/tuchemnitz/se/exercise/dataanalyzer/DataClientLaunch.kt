package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.dataanalyzer.DataClientMetaDataView
import de.tuchemnitz.se.exercise.core.graphics.system.ToolSelectionView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class DataClientLaunch : App(primaryView = ToolSelectionView::class) {

    companion object {
        const val MAIN_VIEW_TEMPLATE_PATH = "/views/MainViewTemplate.fxml"
    }
    override val primaryView = DataClientMetaDataView::class

    override fun start(stage: Stage) {
        stage.isFullScreen = true
        stage.isResizable = true
        // stage.maxHeight = 500.0
        // stage.maxWidth = 1100.0
        stage.fullScreenExitHint = ""
        super.start(stage)
    }

    init {
        importStylesheet(Style::class)
    }
}
