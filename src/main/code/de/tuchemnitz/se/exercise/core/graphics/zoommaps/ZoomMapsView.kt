package de.tuchemnitz.se.exercise.core.graphics.zoommaps

import com.sun.javafx.util.Utils.clamp
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCombination
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.borderpane
import tornadofx.center
import tornadofx.imageview
import tornadofx.getValue
import tornadofx.setValue
import kotlin.math.pow

const val MIN_PIXELS = 10

class ZoomMapsView : View("Zoom Maps") {
    private val clickLocationProperty: ObjectProperty<Point2D> = SimpleObjectProperty()
    var clickLocation: Point2D by clickLocationProperty

    companion object {
        val logger: Logger = LoggerFactory.getLogger("ZoomMapsView Logger")
    }

    private fun imageViewToImage(imageView: ImageView, imageViewCoordinates: Point2D): Point2D {
        val xProportion = imageViewCoordinates.x / imageView.boundsInLocal.width
        val yProportion = imageViewCoordinates.y / imageView.boundsInLocal.height

        val viewport = imageView.viewport
        return Point2D(
            viewport.minX + xProportion * viewport.width,
            viewport.minY + yProportion * viewport.height
        )
    }

    override val root = borderpane {
        center {

            imageview {
                image = Image("/cross.png")
                fitWidthProperty().bind(this@borderpane.widthProperty())
                fitHeightProperty().bind(this@borderpane.heightProperty())
                fitWidthProperty().addListener { _, _, value ->
                    //reset(this, value.toDouble(), fitHeight)
                    reset(this, image.width, image.height)

                }
                fitHeightProperty().addListener { _, _, value ->
                    //  reset(this, fitWidth, value.toDouble())
                    reset(this, image.width, image.height)

                }
                // reset(this, image.width, image.height)

                setOnMousePressed { e ->
                    clickLocation = Point2D(e.x, e.y)
                    logger.info("Clicked at: $clickLocation")
                }


                setOnScroll { e ->
                    val delta = e.deltaY
                    val scale = clamp(
                        1.001.pow(delta),
                        (MIN_PIXELS / viewport.width).coerceAtMost(MIN_PIXELS / viewport.height),
                        (image.width / viewport.width).coerceAtLeast(image.height / viewport.height)
                    )
                    val mouseLocation = imageViewToImage(this, Point2D(e.x, e.y))
                    val newWidth = image.width * scale
                    val newHeight = image.height * scale

                    // of the imageview:
                    val newMinX = clamp(
                        mouseLocation.x - (mouseLocation.x - viewport.minX) * scale,
                        0.0,
                        width - newWidth
                    )
                    val newMinY: Double = clamp(
                        mouseLocation.y - (mouseLocation.y - viewport.minY) * scale,
                        0.0,
                        height - newHeight
                    )

                    viewport = Rectangle2D(newMinX, newMinY, newWidth, newHeight)
                    //this.fitHeight = newMinY - newMinY
                    //this.fitWidth = newMinX - newMinX
                    logger.info("viewport: $viewport")
                    // image.width = newMinY - newMinY
                    // image.height = newMinX - newMinX
                }

                shortcut(KeyCombination.valueOf("Ctrl+Y")) {

                }
            }

        }
    }

    private fun reset(imageView: ImageView, width: Double, height: Double) {
        val diffx = imageView.fitWidth - width
        val diffy = imageView.fitHeight - height
        imageView.viewport = Rectangle2D(
            -diffx / 2, -diffy / 2, width + diffx / 2, height + diffy / 2
        )
        logger.info("${imageView.viewport}")
    }

    private fun doSomething() {
        TODO("Not yet implemented")
    }
}