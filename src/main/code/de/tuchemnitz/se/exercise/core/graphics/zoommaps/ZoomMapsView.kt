package de.tuchemnitz.se.exercise.core.graphics.zoommaps

import com.sun.javafx.util.Utils.clamp
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.borderpane
import tornadofx.center
import tornadofx.getValue
import tornadofx.imageview
import tornadofx.setValue

class ZoomMapsView : View("Zoom Maps") {
    val clickLocationProperty: ObjectProperty<Point2D> = SimpleObjectProperty()
    var clickLocation by clickLocationProperty

    val scaleProperty = SimpleDoubleProperty(1.0)
    var scale by scaleProperty

    val zoomMapsConfig: ZoomMapsConfig by inject()

    companion object {
        val logger: Logger = LoggerFactory.getLogger("ZoomMapsView Logger")
    }

    override val root = borderpane {
        center {
            imageview {
                image = Image("/octopus.png")
                maxWidthProperty().bind(this@borderpane.widthProperty())
                fitHeightProperty().bind(this@borderpane.heightProperty())
                isPreserveRatio = true
                isPickOnBounds = true
                isSmooth = false // this does not disable anti-aliasing though :(

                viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
                logger.info("$zoomMapsConfig")

                setOnMouseClicked { e ->
                    clickLocation = imageViewToImage(Point2D(e.x, e.y))
                }

                shortcut(zoomMapsConfig.zoomKey.char) {
                    setOnScroll { e ->
                        val mouseLocation = imageViewToImage(Point2D(e.x, e.y))

                        val factor = if (e.deltaY > 0) 1.0 / 1.2 else 1.2
                        val oldScale = scale
                        scale = clamp(0.0625, oldScale * factor, 1.0)
                        val actualFactor = scale / oldScale

                        val minX = mouseLocation.x - (mouseLocation.x - viewport.minX) * actualFactor
                        val minY = mouseLocation.y - (mouseLocation.y - viewport.minY) * actualFactor
                        viewport = Rectangle2D(
                            minX,
                            minY,
                            mouseLocation.x + (viewport.maxX - mouseLocation.x) * actualFactor - minX,
                            mouseLocation.y + (viewport.maxY - mouseLocation.y) * actualFactor - minY
                        )

                        logger.info("Scaling around: $mouseLocation, factor: $scale")
                    }
                }
            }
        }
    }

    private fun ImageView.imageViewToImage(position: Point2D) = Point2D(
        viewport.minX + position.x * viewport.width / boundsInLocal.width,
        viewport.minY + position.y * viewport.height / boundsInLocal.height
    )
}
