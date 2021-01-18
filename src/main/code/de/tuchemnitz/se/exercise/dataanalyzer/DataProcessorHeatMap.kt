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
                    dataset.scaledImageSize
                )
            )
        }
        return coordinatesList
    }

    override fun processMany(data: List<DummyData>): MutableList<Coordinates> {
        val coordinatesList: MutableList<Coordinates> = mutableListOf()

        /**
         * take scaled image size of first dataset as default size and adjust coordinates
         * form all other datasets accordingly
         */
        val imgSize = data[0].scaledImageSize
        val (width, height) = data[0].scaledImageSize

        for (dataset in data) {

            val (curWidth, curHeight) = dataset.scaledImageSize
            val (xMin, xMax, yMin, yMax) = dataset.interval

            coordinatesList.add(
                Coordinates(
                    xMin * (width / curWidth),
                    yMin * (height / curHeight),
                    xMax * (width / curWidth),
                    yMax * (height / curHeight),
                    dataset.imagePath,
                    imgSize
                )
            )
        }
        return coordinatesList
    }
}
