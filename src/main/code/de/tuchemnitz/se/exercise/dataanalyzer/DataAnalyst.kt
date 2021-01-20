package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.codecharts.Dimension
import de.tuchemnitz.se.exercise.codecharts.Interval2D
import de.tuchemnitz.se.exercise.codecharts.StringCharacters
import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.IData
import de.tuchemnitz.se.exercise.persist.now
import org.litote.kmongo.newId
import org.slf4j.LoggerFactory

/**
 * Holds main logic of the data analyst application
 * Handles data flow
 */

class DataAnalyst() {

    companion object {
        val logger = LoggerFactory.getLogger("DataAnalyst Logger")
        var processor: DataProcessor = DataProcessor()
    }

    /**
     * Takes filter parameters which the user selected in initial view
     * Queries database collection depending corresponding to the selected tool
     * Returns a list of datasets corresponding to filters
     */

    fun getData(
        tool: ITool,
        ageLowerLimit: Int,
        ageUpperLimit: Int,
        gender: Gender,
        imgPath: String
    ): List<IData> {

        /*
        when (tool) {
            is CodeCharts -> {
                val cc = CodeChartsDataFilter(
                   Filter(taken = false, value = 0),
                    Filter(taken = false, value = 0)
                )
                val codeChartsDataFilter = Filter(taken = true, value = cc)
                val userdata: Filter<UserDataFilter> = Filter(
                    taken = true,
                    UserDataFilter(
                        Filter(taken = false, value = ""),
                        Filter(taken = false, value = ""),
                        Filter(taken = true, value = Age(ageLowerLimit, ageUpperLimit)),
                        Filter(taken = true, value = gender)
                    )
                )
#
                val pictureDataFilter = PictureDataFilter( Filter(taken = true, value= imgPath))
                val pic = Filter(taken=true, value= pictureDataFilter)

                val queryFilter = Query.QueryFilter(userdata, codeChartsDataFilter = codeChartsDataFilter,
                zoomMapsFilter = null, pictureDataFilter = pic)

                return query.queryAllElementsSeparately(queryFilter) // list ipersist
            }
        }*/

        var dummyDataList = mutableListOf<CodeChartsData>()
        dummyDataList.add(
            CodeChartsData(
                newId(),
                CodeChartsConfig(
                    newId(),
                    now(),
                    0,
                    StringCharacters(true, true, false),
                    listOf(
                        PictureData(
                            "Pinguin.jpg",
                            Grid(50, 50),
                            5,
                            5,
                            false,
                            false,
                            1
                        )
                    )
                ),
                Dimension(800.0, 600.0),
                Dimension(700.0, 500.0),
                Dimension(1200.0, 900.0),
                Interval2D(100.0, 150.0, 100.0, 150.0)
            )
        )
        dummyDataList.add(
            CodeChartsData(
                newId(),
                CodeChartsConfig(
                    newId(),
                    now(),
                    0,
                    StringCharacters(true, true, false),
                    listOf(
                        PictureData(
                            "Pinguin.jpg",
                            Grid(50, 50),
                            5,
                            5,
                            false,
                            false,
                            1
                        )
                    )
                ),
                Dimension(700.0, 500.0),
                Dimension(1400.0, 1000.0),
                Dimension(1600.0, 1200.0),
                Interval2D(700.0, 750.0, 500.0, 550.0)
            )
        )
        return dummyDataList
    }

    /**
     * Extracts the necessary values from the data, depending selected render method
     */
    fun process(tool: ITool, method: IMethod, data: List<IPersist>): MutableList<Coordinates> {

        when (method) {
            is DataRenderHeatMap -> {
                processor = DataProcessorHeatMap()
            }
            is DataRenderDiagram -> {
                processor = DataProcessorDiagram()
            }
        }
        when (tool) {
            is CodeCharts -> return processor.processCodeCharts(data as List<CodeChartsData>)
        }

        return emptyList<Coordinates>().toMutableList()
    }
}
