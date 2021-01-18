package de.tuchemnitz.se.exercise.core.graphics.codecharts

import de.tuchemnitz.se.exercise.codecharts.CodeChartsPictureViewController
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsClickCounter
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool.codeChartsData
import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.RelativeEvent
import de.tuchemnitz.se.exercise.core.graphics.Style
import javafx.animation.PauseTransition
import javafx.event.EventHandler
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.util.Duration
import tornadofx.CssRule
import tornadofx.EventRegistration
import tornadofx.View
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.imageview
import tornadofx.toProperty
import java.awt.Toolkit
import javax.swing.Spring.height

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

    private var viewPortValues = Rectangle2D(0.0, 0.0, 0.0, 0.0)

    private val eventRegistration: EventRegistration
    private val codeChartsPictureViewController: CodeChartsPictureViewController by inject()

    override val root = hbox {
        addClass(cssRule)
        imageview {
            image = Image(codeChartsData.imagePath)

            viewportProperty().bind(viewPortValues.toProperty())

            val scaledImageSize = scaleImageSize(image, screenSize)
            val scaledImageWidth = scaledImageSize.x
            val scaledImageHeight = scaledImageSize.y

            fitWidthProperty().bind(scaledImageWidth.toProperty())
            fitHeightProperty().bind(scaledImageHeight.toProperty())

            setDataValues(image.width, image.height, scaledImageSize, screenSize)
        }
    }

    init {
        eventRegistration = subscribeToButtonEvents()
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

    private fun subscribeToButtonEvents() = subscribe<RelativeEvent> {
        relativeEvent ->
        if (codeChartsClickCounter.clickList.contains(codeChartsData.neededClicks)) {
            viewPortValues = Rectangle2D(
                codeChartsClickCounter.viewPort.xMin,
                codeChartsClickCounter.viewPort.yMin,
                codeChartsClickCounter.viewPort.xMax - codeChartsClickCounter.viewPort.xMin,
                codeChartsClickCounter.viewPort.yMax - codeChartsClickCounter.viewPort.yMin
            )
            for (i in 0..(codeChartsClickCounter.clickList.size)) {
                println("$i")
                codeChartsClickCounter.clickList[i] = 0
            }
        }
    }

    /*private fun setImageScales() {
        val codeChartsImage: ImageView by fxid("content")
        // root.imageview().fitWidthProperty().bind((25.0).toProperty())
    }
     */

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
        // setImageScales()
        goToGridView()
        // reloadViewsOnFocus()
    }
}
