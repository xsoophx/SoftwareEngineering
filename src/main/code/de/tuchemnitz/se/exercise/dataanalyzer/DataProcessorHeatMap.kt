package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.data.CodeChartsData

/**
 * Extracts neccesary data points for rendering a heat map form data which was returned by query
 */
class DataProcessorHeatMap : DataProcessor(), IMethod {

    /**
     * Maps data points from data returned by query to coordinates
     * returns a list of coordinates, image path and image size
     */
    override fun processCodeCharts(data: List<CodeChartsData>): MutableList<Coordinates> {
        val coordinatesList: MutableList<Coordinates> = mutableListOf()

        // all datasets used same image
        /** scale all images to the same size for output
         * use scaled size of first one as default
         */

        val picturePath = data[0].codeChartsConfig.pictures[0].imagePath
        val outputImageSize = data[0].scaledImageSize
        val(outputWidth, outputHeight) = outputImageSize

        for (dataset in data) {

            val viewFocusPoint = dataset.stringPosition
            val (currWidth, currHeight) = dataset.scaledImageSize

            /** translate position for output view:
             * Data is assuming that 0|0 is left upper corner but in output view
             * 0|0 is center -> shift coordinates accordingly
             * and scale to new image dimensions
             */

            val xStart = (viewFocusPoint.xMin) * (outputWidth / currWidth) - (outputWidth / 2)
            val yStart = (viewFocusPoint.yMin) * (outputHeight / currHeight) - (outputHeight / 2)
            val xEnd = (viewFocusPoint.xMax) * (outputWidth / currWidth) - (outputWidth / 2)
            val yEnd = (viewFocusPoint.yMax) * (outputHeight / currHeight) - (outputHeight / 2)

            coordinatesList.add(
                Coordinates(
                    xStart,
                    xEnd,
                    xEnd - xStart,
                    yEnd - yStart,
                    picturePath,
                    outputImageSize
                )
            )
        }

        return coordinatesList
    }
}
