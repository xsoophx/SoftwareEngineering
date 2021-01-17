package de.tuchemnitz.se.exercise.dataanalyzer

import org.litote.kmongo.newId
import org.slf4j.LoggerFactory


//for demo use
const val img = "/Chameleon.jpg"


/**
 * Holds main logic of the data analyst application
 * Handles the flow of data
 */

class DataAnalyst() {

    companion object {
        val logger = LoggerFactory.getLogger("DataAnalyst Logger")
        var processor: DataProcessor = DataProcessor()
        var renderer: DataRenderer = DataRenderer()
        // val colors: Set<ColorSampleBoard> 
       // val query: QueryBuilder = QueryBuilder("")
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
            //is CodeCharts -> return QueryBuilder("CodeCharts").find(ageLowerLimit, ageUpperLimit, gender)
            is CodeCharts -> {

                //for demo use
                val DummyDataList: MutableList<DummyData> = mutableListOf()
                val dummyDataset = DummyData(
                    newId(), kittenImg,
                    Dimension(400.0, 600.0),
                    Dimension(300.0, 500.0),
                    Interval2D(70.0, 100.0, 50.0, 80.0),
                )
                DummyDataList.add(dummyDataset)
                return DummyDataList
            }
            //is ZoomMaps -> return QueryBuilder("ZoomMaps").find(ageLowerLimit, ageUpperLimit, gender)
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
        return processor.process(data)
    }

    /**
     * Creates the visual representation of the data, according to the render method specified by the user
     */
    fun render(method: IMethod, coordinates: Coordinates) : Boolean{
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
}
