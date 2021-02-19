package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.ImageCollection
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.collections.ObservableList
import javafx.scene.chart.PieChart
import tornadofx.Controller
import tornadofx.asObservable
import tornadofx.observableListOf

/**
 * Queries the database for metadata concerning number and demographic of users and statistics concerning
 * tool use and picture view
 */

class MetaDataController : Controller() {

    /**
     * Contains all Users which match the selected Filter
     */
    val query: Query by inject()
    private val imageCollection: ImageCollection by inject()
    val allImages = imageCollection.find().distinct()
    val totalCodeChartsUsers = query.codeChartsUsers()
    val totalZoomMapsUsers = query.zoomMapsUsers()

    /**
     * Finds total number of users, total number of datasets and total number of datasets distinguished by tool in the database
     */
    fun totalUsers(): Int = query.userDataCollection.find().count()

    fun totalDatasets(): Int =
        query.codeChartsDataCollection.find().count() + query.zoomMapsDataCollection.find().count()

    private fun totalCodeChartsDataSets(): Int = query.codeChartsDataCollection.find().count()

    private fun totalZoomMapsDataSets(): Int = query.zoomMapsDataCollection.find().count()

    /**
     * Gets and formats data to illustrate distribution of gender among users
     */
    data class GenderPieChartData(
        val totalMale: Int = 0,
        val totalFemale: Int = 0,
        val totalDiverse: Int = 0,
        val totalUnselected: Int = 0
    )

    fun createGenderPie(userList: List<UserData>): ObservableList<PieChart.Data> =
        userList.fold(GenderPieChartData()) { piechartdata, userdata ->
            when (userdata.gender) {
                Gender.Male -> piechartdata.copy(totalMale = piechartdata.totalMale + 1)
                Gender.Female -> piechartdata.copy(totalFemale = piechartdata.totalFemale + 1)
                Gender.Diverse -> piechartdata.copy(totalDiverse = piechartdata.totalDiverse + 1)
                Gender.Unselected -> piechartdata.copy(totalUnselected = piechartdata.totalUnselected + 1)
            }
        }.let { (totalMale, totalFemale, totalDiverse, totalUnselected) ->
            observableListOf(
                PieChart.Data("Male", totalMale.toDouble()),
                PieChart.Data("Female", totalFemale.toDouble()),
                PieChart.Data("Diverse", totalDiverse.toDouble()),
                PieChart.Data("Unknown Age", totalUnselected.toDouble())
            )
        }

    /**
     * Gets and formats data to illustrate age distribution among users
     * @param userList: Contains user data which is used to create pie chart data
     */

    fun createAgePie(userList: List<UserData>): ObservableList<PieChart.Data> =
        userList.fold(IntArray(8)) { piechartdata, userdata ->
            if (userdata.age == 0) {
                ++piechartdata[7]
            } else {
                val ageGroup = (userdata.age / 10).coerceAtMost(7)
                ++piechartdata[ageGroup]
            }
            piechartdata
        }.mapIndexed { ageGroup, total ->
            val description =
                when (ageGroup) {
                    6 -> "60+"
                    7 -> "unknown Age"
                    else -> "${ageGroup * 10}-${ageGroup * 10 + 9}"
                }
            PieChart.Data(description, total.toDouble())
        }.asObservable()

    /**
     * Formats data pertaining to tool use distribution among the total collected data
     */
    fun createToolPie(): ObservableList<PieChart.Data> {
        return listOf(
            PieChart.Data("Code Charts", totalCodeChartsDataSets().toDouble()),
            PieChart.Data("Zoom Maps", totalZoomMapsDataSets().toDouble())
        ).asObservable()
    }

    /**
     * Gets and formats data pertaining to distribution of what pictures were most viewed among the total collected data
     * @param pictures: Contains picture path and number of occurrences in datasets
     */
    fun queryPictureDistribution(pictures: Map<String, Int>): ObservableList<PieChart.Data> = observableListOf(
        pictures.filter { (_, count) ->
            count != 0
        }.map { (path, count) ->
            PieChart.Data(path, count.toDouble())
        }.asObservable().toList()
    )
}
