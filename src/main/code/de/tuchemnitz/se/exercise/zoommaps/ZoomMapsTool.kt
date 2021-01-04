package de.tuchemnitz.se.exercise.zoommaps

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import javafx.scene.input.KeyCode
import javafx.stage.Stage
import tornadofx.importStylesheet
import tornadofx.set

class ZoomMapsTool : AbstractTool(primaryView = ZoomMapsView::class) {

    init {
        importStylesheet(Style::class)
    }
}
