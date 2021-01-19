package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData

/**
 * Extracts neccesary data points for rendering a heat map form data which was returned by query
 */
class DataProcessorHeatMap : DataProcessor(), IMethod {

    /**
     * Maps data points from query data to data class coordinates
     * returns a list of coordinates and the corresponing image path
     */
    override fun process(data: List<IPersist>) : MutableList<Coordinates> {
        val coordinatesList: MutableList<Coordinates> = mutableListOf()
        when (data) {
            is List<CodeChartsData> -> {

                for (dataset in data) {
                    val picturePath = dataset.codeChartsConfig.pictures[0].imagePath
                    val viewFocusPoint = dataset.stringPosition
                    val scaledImageSize = dataset.scaledImageSize

                    coordinatesList.add(
                            Coordinates(
                                    viewFocusPoint.xMin,
                                    viewFocusPoint.xMax,
                                    viewFocusPoint.yMin,
                                    viewFocusPoint.yMax,
                                    picturePath,
                                    scaledImageSize))
                }
            }
            is List<ZoomMapsConfig>  -> {
                //...
            }
        }
        return coordinatesList
    }
}

