package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import org.slf4j.LoggerFactory
import tornadofx.circle
import tornadofx.group
import tornadofx.imageview
import tornadofx.pane
import java.awt.Toolkit

class DataClientHeatMapView : MainBarView("Data Client Heat Map") {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    private val zoomMapsDataList: List<Point2D> by param()
    private val codeChartsDataList: List<Point2D> by inject()
    private val colors =
        listOf(Color.BLUE, Color.BURLYWOOD, Color.CORAL, Color.CRIMSON, Color.DARKORANGE, Color.GREENYELLOW)
    private val zoomMapsColor = Color.BLUEVIOLET
    private val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()
    private val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()

    init {
        with(contentBox) {
            pane {
                val iv = imageview {
                    image = Image(CodeChartsTool.codeChartsData.imagePath)
                    viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
                    isPreserveRatio = true
                    fitWidthProperty().bind(contentBox.widthProperty())
                    maxHeightProperty().bind(contentBox.heightProperty())
                    logger.info("width: $maxWidth")
                    logger.info("height: $fitHeight")
                }
                group {
                    zoomMapsDataList.forEach {
                        val position = iv.imageToImageView(it)
                        circle {
                            logger.info("$position")
                            opacity = 0.3
                            fill = colors.random()
                            centerX = position.x
                            centerY = position.y
                            radius = 30.0
                        }
                    }
                }
            }
        }
    }

    private fun ImageView.imageToImageView(position: Point2D) = Point2D(
        boundsInLocal.width * position.x / viewport.width,
        boundsInLocal.height * position.y / viewport.height
    )
}
