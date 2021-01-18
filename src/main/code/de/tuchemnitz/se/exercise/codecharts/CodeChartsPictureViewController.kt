package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.graphics.codecharts.CodeChartsPictureView
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import tornadofx.Controller
import tornadofx.imageview

class CodeChartsPictureViewController : Controller() {
    private val codeChartsPictureView: CodeChartsPictureView by inject()

    fun resize() {
        codeChartsPictureView.root.imageview().viewport = Rectangle2D(0.0, 0.0, 800.0, 800.0)
        // codeChartsPictureView.root.imageview().fitWidthProperty().bind()
        // codeChartsPictureView.root.imageview().fitHeightProperty().bind(heightProperty())
        /*
        codeChartsPictureView.imageview().image = Image("/Chameleon.jpg")
        val reader = codeChartsPictureView.imageview().image.pixelReader
        val newImage = WritableImage(reader, 0, 0, 800, 800) // image.width.toInt(), image.height.toInt())
        codeChartsPictureView.imageview().image = newImage
         */
            /*
        codeChartsPictureView.imageview().viewport =
            Rectangle2D(
                0.0,
                0.0,
                CodeChartsTool.codeChartsData.originalImageSize.x / 10,
                CodeChartsTool.codeChartsData.originalImageSize.y / 10
            )
             */
    }
}