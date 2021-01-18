package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsPictureView
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.PixelReader
import javafx.scene.image.WritableImage
import tornadofx.Controller
import tornadofx.getChildList
import tornadofx.imageview

class CodeChartsPictureViewController : Controller() {
    private val codeChartsPictureView: CodeChartsPictureView by inject()
    var cropData = Rectangle2D(0.0, 0.0, 2000.0, 2000.0)

    val image = Image(codeChartsData.imagePath)

    fun resize() {
        codeChartsPictureView.imageview().image = newImage
    }

    private val reader: PixelReader = image.pixelReader
    val newImage =
        WritableImage(
            reader,
            cropData.minX.toInt(),
            cropData.minY.toInt(),
            cropData.maxX.toInt(),
            cropData.maxY.toInt()
        )
}