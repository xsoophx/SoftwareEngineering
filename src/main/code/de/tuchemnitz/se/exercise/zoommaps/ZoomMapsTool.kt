package de.tuchemnitz.se.exercise.zoommaps

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import javafx.scene.input.KeyCode
import javafx.stage.Stage
import tornadofx.importStylesheet
import tornadofx.set

class ZoomMapsTool : AbstractTool<ZoomMapsView>(viewClass = ZoomMapsView::class) {

    init {
        scope.set(ZoomMapsConfig(zoomSpeed = 1f, zoomKey = KeyCode.C))
    }

    override fun start(stage: Stage) {
        start(stage)
        stage.isMaximized = true
        stage.isResizable = true
        stage.minHeight = 850.00
        stage.minWidth = 1300.0
    }

    init {
        importStylesheet(Style::class)
    }
}
