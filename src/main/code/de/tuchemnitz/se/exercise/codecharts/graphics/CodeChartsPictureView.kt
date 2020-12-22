package de.tuchemnitz.se.exercise.codecharts.graphics

// import javafx.scene.layout.VBox
import de.tuchemnitz.se.exercise.codecharts.ImageSize
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.CssRule
import tornadofx.View
import tornadofx.addClass
import tornadofx.imageview
import tornadofx.toProperty

class CodeChartsPictureView(
    private val path: String = "/penguin.png",
    pictureTitle: String = "",
    private val cssRule: CssRule = CodeChartsStyle.ccPictureWrapper,
) : View("Software Praktikum - CodeCharts Picture") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)

    val gitButton: Button by fxid("git_button")
    private val contentBox: VBox by fxid("content")

    init {
        with(contentBox) {
            addClass(cssRule)
            imageview {
                image = Image(path)
                id = "imageToShow"
                fitHeightProperty().bind((scaleImageSize(image).x).toProperty())
                fitWidthProperty().bind((scaleImageSize(image).y).toProperty())
            }
        }
    }
    private fun scaleImageSize(image: Image): ImageSize {
        val contentBoxWidth = contentBox.width
        val contentBoxHeight = contentBox.height
        val imageWidth = image.width
        val imageHeight = image.height
        var newImageWidth = imageWidth
        var newImageHeight = imageHeight
        println("$contentBoxWidth, $contentBoxHeight")

        if (imageWidth > contentBoxWidth) {
            // scale width to fit
            newImageWidth = contentBoxWidth
            // scale height to maintain aspect ratio
            newImageHeight = (newImageWidth * imageHeight) / imageWidth
        }

        // then check if we need to scale even with the new height
        if (newImageHeight > contentBoxHeight) {
            // scale height to fit instead
            newImageHeight = contentBoxHeight
            // scale width to maintain aspect ratio
            newImageWidth = (newImageHeight * imageWidth) / imageHeight
        }
        return ImageSize(x = newImageWidth, y = newImageHeight)
    }

    fun printGitButton() {
    }
}
/*
    private var scaledImageHeight = 0.0
    private var scaledImageWidth = 0.0
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

            scaledImageHeight = newImageHeight
            scaledImageWidth = newImageWidth
        }
    }

    fun getScaledImageWidth(): Double {
        return scaledImageWidth
    }
    fun getScaledImageHeight(): Double {
        return scaledImageHeight
    }
}
*/
