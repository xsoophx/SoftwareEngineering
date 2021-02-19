package de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import javafx.geometry.Point2D
import java.nio.file.Path

/**
 * Extracts necessary data points for rendering a heat map form data which was returned by query
 */
class DataProcessorHeatMap : DataProcessor<HeatMapCoordinates>() {

    /**
     * processes data from CodeCharts and ZoomMaps tool
     */
    override fun process(data: List<IPersist>): List<HeatMapCoordinates> {
        return data.mapNotNull {
            when (it) {
                is ZoomMapsData -> HeatMapCoordinates(
                    coordinate = it.zoomPosition,
                    tool = Tools.ZoomMapsTool,
                    imagePath = Path.of(it.imagePath)
                )
                is CodeChartsData -> HeatMapCoordinates(
                    coordinate = Point2D(
                        (it.stringPosition.minimum.x + (it.stringPosition.maximum.x - it.stringPosition.minimum.x) / 2) * (it.originalImageSize.x / it.scaledImageSize.x),
                        (it.stringPosition.minimum.y + (it.stringPosition.maximum.y - it.stringPosition.minimum.y) / 2) * (it.originalImageSize.y / it.scaledImageSize.y)
                    ),
                    tool = Tools.CodeChartsTool,
                    imagePath = Path.of(it.codeChartsConfig.pictures.first().imagePath)
                )
                else -> null
            }
        }
    }
}

enum class Tools {
    CodeChartsTool,
    ZoomMapsTool
}

data class HeatMapCoordinates(
    val coordinate: Point2D,
    val tool: Tools,
    val imagePath: Path
)
