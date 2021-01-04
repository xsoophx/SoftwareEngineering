package de.tuchemnitz.se.exercise.zoommaps

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.graphics.Style
import de.tuchemnitz.se.exercise.core.graphics.zoommaps.ZoomMapsView
import tornadofx.importStylesheet

class ZoomMapsTool : AbstractTool(primaryView = ZoomMapsView::class) {

    init {
        importStylesheet(Style::class)
    }
}
