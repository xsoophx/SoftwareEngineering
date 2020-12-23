package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.ImageSize
import de.tuchemnitz.se.exercise.core.graphics.MainApp.Companion.IMAGE_PATH
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
    private val cssRule: CssRule = Style.ccPictureWrapper,
) : View("Software Praktikum - CodeCharts Picture") {
    override val root = vbox {
        title = pictureTitle
        addClass(cssRule)
        imageview {
            image = Image(IMAGE_PATH)
            var scaledImageSize = scaleImageSize(image)
            var scaledImageWidth = scaledImageSize.x
            var scaledImageHeight = scaledImageSize.y
            fitWidthProperty().bind(scaledImageWidth.toProperty())
            fitHeightProperty().bind(scaledImageHeight.toProperty())

            goToGridView()
        }
    }

    private fun scaleImageSize(image: Image): ImageSize {
        val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()
        val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()
        val imageWidth = image.width
        val imageHeight = image.height
        var newImageWidth = imageWidth
        var newImageHeight = imageHeight

        if (imageWidth > screenWidth) {
            // scale width to fit
            newImageWidth = screenWidth
            // scale height to maintain aspect ratio
            newImageHeight = (newImageWidth * imageHeight) / imageWidth
        }

        // then check if we need to scale even with the new height
        if (newImageHeight > screenHeight) {
            // scale height to fit instead
            newImageHeight = screenHeight
            // scale width to maintain aspect ratio
            newImageWidth = (newImageHeight * imageWidth) / imageHeight
        }

        return ImageSize(x = newImageWidth, y = newImageHeight)
    }

    fun goToGridView() {
        val delay = PauseTransition(Duration.seconds(5.0))
        delay.onFinished = EventHandler { event: ActionEvent? -> replaceWith(CodeChartsGridView::class) }
        delay.play()
    }
}
