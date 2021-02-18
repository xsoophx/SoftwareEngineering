package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors.HeatMapCoordinates
import de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors.Tools
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

    private val configManager: ConfigManager by inject()

    /**
     * zoomMapsDataList contains positions of viewpoints of zoomMaps
     * codeChartsDataList contains positions of viewpoints of codeCharts
     * colors contains color palette, which will be extendable later
     * zoomMapsColor current color, selected for zoomMaps
     * screenWidth current screenWidth
     * screenHeight current screenHeight
     */
    private val dataList: List<HeatMapCoordinates> by param()
    private val imagePath: Path by param()
    private val colors = configManager.getConfigColours()

    private val zoomMapsColor =
        Color.color(
            colors[0].red.toDouble() / 256,
            colors[0].green.toDouble() / 256,
            colors[0].blue.toDouble() / 256
        )
    private val codeChartsColor =
        Color.color(
            colors[1].red.toDouble() / 256,
            colors[1].green.toDouble() / 256,
            colors[1].blue.toDouble() / 256
        )

    private val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()
    private val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()

    /**
     *  shows the image selected (currently default image) and draws circles with the viewpoints.
     */
    fun generateContent() {
        contentBox.replaceChildren {
            pane {
                val iv = imageview {
                    image = Image("${imagePath.toAbsolutePath().toUri()}")
                    viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
                    isPreserveRatio = true
                    fitHeightProperty().bind(contentBox.heightProperty())
                    maxWidthProperty().bind(contentBox.widthProperty())
                    logger.info("width: $maxWidth")
                    logger.info("height: $fitHeight")
                }
                group {
                    dataList.forEach {
                        val position = iv.imageToImageView(it.coordinate)
                        circle {
                            logger.info("$position")
                            opacity = 0.3
                            fill = if (it.tool == Tools.CodeChartsTool) codeChartsColor else zoomMapsColor
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
        (position.x * boundsInLocal.width - viewport.minX * boundsInLocal.width) / viewport.width,
        (position.y * boundsInLocal.height - viewport.minY * boundsInLocal.height) / viewport.height
    )
}
