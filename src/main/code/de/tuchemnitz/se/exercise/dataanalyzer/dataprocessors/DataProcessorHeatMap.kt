package de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import javafx.geometry.Point2D

/**
 * Extracts neccesary data points for rendering a heat map form data which was returned by query
 */
class DataProcessorHeatMap : DataProcessor<Point2D>() {

    /**
     * processes data from CodeCharts and ZoomMaps tool
     */
    override fun process(data: List<IPersist>): List<Point2D> {
        println("processing")
        return data.mapNotNull {
            when (it) {
                is ZoomMapsData -> it.zoomPosition
                is CodeChartsData -> Point2D(
                    (it.stringPosition.xMax - it.stringPosition.xMin) / 2,
                    (it.stringPosition.yMax - it.stringPosition.yMin) / 2
                )

                else -> null
            }
        }
    }
}
