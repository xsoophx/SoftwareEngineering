package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.core.graphics.Style
import javafx.animation.PauseTransition
import javafx.event.EventHandler
import javafx.scene.image.Image
import javafx.util.Duration
import tornadofx.CssRule
import tornadofx.View
import tornadofx.addClass
import tornadofx.imageview
import tornadofx.toProperty
import tornadofx.vbox
import java.awt.Toolkit

/**
 * Shows picture after scaling it to screen size.
 * Is replaced with CodeChartsGridView after delay.
 * Styling with [cssRule].
 */
class CodeChartsPictureView(
    private val cssRule: CssRule = Style.ccPictureWrapper
) : View("Software Praktikum - CodeCharts Picture") {

    private val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()
    private val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()
    private val screenSize = Dimension(x = screenWidth, y = screenHeight)

    override val root = vbox {
        addClass(cssRule)
        imageview {
            image = Image(codeChartsData.imagePath)
            val scaledImageSize = scaleImageSize(image, screenSize)
            val scaledImageWidth = scaledImageSize.x
            val scaledImageHeight = scaledImageSize.y

            fitWidthProperty().bind(scaledImageWidth.toProperty())
            fitHeightProperty().bind(scaledImageHeight.toProperty())

            setDataValues(image.width, image.height, scaledImageSize, screenSize)
        }
    }

    private fun scaleImageSize(image: Image, screenSize: Dimension): Dimension {
        val screenWidth = screenSize.x
        val screenHeight = screenSize.y
        val imageWidth = image.width
        val imageHeight = image.height
        var newImageWidth = imageWidth
        var newImageHeight = imageHeight

        if (imageWidth != screenWidth) {
            newImageWidth = screenWidth
            newImageHeight = (newImageWidth * imageHeight) / imageWidth
        }

        if (newImageHeight != screenHeight) {
            newImageHeight = screenHeight
            // scales image while maintaining aspect ratio
            newImageWidth = (newImageHeight * imageWidth) / imageHeight
        }

        return Dimension(x = newImageWidth, y = newImageHeight)
    }

    private fun goToGridView() {
        val delay = PauseTransition(Duration.seconds(codeChartsData.pictureViewTime))
        delay.onFinished = EventHandler {
            replaceWith(CodeChartsGridView::class)
        }
        delay.play()
    }

    private fun setDataValues(
        originalImageWidth: Double,
        originalImageHeight: Double,
        scaledImageSize: Dimension,
        screenSize: Dimension
    ) {
        codeChartsData.originalImageSize = (Dimension(x = originalImageWidth, y = originalImageHeight))
        codeChartsData.scaledImageSize = scaledImageSize
        codeChartsData.screenSize = screenSize
    }

    /**
     * Calls a timer. Replaces current view with CodeChartsGridView after delay.
     */
    override fun onDock() {
        goToGridView()
    }
}
