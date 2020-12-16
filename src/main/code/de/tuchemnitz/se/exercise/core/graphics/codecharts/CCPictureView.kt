package de.tuchemnitz.se.exercise.core.graphics.codecharts

import javafx.scene.image.Image
import tornadofx.CssRule
import tornadofx.View
import tornadofx.addClass
import tornadofx.imageview
import tornadofx.toProperty
import tornadofx.vbox
import java.awt.Toolkit

class CCPictureView(
    private val path: String = "/penguin.png",
    pictureTitle: String = "",
    private val cssRule: CssRule = CCStyle.ccPictureWrapper,
) : View("Software Praktikum - CodeCharts Picture") {
    override val root = vbox {
        title = pictureTitle
        addClass(cssRule)
        imageview {
            image = Image(path)

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

            fitHeightProperty().bind(newImageHeight.toProperty())
            fitWidthProperty().bind(newImageWidth.toProperty())
        }
    }
}
