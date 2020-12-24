package de.tuchemnitz.se.exercise.core.graphics.zoommaps

import com.sun.javafx.util.Utils.clamp
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCombination
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.borderpane
import tornadofx.center
import tornadofx.imageview
import kotlin.math.pow

const val MIN_PIXELS = 10

class ZoomMapsView : View("Zoom Maps") {
    var mouseDown: ObjectProperty<Point2D> = SimpleObjectProperty()

    companion object {
        val logger = LoggerFactory.getLogger("ZoomMapsView Logger")
    }

    override val root = borderpane {
        center {

            imageview {
                //var viewport = Point2D(this., this.maxWidth())

                image = Image("/cross.png")
                this.isPreserveRatio = true;
                reset(this, width / 2, height / 2);
                this.setOnMousePressed { e ->
                    val mousePress: Point2D = Point2D(e.x, e.y)
                    mouseDown.set(mousePress)
                    logger.info(mousePress.toString())
                }
                this.setOnZoom { e ->
                    this.viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
                    val delta = e.y
                    val scale = clamp(
                        1.01.pow(delta),
                        (MIN_PIXELS / image.width).coerceAtMost(MIN_PIXELS / image.height),
                        (width / image.width).coerceAtLeast(height / image.height)
                    )
                    val mouse: Point2D = Point2D(e.x, e.y)
                    val newWidth = image.width * scale
                    val newHeight = image.height * scale

                    // of the imageview:
                    val newMinX: Double = clamp(
                        mouse.x - (mouse.x - viewport.minX) * scale,
                        0.0,
                        width - newWidth
                    )
                    val newMinY: Double = clamp(
                        mouse.y - (mouse.y - viewport.minY) * scale,
                        0.0,
                        height - newHeight
                    )

                    this.viewport = Rectangle2D(newMinX, newMinY, newWidth, newHeight)
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
        imageView.viewport = Rectangle2D(0.0, 0.0, width, height)
    }

    private fun doSomething() {
        TODO("Not yet implemented")
    }
}