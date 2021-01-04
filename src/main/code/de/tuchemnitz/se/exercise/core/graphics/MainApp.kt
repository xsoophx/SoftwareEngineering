package de.tuchemnitz.se.exercise.core.graphics

import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsDialogView
import tornadofx.importStylesheet

class MainApp : AbstractTool(primaryView = CodeChartsDialogView::class) {

    companion object {
        const val MAIN_VIEW_TEMPLATE_PATH = "/views/MainViewTemplate.fxml"
    }

    init {
        importStylesheet(Style::class)
    }
}
