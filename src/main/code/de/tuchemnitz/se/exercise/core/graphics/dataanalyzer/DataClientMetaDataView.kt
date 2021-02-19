package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.MetaDataController
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.action
import tornadofx.button
import tornadofx.hbox
import tornadofx.hboxConstraints
import tornadofx.piechart
import tornadofx.replaceChildren
import tornadofx.text
import tornadofx.useMaxWidth

/**
 * Allows the user of the data client to gain insight into the statistics behind the use of the eye tracking tool
 * Displays metadata concerning demographic of users and distribution of tool use and picture view
 */
class DataClientMetaDataView : MainBarView("Data Client Metadata") {
    /**
     * metaDataController is get and format metadata so that it can be displayed
     */
    private val metaDataController: MetaDataController by inject()
    private val dataList: List<UserData> by param()

    init {
        with(contentBox) {
            val headerBox = hbox {
                text(
                    "Total number of users: ${metaDataController.totalUsers()} - " +
                        "Total number of datasets: ${metaDataController.totalDatasets()}"
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

            /**
             * Create pie charts to visualize data
             */

            val dataBox = hbox {
                piechart("Distribution By Gender: ", metaDataController.createGenderPie(dataList))
                piechart("Distribution By Age:", metaDataController.createAgePie(dataList))
                piechart("Tool Use Distribution", metaDataController.createToolPie())
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
                        val codeChartsGenderPie =
                            metaDataController.createGenderPie(metaDataController.totalCodeChartsUsers)
                        val codeChartsAgePie = metaDataController.createAgePie(metaDataController.totalCodeChartsUsers)
                        val codeChartsPicturePie =
                            metaDataController.queryPictureDistribution(
                                metaDataController.allImages.map {
                                    it.name to metaDataController.query.queryCodeChartsImage("/${it.path.fileName}")
                                }.toMap()
                            )

                        headerBox.replaceChildren {
                            text(
                                "Total number of datasets for CODE CHARTS tool: ${metaDataController.totalCodeChartsUsers.size}"
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
                            piechart("Distribution CODE CHARTS by gender", codeChartsGenderPie)
                            piechart("Distribution CODE CHARTS by age", codeChartsAgePie)
                            piechart("Picture distribution for CODE CHARTS", codeChartsPicturePie)
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
                        val zoomGenderPie = metaDataController.createGenderPie(metaDataController.totalZoomMapsUsers)
                        val zoomAgePie = metaDataController.createAgePie(metaDataController.totalZoomMapsUsers)
                        val zoomPicturePie =
                            metaDataController.queryPictureDistribution(
                                metaDataController.allImages.map {
                                    it.name to metaDataController.query.queryZoomMapsImage("/${it.path.fileName}")
                                }.toMap()
                            )

                        headerBox.replaceChildren {
                            text(
                                "Total number of datasets for ZOOM MAPS tool: ${metaDataController.totalZoomMapsUsers.count()}"
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
                            piechart("Distribution ZOOM MAPS by gender", zoomGenderPie)
                            piechart("Distribution ZOOM MAPS by age", zoomAgePie)
                            piechart("Picture distribution for ZOOM MAPS tool", zoomPicturePie)
                        }
                    }
                }
            }
        }
    }
}
