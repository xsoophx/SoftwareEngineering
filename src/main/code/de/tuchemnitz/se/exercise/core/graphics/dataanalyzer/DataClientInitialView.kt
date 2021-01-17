package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.dataanalyzer.CodeCharts
import de.tuchemnitz.se.exercise.dataanalyzer.DataAnalyst
import de.tuchemnitz.se.exercise.dataanalyzer.DataRenderDiagram
import de.tuchemnitz.se.exercise.dataanalyzer.DataRenderHeatMap
import de.tuchemnitz.se.exercise.dataanalyzer.IMethod
import de.tuchemnitz.se.exercise.dataanalyzer.ITool
import de.tuchemnitz.se.exercise.dataanalyzer.ZoomMaps
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
    var gender: String = ""
    val toolList = FXCollections.observableArrayList("Code Charts", "Zoom Maps")
    val selectedTool = SimpleStringProperty()
    val renderMethodList = FXCollections.observableArrayList("Heat Map", "Diagram")
    val selectedRenderMethod = SimpleStringProperty()
    val ageRangeList = FXCollections.observableArrayList("10-20", "20-40", "40-60", "60 +")
    val selectedAgeRage = SimpleStringProperty()
    val genderList = FXCollections.observableArrayList("Male", "Female", "Others")
    val selectedGender = SimpleStringProperty()

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
                                println("Code Charts selected!")
                                tool = codeChartsTool
                            }
                            "Zoom Maps" -> {
                                println("Zoom Maps selected!")
                                tool = zoomMapsTool
                            }
                        }
                    }
                    combobox(selectedRenderMethod, renderMethodList)
                    selectedRenderMethod.onChange {
                        when ("$it") {
                            "Heat Map" -> {
                                println("Heat Map selected!")
                                method = heatMapMethod
                            }
                            "Diagram" -> {
                                println("Diagram selected!")
                                method = diagramMethod
                            }
                        }
                    }
                    combobox(selectedAgeRage, ageRangeList)
                    selectedAgeRage.onChange {
                        when ("$it") {
                            "10-20" -> {
                                println("Age Range 10-20 selected!")
                                ageRangeLower = 10
                                ageRangeUpper = 20
                            }
                            "20-40" -> {
                                println("Age Range 20-40 selected!")
                                ageRangeLower = 20
                                ageRangeUpper = 40
                            }
                            "40-60" -> {
                                println("Age Range 40-60 selected!")
                                ageRangeLower = 40
                                ageRangeUpper = 60
                            }
                            "60+" -> {
                                println("Age Range 60+ selected!")
                                ageRangeLower = 60
                                ageRangeUpper = 100
                            }
                        }
                    }
                    combobox(selectedGender, genderList)
                    selectedGender.onChange {
                        when ("$it") {
                            "Male" -> {
                                println("Gender Male selected!")
                                gender = "Male"
                            }
                            "Female" -> {
                                println("Gender Female selected!")
                                gender = "Female"
                            }
                            "Other" -> {
                                println("Gender Other selected!")
                                gender = "Other"
                            }
                        }
                    }
                }

                /**
                 * Submit filter params and start rendering data
                 */
                button("START") {
                    action {
                        println(tool)
                        println(method)
                        println(ageRangeLower)
                        println(ageRangeUpper)
                        println(gender)

                        /**
                         * Create new Data Analyst
                         */
                        val client = DataAnalyst()

                        /**
                         * Query database for data corresponding to filter params
                         * Receives a list of datasets
                         */
                        val data = client.getData(tool, ageRangeLower, ageRangeUpper, gender)
                        println("received data")
                        println(data)

                        /**
                         * Process the data according to render method specified by user
                         * Receivers a list of coordinates
                         */
                        val processed = client.process(method, data)
                        println("processed data")
                        println(processed)

                        /**
                         * For every set of coordinates :
                         * Create viusal representation/ rendered image of eye tracking data
                         * Open a new view with the rendered image
                         */
                        for (coordinates in processed) {
                            if (client.render(method, coordinates)) {
                                // display output.bmp -> change view
                                replaceWith(DataClientOutputView::class)
                            }
                        }
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
