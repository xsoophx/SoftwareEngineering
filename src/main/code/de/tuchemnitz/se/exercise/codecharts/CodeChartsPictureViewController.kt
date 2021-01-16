package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsPictureView
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import javafx.geometry.Rectangle2D
import tornadofx.Controller
import tornadofx.imageview

class CodeChartsPictureViewController : Controller() {
    private val codeChartsPictureView: CodeChartsPictureView by inject()

    fun resize() {
        codeChartsPictureView.imageview().viewport =
            Rectangle2D(
                0.0,
                0.0,
                CodeChartsTool.codeChartsData.originalImageSize.x / 10,
                CodeChartsTool.codeChartsData.originalImageSize.y / 10
            )
    }
}