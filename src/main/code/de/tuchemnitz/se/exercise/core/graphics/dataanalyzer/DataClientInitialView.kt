package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.dataanalyzer.*
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.combobox
import tornadofx.hbox
import tornadofx.onChange
import tornadofx.paddingAll
import tornadofx.style
import tornadofx.text
import tornadofx.vbox
import tornadofx.vboxConstraints

/**
 * First view when launching data client
 * Allows user to select filter parameters
 * Allows user to select how the data should be visualized
 */

var PROCESSED_DATA: MutableList<Coordinates> = mutableListOf()

class DataClientInitialView : View("Willkommen beim Data Client!") {
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    private val contentBox: VBox by fxid("content")

    /**
     * To hold user input values
     */
    companion object {
        var codeChartsTool = CodeCharts()
        var zoomMapsTool = ZoomMaps()
        var heatMapMethod = DataRenderHeatMap()
        var diagramMethod = DataRenderDiagram()
    }

    lateinit var tool: ITool
    lateinit var method: IMethod
    var ageRangeLower: Number = 0
    var ageRangeUpper: Number = 0
    lateinit var gender: Gender
    var picturePath: String = ""

    /**
     * for getting user input in the View
     */
    val toolList = FXCollections.observableArrayList("Code Charts", "Zoom Maps")
    val selectedTool = SimpleStringProperty()
    val renderMethodList = FXCollections.observableArrayList("Heat Map", "Diagram")
    val selectedRenderMethod = SimpleStringProperty()
    val ageRangeList = FXCollections.observableArrayList("10-20", "20-40", "40-60", "60 +")
    val selectedAgeRage = SimpleStringProperty()
    val genderList = FXCollections.observableArrayList("Male", "Female", "Others")
    val selectedGender = SimpleStringProperty()
    val imageList = FXCollections.observableArrayList("Chameleon", "Penguin", "Kitten")
    val selectedImage = SimpleStringProperty()
    /**
     * View
     */
    init {
        with(contentBox) {
            vbox {

                text("Willkommen beim Datenanalyse Client!") {
                    fill = Color.MEDIUMAQUAMARINE
                    font = Font(20.0)
                    textAlignment = TextAlignment.CENTER
                }
                text("Bitte wählen Sie Tool, Darstellungsmethode sowie Altersgruppe und Geschlecht der Probanden aus!") {
                    fill = Color.BLACK
                    font = Font(18.0)
                    textAlignment = TextAlignment.CENTER
                }

                /**
                 * Get user input
                 */
                hbox {
                    spacing = 5.0
                    combobox(selectedTool, toolList)
                    selectedTool.onChange {
                        when ("$it") {
                            "Code Charts" -> {
                                tool = codeChartsTool
                            }
                            "Zoom Maps" -> {
                                tool = zoomMapsTool
                            }
                        }
                    }
                    combobox(selectedRenderMethod, renderMethodList)
                    selectedRenderMethod.onChange {
                        when ("$it") {
                            "Heat Map" -> {
                                method = heatMapMethod
                            }
                            "Diagram" -> {
                                method = diagramMethod
                            }
                        }
                    }
                    combobox(selectedAgeRage, ageRangeList)
                    selectedAgeRage.onChange {
                        when ("$it") {
                            "10-20" -> {
                                ageRangeLower = 10
                                ageRangeUpper = 20
                            }
                            "20-40" -> {
                                ageRangeLower = 20
                                ageRangeUpper = 40
                            }
                            "40-60" -> {
                                ageRangeLower = 40
                                ageRangeUpper = 60
                            }
                            "60+" -> {
                                ageRangeLower = 60
                                ageRangeUpper = 100
                            }
                        }
                    }
                    combobox(selectedGender, genderList)
                    selectedGender.onChange {
                        when ("$it") {
                            "Male" -> {
                                gender = Gender(true, false, false)
                            }
                            "Female" -> {
                                gender = Gender(false, true, false)
                            }
                            "Other" -> {
                                gender = Gender(false, false, true)
                            }
                        }
                    }
                    combobox(selectedImage, imageList)
                    selectedGender.onChange {
                        when ("$it") {
                            "Chameleon" -> {
                                picturePath = "Chameleon.jpg"
                            }
                            "Penguin" -> {
                                picturePath = "Pinguin.jpg"
                            }
                            "Kitten" -> {
                                picturePath = "kitten.jpg"
                            }
                        }
                    }
                }

                /**
                 * Submit filter params and start rendering data
                 */
                button("START") {
                    action {

                        /**
                         * Create new Data Analyst
                         */
                        val client = DataAnalyst()

                        /**
                         * Query database by filter params
                         * data:  list of datasets
                         */
                        val data = client.getData(tool, ageRangeLower as Int, ageRangeUpper as Int, gender, picturePath)

                        /**
                         * Process the data according to render method specified by user
                         * Processed_Data receives a list of coordinates
                         * coordinates hold: image path, scaled image size and pixel coordinates of the area that was viewed
                         */

                        PROCESSED_DATA = client.process(tool, method, data)

                        /**
                         * Open a new view
                         * display selected image and render areas that were focused by subjects
                         * pass in the list of coordinates
                         */
                        replaceWith(DataClientOutputView::class)
                    }
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
                button("CLOSE") {
                    action {
                        // close data client
                    }
                }
            }
            vboxConstraints {
                margin = Insets(50.0)
                paddingAll = 5.0
            }
        }
    }

    // needed for git button
    fun printGitButton() {
    }
}
