package de.tuchemnitz.se.exercise.dataanalyzer

import org.litote.kmongo.newId
import org.slf4j.LoggerFactory

// for demo use
const val img = "/kitten.jpeg"

/**
 * Holds main logic of the data analyst application
 * Handles the flow of data
 */

class DataAnalyst() {

    companion object {
        val logger = LoggerFactory.getLogger("DataAnalyst Logger")
        var processor: DataProcessor = DataProcessor()
        var renderer: DataRenderer = DataRenderer()

    }

    /**
     * Takes filter parameters which the user input
     * Queries database collection depending on the tool asked for by the user
     * Returns a list of datasets which match the given filters
     */

    fun getData(
        tool: ITool,
        ageLowerLimit: Number,
        ageUpperLimit: Number,
        gender: String
    ): List<DummyData> {

        when (tool) {
            is CodeCharts -> {

                val dummyDataList: MutableList<DummyData> = mutableListOf()
                val dummyDataset = DummyData(
                    newId(), img,
                    Dimension(400.0, 600.0),
                    Dimension(800.0, 1200.0),
                    Interval2D(170.0, 200.0, 150.0, 180.0),
                )
                val dummyDataset1 = DummyData(
                    newId(), img,
                    Dimension(200.0, 300.0),
                    Dimension(800.0, 1200.0),
                    Interval2D(10.0, 40.0, 50.0, 80.0),
                )

                dummyDataList.add(dummyDataset)
                dummyDataList.add(dummyDataset1)

                return dummyDataList
            }
            // is ZoomMaps -> return QueryBuilder("ZoomMaps").find(ageLowerLimit, ageUpperLimit, gender)
        }
        return emptyList()
    }

    /**
     * Extracts the necessary values from the data, depending on the render method specified by the user
     */
    fun process(method: IMethod, data: List<DummyData>): MutableList<Coordinates> {

        when (method) {
            is DataRenderHeatMap -> {
                processor = DataProcessorHeatMap()
            }
            is DataRenderDiagram -> {
                processor = DataProcessorDiagram()
            }
        }
        // return processor.process(data)
        return processor.processMany(data)
    }

    /**
     * Creates the visual representation of the data, according to the render method specified by the user
     */
    fun render(method: IMethod, coordinates: Coordinates): Boolean {
        when (method) {
            is DataRenderHeatMap -> {
                renderer = DataRenderHeatMap()
            }
            is DataRenderDiagram -> {
                renderer = DataRenderDiagram()
            }
        }
        return renderer.render(coordinates)
    }

    fun renderMany(method: IMethod, coordinates: MutableList<Coordinates>): Boolean {
        when (method) {
            is DataRenderHeatMap -> {
                renderer = DataRenderHeatMap()
            }
            is DataRenderDiagram -> {
                renderer = DataRenderDiagram()
            }
        }
        return renderer.renderMany(coordinates)
    }
}
