package de.tuchemnitz.se.exercise.dataanalyzer

import org.litote.kmongo.newId
import org.slf4j.LoggerFactory

const val kittenImg = "/Chameleon.jpg"

class DataAnalyst() {
    companion object {
        val logger = LoggerFactory.getLogger("DataAnalyst Logger")
        var processor: DataProcessor = DataProcessor()
        var renderer: DataRenderer = DataRenderer()
        // val colors: Set<ColorSampleBoard> 
       // val query: QueryBuilder = QueryBuilder("")
    }

    // process data by render method

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

    fun getData(
        tool: ITool,
        ageLowerLimit: Number,
        ageUpperLimit: Number,
        gender: String
    ): List<DummyData> {

        when (tool) {
            //is CodeCharts -> return QueryBuilder("CodeCharts").find(ageLowerLimit, ageUpperLimit, gender)
                is CodeCharts -> {
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
