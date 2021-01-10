package de.tuchemnitz.se.exercise.dataanalyzer

class DataProcessorHeatMap : DataProcessor(), IMethod {
    override fun process(data: List<DummyData>): MutableList<Coordinates> {
        val coordinatesList: MutableList<Coordinates> = mutableListOf()
        for (dataset in data) {
            coordinatesList.add(
                Coordinates(dataset.interval.xMin,
                dataset.interval.yMin,
                dataset.interval.xMax,
                dataset.interval.yMax,
                dataset.imagePath,
                dataset.scaledImageSize))
        }
        return coordinatesList
    }
}
