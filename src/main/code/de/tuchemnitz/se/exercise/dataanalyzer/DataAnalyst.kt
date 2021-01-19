package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.persist.IPersist
import org.slf4j.LoggerFactory

// for demo use
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
        val query = Query()

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
        ageLowerLimit: Int,
        ageUpperLimit: Int,
        gender: String
    ): List<IPersist> {

        when (tool) {
            is CodeCharts -> {
                val cc = CodeChartsDataFilter(
                    originalImageSize = Filter(taken = false, Dimension(x = 0.0, y = 0.0)),
                    scaledImageSize = Filter(taken = false, Dimension(x = 0.0, y = 0.0)),
                    screenSize = Filter(taken = false, Dimension(x = 0.0, y = 0.0)),
                    stringPosition = Filter(taken = false, Interval2D(0.0, 0.0, 0.0, 0.0))
                )
                val codeChartsDataFilter = Filter(taken = true, value = cc)
                val userdata: Filter<UserDataFilter> = Filter(
                    taken = true,
                    UserDataFilter(
                        Filter(taken = false, value = ""),
                        Filter(taken = false, value = ""),
                        Filter(taken = true, value = ageLowerLimit),
                        Filter(taken = true, value = ageUpperLimit),
                        Filter(taken = true, value = gender)
                    )
                )

                val user = Query.UserFilter(userdata, codeChartsDataFilter = codeChartsDataFilter)

                return query.queryAllElementsSeparately(user) // list ipersist
            }
        }
        return emptyList()
    }

    /**
     * Extracts the necessary values from the data, depending on the render method specified by the user
     */
    fun process(method: IMethod, data: List<IPersist>): MutableList<Coordinates> {

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
}
