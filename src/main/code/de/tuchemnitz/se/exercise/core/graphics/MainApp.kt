package de.tuchemnitz.se.exercise.core.graphics

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import javafx.scene.input.KeyCode
import tornadofx.importStylesheet
import tornadofx.set

class MainApp : AbstractTool(primaryView = ZoomMapsView::class) {
    init {
        scope.set(ZoomMapsConfig(zoomSpeed = 1.01f, zoomKey = KeyCode.C))
    }

    companion object {
        const val MAIN_VIEW_TEMPLATE_PATH = "/views/MainViewTemplate.fxml"
    }

    init {
        importStylesheet(Style::class)
    }
}
