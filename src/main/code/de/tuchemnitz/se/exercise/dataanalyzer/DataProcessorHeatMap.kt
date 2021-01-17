package de.tuchemnitz.se.exercise.dataanalyzer

/**
 * Extracts neccesary data points for rendering a heat map form data which was returned by query
 */
class DataProcessorHeatMap : DataProcessor(), IMethod {

    /**
     * Maps data points from query data to data class coordinates
     * returns a list of coordinates and the corresponing image path
     */
    override fun process(data: List<DummyData>): MutableList<Coordinates> {

        // here maybe mapping function for data returned from query to format of dummyData
        val coordinatesList: MutableList<Coordinates> = mutableListOf()
        for (dataset in data) {
            coordinatesList.add(
                Coordinates(
                    dataset.interval.xMin,
                dataset.interval.yMin,
                dataset.interval.xMax,
                dataset.interval.yMax,
                dataset.imagePath,
                dataset.scaledImageSize))
        }
        return coordinatesList
    }
}
