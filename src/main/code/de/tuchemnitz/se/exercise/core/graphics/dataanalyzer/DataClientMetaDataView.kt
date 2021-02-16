package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.DataClientMetadataCodeCharts
import de.tuchemnitz.se.exercise.dataanalyzer.DataClientMetadataTotal
import de.tuchemnitz.se.exercise.dataanalyzer.DataClientMetadataZoomMaps
import de.tuchemnitz.se.exercise.dataanalyzer.MetaDataQuery
import javafx.collections.ObservableList
import javafx.scene.chart.PieChart
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.action
import tornadofx.asObservable
import tornadofx.button
import tornadofx.hbox
import tornadofx.hboxConstraints
import tornadofx.piechart
import tornadofx.replaceChildren
import tornadofx.text
import tornadofx.useMaxWidth

class DataClientMetaDataView : MainBarView("Data Client Metadata") {
    /**
     * query database for metadata, general and split by tool use
     */
    private val metaDataQuery: MetaDataQuery = MetaDataQuery()

    private var totalMetaData: DataClientMetadataTotal = metaDataQuery.queryMetadataTotal()

    private val codeChartsMetaData: DataClientMetadataCodeCharts = metaDataQuery.queryMetadataCodeCharts()
    private val pictureDistributionCodeCharts = createPicturePieCC()

    private val zoomMetaData: DataClientMetadataZoomMaps = metaDataQuery.queryMetadataZoomMaps()
    private val pictureDistributionZoom = createPicturePieZoom()

    /**
     * Populate pie charts with initial data. They can later be exchanged to display other data
     * relevant to more specific queries
     */
    var genderPieItems = createGenderPie()
    var agePieItems = createAgePie()
    var toolPieItems = createToolPie()

    init {
        with(contentBox) {
            val headerBox = hbox {
                text(
                    "Total number of registered users: ${totalMetaData.totalNumberOfUsers} - " +
                        "Total number of datasets: ${totalMetaData.totalNumberOfDatasets}"
                ) {
                    fill = Color.BLACK
                    font = Font(18.0)
                    textAlignment = TextAlignment.CENTER
                    spacing = 15.0
                    hboxConstraints {
                        marginTop = 50.0
                        marginLeft = 50.0
                        marginBottom = 50.0
                    }
                }
            }

            val dataBox = hbox {
                piechart("Distribution By Gender: ", genderPieItems)
                piechart("Distribution By Age:", agePieItems)
                piechart("Tool Use Distribution", toolPieItems)
            }

            hbox {
                useMaxWidth = true
                button("Show Code Charts Metadata") {
                    hboxConstraints {
                        marginTop = 50.0
                        marginLeft = 50.0
                    }
                    action {
                        /**
                         * replace pie charts with pie charts containing metadata for Code Charts Tool
                         */
                        genderPieItems = createGenderPieCC()
                        agePieItems = createAgePieCC()

                        headerBox.replaceChildren {
                            text(
                                "Total number of datasets for CODE CHARTS tool: ${codeChartsMetaData.ccTotalNumberOfDatasets}"
                            ) {
                                fill = Color.BLACK
                                font = Font(18.0)
                                textAlignment = TextAlignment.CENTER
                                spacing = 15.0
                                hboxConstraints {
                                    marginTop = 50.0
                                    marginLeft = 50.0
                                    marginBottom = 50.0
                                }
                            }
                        }
                        dataBox.replaceChildren {
                            piechart("Distribution CODE CHARTS by gender", genderPieItems)
                            piechart("Distribution CODE CHARTS by age", agePieItems)
                            piechart("Picture distribution for CODE CHARTS", pictureDistributionCodeCharts)
                        }
                    }
                }
                button("Show Zoom Maps Metadata") {
                    hboxConstraints {
                        marginTop = 50.0
                        marginLeft = 30.0
                    }
                    action {
                        /**
                         * replace pie charts with pie charts containing metadata for Zoom Maps Tool
                         */
                        genderPieItems = createGenderPieZoom()
                        agePieItems = createAgePieZoom()
                        headerBox.replaceChildren {
                            text(
                                "Total number of datasets for ZOOM MAPS tool: ${zoomMetaData.zoomTotalNumberOfDatasets}"
                            ) {
                                fill = Color.BLACK
                                font = Font(18.0)
                                textAlignment = TextAlignment.CENTER
                                spacing = 15.0
                                hboxConstraints {
                                    marginTop = 50.0
                                    marginLeft = 50.0
                                    marginBottom = 50.0
                                }
                            }
                        }
                        dataBox.replaceChildren {
                            piechart("Distribution ZOOM MAPS by gender", genderPieItems)
                            piechart("Distribution ZOOM MAPS by age", agePieItems)
                            piechart("Picture distribution for ZOOM MAPS tool", pictureDistributionZoom)
                        }
                    }
                }
            }
        }
    }

    /**
     *  Create Pie Charts for overview over all datasets
     *  One pie chart for each: distribution by gender of users, age of users and tools used
     */
    private fun createGenderPie(): ObservableList<PieChart.Data> = listOf(
        PieChart.Data("Male", totalMetaData.totalGenderMale.toDouble()),
        PieChart.Data("Female", totalMetaData.totalGenderFemale.toDouble()),
        PieChart.Data("Diverse", totalMetaData.totalGenderOther.toDouble())
    ).asObservable()

    private fun createAgePie(): ObservableList<PieChart.Data> = listOf(
        PieChart.Data("0-10", totalMetaData.totalAgeGroup0.toDouble()),
        PieChart.Data("10-20", totalMetaData.totalAgeGroup1.toDouble()),
        PieChart.Data("20-40", totalMetaData.totalAgeGroup2.toDouble()),
        PieChart.Data("40-60", totalMetaData.totalAgeGroup3.toDouble()),
        PieChart.Data("60+", totalMetaData.totalAgeGroup4.toDouble())
    ).asObservable()

    private fun createToolPie(): ObservableList<PieChart.Data> {
        val tools = metaDataQuery.queryMetadataToolUse()
        return listOf(
            PieChart.Data("Code Charts", tools.codeChartsTool.toDouble()),
            PieChart.Data("Zoom Maps", tools.zoomMapsTool.toDouble())
        ).asObservable()
    }

    /**
     *  Create pie charts for CODE CHARTS: Gender, age and viewed picture distribution
     */
    private fun createPicturePieCC(): ObservableList<PieChart.Data> {
        val pictureDistributionCC = metaDataQuery.queryPictureDistributionCC()
        val dataList = mutableListOf<PieChart.Data>()
        println(pictureDistributionCC)
        for (item in pictureDistributionCC) {
            dataList.add(PieChart.Data(item.imagePath, item.count.toDouble()))
        }
        return dataList.asObservable()
    }

    private fun createGenderPieCC(): ObservableList<PieChart.Data> = listOf(
        PieChart.Data("Male", codeChartsMetaData.ccGenderMale.toDouble()),
        PieChart.Data("Female", codeChartsMetaData.ccGenderFemale.toDouble()),
        PieChart.Data("Diverse", codeChartsMetaData.ccGenderOther.toDouble())
    ).asObservable()

    private fun createAgePieCC(): ObservableList<PieChart.Data> = listOf(
        PieChart.Data("0-10", codeChartsMetaData.ccAgeGroup0.toDouble()),
        PieChart.Data("10-20", codeChartsMetaData.ccAgeGroup1.toDouble()),
        PieChart.Data("20-40", codeChartsMetaData.ccAgeGroup2.toDouble()),
        PieChart.Data("40-60", codeChartsMetaData.ccAgeGroup3.toDouble()),
        PieChart.Data("60+", codeChartsMetaData.ccAgeGroup4.toDouble())
    ).asObservable()

    /**
     *  Create pie charts for ZOOM MAPS: Gender, age and viewed picture distribution
     */
    private fun createGenderPieZoom(): ObservableList<PieChart.Data> {
        println(zoomMetaData)
        return listOf(
            PieChart.Data("Male", zoomMetaData.zoomGenderMale.toDouble()),
            PieChart.Data("Female", zoomMetaData.zoomGenderFemale.toDouble()),
            PieChart.Data("Diverse", zoomMetaData.zoomGenderOther.toDouble())
        ).asObservable()
    }

    private fun createAgePieZoom(): ObservableList<PieChart.Data> {
        return listOf(
            PieChart.Data("0-10", zoomMetaData.zoomAgeGroup0.toDouble()),
            PieChart.Data("10-20", zoomMetaData.zoomAgeGroup1.toDouble()),
            PieChart.Data("20-40", zoomMetaData.zoomAgeGroup2.toDouble()),
            PieChart.Data("40-60", zoomMetaData.zoomAgeGroup3.toDouble()),
            PieChart.Data("60+", zoomMetaData.zoomAgeGroup4.toDouble())
        ).asObservable()
    }

    private fun createPicturePieZoom(): ObservableList<PieChart.Data> {
        val pictureDistributionCC = metaDataQuery.queryPictureDistributionZoom()
        val dataList = mutableListOf<PieChart.Data>()
        println(pictureDistributionCC)
        for (item in pictureDistributionCC) {
            dataList.add(PieChart.Data(item.imagePath, item.count.toDouble()))
        }
        return dataList.asObservable()
    }
}
