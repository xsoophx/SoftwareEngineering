package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.CodeCharts
import de.tuchemnitz.se.exercise.dataanalyzer.DataAnalyst
import de.tuchemnitz.se.exercise.dataanalyzer.DataRenderDiagram
import de.tuchemnitz.se.exercise.dataanalyzer.DataRenderHeatMap
import de.tuchemnitz.se.exercise.dataanalyzer.IMethod
import de.tuchemnitz.se.exercise.dataanalyzer.ITool
import de.tuchemnitz.se.exercise.dataanalyzer.ZoomMaps
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.ViewModel
import tornadofx.action
import tornadofx.bind
import tornadofx.button
import tornadofx.combobox
import tornadofx.compareTo
import tornadofx.field
import tornadofx.fieldset
import tornadofx.filterInput
import tornadofx.form
import tornadofx.hbox
import tornadofx.isInt
import tornadofx.onChange
import tornadofx.paddingAll
import tornadofx.required
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

    val model = ViewModel()
    private var minimumAge = model.bind { SimpleIntegerProperty() }
    private var maximumAge = model.bind { SimpleIntegerProperty() }

    val genderList = FXCollections.observableArrayList("Male", "Female", "Others")
    val selectedGender = SimpleStringProperty()

    /**
     * View
     */
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
                 * Get user input
                 */
                hbox {
                    fieldset("Tool Selection", FontAwesomeIconView(FontAwesomeIcon.COG), Orientation.HORIZONTAL) {
                        spacing = 20.0
                        padding = Insets(20.0)
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
                    }

                    fieldset("Diagram Type", FontAwesomeIconView(FontAwesomeIcon.DATABASE), Orientation.HORIZONTAL) {
                        spacing = 20.0
                        padding = Insets(20.0)

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
                    }

                    fieldset("Age of the User", FontAwesomeIconView(FontAwesomeIcon.USER), Orientation.HORIZONTAL) {
                        spacing = 20.0
                        padding = Insets(20.0)

                        field("minimum Age") {
                            textfield { filterInput { it.controlNewText.isInt() } }.bind(minimumAge)
                            textfield(minimumAge).required()
                        }
                        field("maximum Age") {
                            textfield { filterInput { it.controlNewText.isInt() } }.bind(maximumAge)
                            textfield(maximumAge).required()

                        }
                    }.apply {
                        if (minimumAge > maximumAge)
                            minimumAge = maximumAge.also { maximumAge = minimumAge }
                    }

                    fieldset(
                        "Gender of the User",
                        FontAwesomeIconView(FontAwesomeIcon.FEMALE),
                        Orientation.HORIZONTAL
                    ) {
                        spacing = 20.0
                        padding = Insets(20.0)
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
}
