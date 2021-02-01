package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import javafx.geometry.Point2D

/**
 * Extracts neccesary data points for rendering a heat map form data which was returned by query
 */
class DataProcessorHeatMap : DataProcessor() {

    /**
     * Processes only ZoomMaps data
     */

    fun processZoomMaps(data: List<IPersist>,): List<Point2D> {
        return data.mapNotNull {
            when (it) {
                is ZoomMapsData -> it.zoomPosition

                else -> null
            }
        }
    }

    /**
     * Processes only CodeCharts data
     * Calculates mid point of grid square which the subject focused on
     */
    fun processCodeCharts(data: List<IPersist>,): List<Point2D> {
        return data.mapNotNull {
            when (it) {
                is CodeChartsData -> Point2D(
                    (it.stringPosition.xMax - it.stringPosition.xMin) / 2,
                    (it.stringPosition.yMax - it.stringPosition.yMin) / 2
                )

                else -> null
            }
        }
    }
    /**
     * processes data from CodeCharts and ZoomMaps tool
     */

    override fun process(data: List<IPersist>,): List<Point2D> {
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
