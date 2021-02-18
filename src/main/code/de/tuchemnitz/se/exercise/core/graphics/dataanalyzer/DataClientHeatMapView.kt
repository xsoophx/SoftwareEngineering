package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

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
import tornadofx.replaceChildren
import java.awt.Toolkit
import java.nio.file.Path

/**
 * Current HeatMap Diagram to show data
 */
class DataClientHeatMapView : MainBarView("Data Client Heat Map") {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     *  @param zoomMapsDataList contains positions of viewpoints of zoomMaps
     *  @param codeChartsDataList contains positions of viewpoints of codeCharts
     *  @param colors contains colorpalette, which will be extendable later
     *  @param zoomMapsColor current color, selected for zoomMaps
     *  @param screenWidth current screenWidth
     *  @param screenHeight current screenHeight
     */
    private val dataList: List<Point2D> by param()
    private val imagePath: Path by param()
    private val colors =
        listOf(Color.BLUE, Color.BURLYWOOD, Color.CORAL, Color.CRIMSON, Color.DARKORANGE, Color.GREENYELLOW)
    private val zoomMapsColor = Color.BLUEVIOLET
    private val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()
    private val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()

    /**
     *  shows the image selected (currently default image) and draws circles with the viewpoints.
     */
    fun generateContent(){
        contentBox.replaceChildren {
            pane {
                val iv = imageview {
                    image = Image("${imagePath.toAbsolutePath().toUri()}")
                    viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
                    isPreserveRatio = true
                    fitWidthProperty().bind(contentBox.widthProperty())
                    maxHeightProperty().bind(contentBox.heightProperty())
                    logger.info("width: $maxWidth")
                    logger.info("height: $fitHeight")
                }
                group {
                    dataList.forEach {
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

    /**
     * Converts the image points of the 2DPoint into screencoordinates.
     * @param position is the ImageCoordinate, taken in another tool
     * @receiver ImageView contains the shapes and the image itself
     */
    private fun ImageView.imageToImageView(position: Point2D) = Point2D(
        screenWidth * position.x / image.width,
        screenHeight * position.y / image.height
    )
}
