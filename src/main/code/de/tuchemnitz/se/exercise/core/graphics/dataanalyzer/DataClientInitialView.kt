package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.DataAnalyst
import de.tuchemnitz.se.exercise.dataanalyzer.DataClientQueryModel
import de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors.DataProcessorHeatMap
import de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors.DataProcessorMetaData
import de.tuchemnitz.se.exercise.persist.Image
import de.tuchemnitz.se.exercise.persist.data.Gender
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.ButtonBar
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import javafx.util.StringConverter
import tornadofx.action
import tornadofx.bind
import tornadofx.button
import tornadofx.buttonbar
import tornadofx.checkbox
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
        const val imageName = "DataClientInitialView_imagePath"
    }

    private val configManager: ConfigManager by inject()
    private val images = configManager.getAllImages()

    /** dataClientQueryModel is used to pass information to (entered by the user)
     * dataAnalyst analyzes data and initializes query object
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
                        checkbox("Codecharts Tool", property = dataClientQueryModel.codeChartsActivated) {
                            id = Ids.codeCharts

                            action {
                                if (!isSelected) {
                                    dataClientQueryModel.zoomMapsActivated.set(true)
                                }
                            }
                        }
                        checkbox("Zoom Maps Tool", property = dataClientQueryModel.zoomMapsActivated) {
                            id = Ids.zoomMaps
                            action {
                                if (!isSelected) {
                                    dataClientQueryModel.codeChartsActivated.set(true)
                                }
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

                    fieldset(
                        "Image",
                        FontAwesomeIconView(FontAwesomeIcon.FOLDER),
                        Orientation.HORIZONTAL
                    ) {
                        spacing = 20.0
                        paddingAll = 20.0
                        field("Image Path:") {
                            combobox<Image>(
                                property = dataClientQueryModel.image,
                                values = images
                            ) {
                                id = Ids.imageName

                                converter = object : StringConverter<Image>() {
                                    override fun toString(instance: Image): String {
                                        return instance.name
                                    }

                                    override fun fromString(name: String): Image? {
                                        return images.find { it.name == name }
                                    }
                                }
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
                                val processedData = DataProcessorHeatMap().process(data)

                                val view = find<DataClientHeatMapView>(
                                    "imagePath" to dataClientQueryModel.item.image.path,
                                    "dataList" to processedData
                                )
                                view.generateContent()
                                replaceWith(view)
                            }
                        }
                        button("View Metadata") {
                            action {
                                dataClientQueryModel.commit()
                                val data = dataAnalyst.getData(dataClientQueryModel.item)
                                val processedData = DataProcessorMetaData().process(data)

                                replaceWith(
                                    find<DataClientMetaDataView>(
                                        "dataList" to processedData
                                    )
                                )
                            }
                        }
                    }
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
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
