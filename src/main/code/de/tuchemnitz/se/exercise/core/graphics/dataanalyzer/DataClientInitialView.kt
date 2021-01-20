package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.DataAnalyst
import de.tuchemnitz.se.exercise.dataanalyzer.Gender
import de.tuchemnitz.se.exercise.dataanalyzer.Method
import de.tuchemnitz.se.exercise.dataanalyzer.ActiveTools
import de.tuchemnitz.se.exercise.dataanalyzer.DataClientQueryModel
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
import tornadofx.radiobutton
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
    object Ids {
        const val age = "DataClientInitialView_age"
        const val gender = "DataClientInitialView_gender"
        const val add = "DataClientInitialView_add"
        const val method = "DataClientInitialView_method"
        const val tool = "DataClientInitialView_tool"
    }

    // model to pass information to (entered by the user)
    private val dataClientQueryModel = DataClientQueryModel()

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
                        paddingAll = 20.0
                        field("Tool:") {
                            radiobutton(ActiveTools.CodeCharts.name){
                                action {

                                }
                            }
                            (
                                property = dataClientQueryModel.activeTool,
                                values = ActiveTools.values().toList()
                            ) {
                                id = Ids.tool
                            }.required()
                        }

                        fieldset(
                            "Diagram Type",
                            FontAwesomeIconView(FontAwesomeIcon.DATABASE),
                            Orientation.HORIZONTAL
                        ) {
                            spacing = 20.0
                            paddingAll = 20.0

                            field("Method:") {
                                combobox<Method>(
                                    property = dataClientQueryModel.method,
                                    values = Method.values().toList()
                                ) {
                                    id = Ids.method
                                }.required()
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
                                    textfield{
                                        id = Ids.age
                                        filterInput { it.controlNewText.isInt() }
                                        bind(dataClientQueryModel.age)
                                    }.required()
                                }
                                field("maximum Age") {
                                    textfield { filterInput { it.controlNewText.isInt() } }.bind(maximumAge)
                                    // textfield(maximumAge).required()

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
                                paddingAll = 20.0
                                field("Gender:") {
                                    combobox<Gender>(
                                        property = dataClientQueryModel.gender,
                                        values = Gender.values().toList()
                                    ) {
                                        id = Ids.gender
                                    }.required()

                                }
                            }

                            /**
                             * Submit filter params and start rendering data
                             */
                            buttonbar {
                                button("START", ButtonBar.ButtonData.OK_DONE) {
                                    action {

                                        /**
                                         * Create new Data Analyst
                                         */
                                        val dataAnalyst = DataAnalyst()

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
                            }
                        }
                        vboxConstraints {
                            margin = Insets(50.0)
                            paddingAll = 5.0
                        }
                    }
                }
            }
        }
    }
}
