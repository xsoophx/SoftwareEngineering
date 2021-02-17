package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.collections.ObservableList
import javafx.scene.chart.PieChart
import tornadofx.Controller
import tornadofx.asObservable
import tornadofx.observableListOf

class MetaDataController : Controller() {

    /**
     * Contains all Users which match the selected Filter
     */
    private val query: Query by inject()
    val totalCodeChartsUsers = query.codeChartsUsers()
    val totalZoomMapsUsers = query.zoomMapsUsers()

    fun totalUsers(): Int = query.userDataCollection.find().count()
    //TODO: inject query element

    fun totalDatasets(): Int =
        query.codeChartsDataCollection.find().count() + query.zoomMapsDataCollection.find().count()

    private fun totalCodeChartsDataSets(): Int = query.codeChartsDataCollection.find().count()

    private fun totalZoomMapsDataSets(): Int = query.zoomMapsDataCollection.find().count()

    data class GenderPieChartData(
        val totalMale: Int = 0,
        val totalFemale: Int = 0,
        val totalDiverse: Int = 0,
        val totalUnselected: Int = 0
    )

    val codeChartsPictures = listOf(
        "/Chameleon.jpg" to query.queryCodeChartsImage("/Chameleon.jpg"),
        "/Pinguin.jpg" to query.queryCodeChartsImage("/Pinguin.jpg"),
        "/kitten.jpg" to query.queryCodeChartsImage("/kitten.jpg"),
    )

    val zoomMapsPictures = listOf(
        "/Chameleon.jpg" to query.queryZoomMapsImage("/Chameleon.jpg"),
        "/Pinguin.jpg" to query.queryZoomMapsImage("/Pinguin.jpg"),
        "/kitten.jpg" to query.queryZoomMapsImage("/kitten.jpg"),
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

    fun createToolPie(): ObservableList<PieChart.Data> {
        return listOf(
            PieChart.Data("Code Charts", totalCodeChartsDataSets().toDouble()),
            PieChart.Data("Zoom Maps", totalZoomMapsDataSets().toDouble())
        ).asObservable()
    }

    /**
     * query database for metadata specific to CODE CHARTS tool
     */
    fun queryPictureDistribution(pictures: List<Pair<String, Int>>): ObservableList<PieChart.Data> = observableListOf(
        pictures.filter { (_, count) ->
            count != 0
        }.map { (path, count) ->
            PieChart.Data(path, count.toDouble())
        }.asObservable().toList()
    )
}
