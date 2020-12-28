package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.IMAGE_PATH
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.Companion.ccData
import de.tuchemnitz.se.exercise.core.graphics.Style
import javafx.animation.PauseTransition
import javafx.event.ActionEvent
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

class CodeChartsPictureView(
    pictureTitle: String = "",
    private val cssRule: CssRule = Style.ccPictureWrapper
) : View("Software Praktikum - CodeCharts Picture") {
    override val root = vbox {
        title = pictureTitle
        addClass(cssRule)
        imageview {
            val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()
            val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()
            val screenSize = Dimension(x = screenWidth, y = screenHeight)

            image = Image(ccData.getImagePath())
            var scaledImageSize = scaleImageSize(image, screenSize)
            var scaledImageWidth = scaledImageSize.x
            var scaledImageHeight = scaledImageSize.y
            fitWidthProperty().bind(scaledImageWidth.toProperty())
            fitHeightProperty().bind(scaledImageHeight.toProperty())

            setDataValues(image.width, image.height, scaledImageSize, screenSize)
            goToGridView()
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
            // scale width to fit
            newImageWidth = screenWidth
            // scale height to maintain aspect ratio
            newImageHeight = (newImageWidth * imageHeight) / imageWidth
        }

        // then check if we need to scale even with the new height
        if (newImageHeight != screenHeight) {
            // scale height to fit instead
            newImageHeight = screenHeight
            // scale width to maintain aspect ratio
            newImageWidth = (newImageHeight * imageWidth) / imageHeight
        }

        return Dimension(x = newImageWidth, y = newImageHeight)
    }

    private fun goToGridView() {
        val delay = PauseTransition(Duration.seconds(5.0))
        delay.onFinished = EventHandler { event: ActionEvent? -> replaceWith(CodeChartsGridView::class) }
        delay.play()
    }

    private fun setDataValues(originalImageWidth: Double, originalImageHeight: Double, scaledImageSize: Dimension, screenSize: Dimension) {
        ccData.setOriginalImageSize(Dimension(x = originalImageWidth, y = originalImageHeight))
        ccData.setScaledImageSize(scaledImageSize)
        ccData.setScreenSize(screenSize)
    }
}
