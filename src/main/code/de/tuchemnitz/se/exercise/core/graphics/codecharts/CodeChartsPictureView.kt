package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsClickCounter
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.core.graphics.Style
import javafx.animation.PauseTransition
import javafx.event.EventHandler
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.util.Duration
import tornadofx.CssRule
import tornadofx.View
import tornadofx.addClass
import tornadofx.imageview
import tornadofx.singleAssign
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
    var pictureViewImageView: ImageView by singleAssign()

    override val root = vbox {
        primaryStage.isFullScreen = true
        addClass(cssRule)
        pictureViewImageView = imageview {
            replaceImage(codeChartsData.imagePath)
            val scaledImageSize = scaleImageSize(image, screenSize)
            val scaledImageWidth = scaledImageSize.x
            val scaledImageHeight = scaledImageSize.y

            fitWidthProperty().bind(scaledImageWidth.toProperty())
            fitHeightProperty().bind(scaledImageHeight.toProperty())

            setDataValues(image.width, image.height, scaledImageSize, screenSize)
        }
    }

    fun changeImageSettings() {
        codeChartsClickCounter.pictureImageView.replaceImage("/cross.png")
    }

    fun ImageView.replaceImage(path: String) {
        image = Image(path)
        viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
    }

    /**
     * scales [image] to the user's [screenSize] maintaining the aspect ratio of the [image].
     */
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

    /**
     * Calls a timer using [PauseTransition].
     * Replaces CodeChartsPictureView with CodeChartsGridView when timer is ready.
     */
    private fun goToGridView() {
        val delay = PauseTransition(Duration.seconds(codeChartsData.pictureViewTime))
        delay.onFinished = EventHandler {
            codeChartsClickCounter.pictureImageView = pictureViewImageView
            replaceWith(CodeChartsGridView::class)
        }
        delay.play()
    }

    /**
     * Sets values that will be saved to database.
     * These are [originalImageWidth] which contains the width of the original image in pixels,
     * [originalImageHeight] which contains the height of the original image in pixels,
     * [scaledImageSize] which contains the width and height of the image after scaling it to the user's [screenSize].
     */
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
