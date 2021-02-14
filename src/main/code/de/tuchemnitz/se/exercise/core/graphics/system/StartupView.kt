package de.tuchemnitz.se.exercise.core.graphics.system

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.system.StartupControllerModel
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.action
import tornadofx.bind
import tornadofx.button
import tornadofx.buttonbar
import tornadofx.combobox
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.hbox
import tornadofx.paddingAll
import tornadofx.text
import tornadofx.textfield

class StartupView : MainBarView("Software Praktikum - Gruppe 4") {
    private val configManager: ConfigManager by inject()

    object Ids {
        const val firstName = "StartupView_firstName"
        const val lastName = "StartupView_lastName"
        const val age = "StartupView_age"
        const val gender = "StartupView_gender"
    }

    private val startupControllerModel = StartupControllerModel()

    init {
        with(contentBox) {
            form {
                text("Please enter your data:") {
                    fill = Color.BLACK
                    font = Font(20.0)
                    textAlignment = TextAlignment.CENTER
                    spacing = 20.0
                }
                hbox {
                    fieldset(
                        "Firstname and Lastname",
                        FontAwesomeIconView(FontAwesomeIcon.COG),
                        Orientation.HORIZONTAL
                    ) {
                        spacing = 20.0
                        paddingAll = 20.0
                        field("Firstname") {
                            textfield {
                                id = Ids.firstName
                                bind(startupControllerModel.firstName)
                            }
                        }
                        field("Lastname: ") {
                            textfield {
                                id = Ids.lastName
                                bind(startupControllerModel.lastName)
                            }
                        }
                        field("Age:") {
                            textfield {
                                id = Ids.age
                                bind(startupControllerModel.age)
                            }
                        }
                        field("Gender:") {
                            combobox<Gender>(
                                property = startupControllerModel.gender,
                                values = Gender.values().toList()
                            ) {
                                id = Ids.gender
                            }
                        }
                        field("Vision impaired:") {
                            combobox(
                                property = startupControllerModel.visionImpaired,
                                values = listOf(true, false)
                            )
                        }
                        buttonbar {
                            button("Confirm") {
                                action {
                                    confirmInput()
                                }
                            }
                            button("Continue without providing data.") {
                                action {
                                    replaceWith(ToolSelectionView::class)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun confirmInput() {
        startupControllerModel.commit()
        val data = startupControllerModel.item
        configManager.currentUser = UserData(
            firstName = data.firstNameValue,
            lastName = data.lastNameValue,
            age = data.ageValue,
            gender = data.genderValue,
            visionImpaired = data.visionImpairedValue
        )
        replaceWith(ToolSelectionView::class)
    }
}
