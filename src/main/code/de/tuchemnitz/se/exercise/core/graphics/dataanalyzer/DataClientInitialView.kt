package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.DataAnalyst
import de.tuchemnitz.se.exercise.dataanalyzer.DataClientQueryModel
import de.tuchemnitz.se.exercise.dataanalyzer.DataProcessorHeatMap
import de.tuchemnitz.se.exercise.dataanalyzer.Gender
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.ButtonBar
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.action
import tornadofx.bind
import tornadofx.button
import tornadofx.buttonbar
import tornadofx.combobox
import tornadofx.field
import tornadofx.fieldset
import tornadofx.filterInput
import tornadofx.form
import tornadofx.hbox
import tornadofx.isInt
import tornadofx.paddingAll
import tornadofx.separator
import tornadofx.style
import tornadofx.text
import tornadofx.textfield
import tornadofx.vboxConstraints

/**
 * First view when launching data client
 * Allows user to select filter parameters
 * Allows user to select how the data should be visualized
 */
class DataClientInitialView : MainBarView("Willkommen beim Data Client!") {
    object Ids {
        const val maximumAge = "DataClientInitialView_maximumAge"
        const val minimumAge = "DataClientInitialView_minimumAge"
        const val gender = "DataClientInitialView_gender"
        const val add = "DataClientInitialView_add"
        const val codeCharts = "DataClientInitialView_codeCharts"
        const val zoomMaps = "DataClientInitialView_zoomMaps"
        const val imagePath = "DataClientInitialView_imagePath"
    }

    /**
     * Variables to allow the user to choose the image on which the data was collected
     */

    /** @param dataClientQueryModel is to pass information to (entered by the user)
     * @param dataAnalyst analyzes data and initializes query object
     */
    private val dataClientQueryModel = DataClientQueryModel()
    private val dataAnalyst: DataAnalyst by inject()

    init {
        with(contentBox) {
            form {
                text("Willkommen beim Datenanalyse Client!") {
                    fill = Color.MEDIUMAQUAMARINE
                    font = Font(20.0)
                    textAlignment = TextAlignment.CENTER
                    spacing = 20.0
                }
                text("Bitte wählen Sie Tool, Darstellungsmethode sowie Altersgruppe und Geschlecht der Probanden aus!") {
                    fill = Color.BLACK
                    font = Font(18.0)
                    textAlignment = TextAlignment.CENTER
                    spacing = 20.0
                }
                separator(Orientation.HORIZONTAL)

                /**
                 * Get user input, with the help of combo boxes
                 */
                hbox {
                    fieldset("Tool Selection", FontAwesomeIconView(FontAwesomeIcon.COG), Orientation.HORIZONTAL) {
                        spacing = 20.0
                        paddingAll = 20.0
                        field("Codechartstool:") {
                            combobox(
                                property = dataClientQueryModel.codeChartsActivated,
                                values = listOf(true, false)
                            ) {
                                id = Ids.codeCharts
                            }
                        }
                        field("Zoommaps:") {
                            combobox(
                                property = dataClientQueryModel.zoomMapsActivated,
                                values = listOf(true, false)
                            ) {
                                id = Ids.zoomMaps
                            }
                        }
                    }



                    fieldset(
                        "Age of the User",
                        FontAwesomeIconView(FontAwesomeIcon.USER),
                        Orientation.HORIZONTAL
                    ) {
                        spacing = 20.0
                        paddingAll = 20.0
                        alignment = Pos.CENTER

                        field("minimum Age") {
                            textfield {
                                id = Ids.minimumAge
                                filterInput { it.controlNewText.isInt() }
                                bind(dataClientQueryModel.minimumAge)
                            }
                        }
                        field("maximum Age") {
                            textfield {
                                id = Ids.maximumAge
                                filterInput { it.controlNewText.isInt() }
                                bind(dataClientQueryModel.maximumAge)
                            }
                        }
                    }

                    fieldset(
                        "Gender of the User",
                        FontAwesomeIconView(FontAwesomeIcon.FEMALE),
                        Orientation.HORIZONTAL
                    ) {
                        spacing = 20.0
                        paddingAll = 20.0
                        field("Gender:") {
                            combobox<Gender>(
                                property = dataClientQueryModel.gender,
                                values = Gender.values().toList()
                            ) {
                                id = Ids.gender
                            }
                        }
                    }

                    // TODO select image!!!

                    fieldset(
                        "Image Path",
                        FontAwesomeIconView(FontAwesomeIcon.FOLDER),
                        Orientation.HORIZONTAL
                    ) {
                        spacing = 20.0
                        paddingAll = 20.0
                        field("Image Path:") {
                            combobox<String>(
                                property = dataClientQueryModel.imagePath,
                                values = listOf(
                                    "Chameleon.jpg",
                                    "Pinguin.jpg",
                                    "kitten.jpg"
                                )
                            ) {
                                id = Ids.imagePath
                            }
                        }
                    }

                    /**
                     * Submit filter params and start rendering data
                     * Use injected @param dataAnalyst to get matching data from the db.
                     */
                    buttonbar {
                        button("START", ButtonBar.ButtonData.OK_DONE) {
                            action {
                                dataClientQueryModel.commit()
                                val data = dataAnalyst.getData(dataClientQueryModel.item)

                                // if neither zoom maps nor code charts was selected
                                var processedData = DataProcessorHeatMap().process(data)

                                // if zoom maps or code charts was selected: override processedData value
                                when (dataClientQueryModel.codeChartsActivated.value) {
                                    true -> processedData = DataProcessorHeatMap().processCodeCharts(data)
                                }
                                when (dataClientQueryModel.zoomMapsActivated.value) {
                                    true -> processedData = DataProcessorHeatMap().processZoomMaps(data)
                                }

                                println(data)
                                println(processedData)
                                println(dataClientQueryModel.imagePath.value)
                                replaceWith(
                                    find<DataClientHeatMapView>(
                                        "dataList" to processedData,
                                        "imagePath" to dataClientQueryModel.imagePath.value
                                    )
                                )
                            }
                        }
                    }
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }

                hbox {
                    text("View Metadata") {
                        fill = Color.BLACK
                        font = Font(18.0)
                        textAlignment = TextAlignment.CENTER
                        spacing = 20.0
                    }
                    button("Meta") {
                        font = Font(15.0)

                        action {
                            replaceWith<DataClientMetaDataView>()
                        }
                    }
                }
            }
            vboxConstraints {
                margin = Insets(50.0)
                paddingAll = 5.0
            }
        }
    }
}
