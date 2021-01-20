package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import javafx.scene.input.KeyCode
import org.slf4j.LoggerFactory

// for demo use
const val img = "/kitten.jpeg"

/**
 * Holds main logic of the data analyst application
 * Handles the flow of data
 */

class DataAnalyst(private val dataClientQuery: DataClientQuery) {

    companion object {
        val logger = LoggerFactory.getLogger("DataAnalyst Logger")
        var processor: DataProcessor = DataProcessor()
        var renderer: DataRenderer = DataRenderer()
    }

    private val query = Query()

    /**
     * Takes filter parameters which the user input
     * Queries database collection depending on the tool asked for by the user
     * Returns a list of datasets which match the given filters
     */

    fun getData(): List<IPersist> = query.queryAllElementsSeparately(createQueryFilter())

    private fun createQueryFilter() = Query.QueryFilter(
        userDataFilter = userDataFilter(),
        codeChartsDataFilter = codeChartsDataFilter(),
        zoomMapsFilter = zoomMapsDataFilter(),
        pictureDataFilter = pictureDataFilter()
    )

    private fun userDataFilter() = Filter<UserDataFilter>(
        taken = dataClientQuery.age != null || dataClientQuery.gender != null,
        value = UserDataFilter(
            firstName = Filter(taken = false, value = ""),
            lastName = Filter(taken = false, value = ""),
            age = Filter(taken = dataClientQuery.age != null, value = dataClientQuery.age),
            gender = Filter(taken = dataClientQuery.gender != null, value = dataClientQuery.gender)
        )
    )

    private fun codeChartsDataFilter() = Filter<CodeChartsDataFilter>(
        taken = dataClientQuery.codeCharts,
        value = CodeChartsDataFilter(
            pictureViewTime = Filter(taken = false, value = -1),
            matrixViewTime = Filter(taken = false, value = -1),
        )
    )

    private fun zoomMapsDataFilter() = Filter<ZoomMapsDataFilter>(
        taken = dataClientQuery.zoomMaps,
        value = ZoomMapsDataFilter(
            keyCode = Filter(taken = false, value = KeyCode.A)
        )
    )

    private fun pictureDataFilter() = Filter<PictureDataFilter>(
        taken = false,
        value = PictureDataFilter(
            imagePath = Filter(taken = false, value = "")
        )
    )

    /**
     * Extracts the necessary values from the data, depending on the render method specified by the user
     */
    fun process(): MutableList<Coordinates> {
        when (dataClientQuery.method) {
            Method.Heatmap -> processor = DataProcessorHeatMap()
            Method.Diagram -> processor =  DataProcessorDiagram()
        }
        return processor.processMany(getData())
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
