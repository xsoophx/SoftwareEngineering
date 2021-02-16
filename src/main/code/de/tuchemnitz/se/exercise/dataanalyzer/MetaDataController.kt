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
        val totalDiverse: Int = 0
    )

    private val pictures = listOf(
        "/Chameleon.jpg",
        "/Pinguin.jpg",
        "/kitten.jpg"
    )

    fun createGenderPie(userList: List<UserData>): ObservableList<PieChart.Data> =
        userList.fold(GenderPieChartData()) { piechartdata, userdata ->
            when (userdata.gender) {
                Gender.Male -> piechartdata.copy(totalMale = piechartdata.totalMale + 1)
                Gender.Female -> piechartdata.copy(totalFemale = piechartdata.totalFemale + 1)
                Gender.Diverse -> piechartdata.copy(totalDiverse = piechartdata.totalDiverse + 1)
                else -> piechartdata
            }
        }.let { (totalMale, totalFemale, totalDiverse) ->
            observableListOf(
                PieChart.Data("Male", totalMale.toDouble()),
                PieChart.Data("Female", totalFemale.toDouble()),
                PieChart.Data("Diverse", totalDiverse.toDouble())
            )
        }

    fun createAgePie(userList: List<UserData>): ObservableList<PieChart.Data> =
        userList.fold(IntArray(7)) { piechartdata, userdata ->
            val ageGroup = (userdata.age / 10).coerceAtMost(6)
            ++piechartdata[ageGroup]
            piechartdata
        }.mapIndexed { ageGroup, total ->
            val description = if (ageGroup == 6) "60+" else "${ageGroup * 10}-${ageGroup * 10 + 9}"
            PieChart.Data(description, total.toDouble())
        }.asObservable()

    fun createToolPie(): ObservableList<PieChart.Data> {
        return listOf(
            PieChart.Data("Code Charts", totalCodeChartsDataSets().toDouble()),
            PieChart.Data("Zoom Maps", totalZoomMapsDataSets().toDouble())
        ).asObservable()
    }

    fun createGenderPieCodeCharts(): ObservableList<PieChart.Data> = listOf(
        PieChart.Data("Male", query.codeChartsGenderCount(Gender.Male).toDouble()),
        PieChart.Data("Female", query.codeChartsGenderCount(Gender.Female).toDouble()),
        PieChart.Data("Diverse", query.codeChartsGenderCount(Gender.Diverse).toDouble())
    ).asObservable()

    /**
     * query database for metadata specific to CODE CHARTS tool
     */
    fun queryPictureDistributionCodeCharts(): ObservableList<PieChart.Data> = observableListOf(
        pictures.map {
            PieChart.Data(it, query.queryCodeChartsImage(it).toDouble())
        }.asObservable().toList()
    )

    /**
     * Query database for metadata specific to ZOOM MAPS Tool
     */
    fun queryPictureDistributionZoom(): ObservableList<PieChart.Data> = observableListOf(
        pictures.map {
            PieChart.Data(it, query.queryZoomMapsImage(it).toDouble())
        }.asObservable().toList()
    )
}
