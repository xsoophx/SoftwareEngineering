package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.system.StartupControllerModel
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.action
import tornadofx.button
import tornadofx.combobox
import tornadofx.gridpane
import tornadofx.label
import tornadofx.px
import tornadofx.required
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.style
import tornadofx.textfield

class StartupView : MainBarView("Software Praktikum - Gruppe 4") {
    override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")


    companion object {
        val logger: Logger = LoggerFactory.getLogger(LoginView::class.java)
    }

    private val configManager: ConfigManager by inject()
    private val startupControllerModel = StartupControllerModel()
    var firstNameField: TextField by singleAssign()
    var surnameField: TextField by singleAssign()
    var ageField: ComboBox<Int> by singleAssign()
    var genderField: ComboBox<Gender> by singleAssign()
    var visionField: ComboBox<Boolean> by singleAssign()

    init {
        with(contentBox) {
            mainMenuButton.isDisabled
            alignment = Pos.CENTER
            spacing = 64.0
            label("Bitte geben Sie ihre Daten an") {
                style {
                    fontSize = 32.px
                }
            }
            gridpane {
                alignment = Pos.CENTER
                style {
                    fontSize = 16.px
                }
                row {
                    label("Vorname: ")
                    textfield(
                        property = startupControllerModel.firstName
                    ).required(message = "Please enter your first Name.")
                }
                row {
                    label("Nachname: ")
                    textfield(
                        property = startupControllerModel.lastName
                    ).required(message = "Please enter your second Name.")
                }
                row {
                    label("Alter*:")
                    combobox(
                        property = startupControllerModel.age,
                        values = FXCollections.observableArrayList((1..99).toList())
                    ).required(message = "Please enter your age.")
                }
                row {
                    label("Geschlecht*:")
                    combobox<Gender>(
                        property = startupControllerModel.gender,
                        values = Gender.values().toList()
                    ).required(message = "Please enter your gender.")
                }
                row {
                    label("Sehschwäche*:")
                    combobox(
                        property = startupControllerModel.visionImpaired,
                        values = listOf(true, false)
                    )
                }
                row {
                    button("Bestätigen") {
                        action {
                            confirmInput()
                        }
                    }
                }
                row {
                    label("*: notwendig") {
                        style {
                            fontSize = 12.px
                        }
                    }
                }
            }
            button("Beenden") {
                textFill = Color.BLACK
                font = Font(22.0)
                textAlignment = TextAlignment.CENTER
                alignment = Pos.BOTTOM_CENTER
                action {
                    primaryStage.close()
                }
            }
        }
    }

    private fun confirmInput() {
        startupControllerModel.commit()
        val data = startupControllerModel.item
        val userConfig = UserData(
            firstName = data.firstNameValue,
            lastName = data.lastNameValue,
            age = data.ageValue,
            gender = data.genderValue,
            visionImpaired = data.visionImpairedValue
        )
        configManager.savePersistable(userConfig)
        replaceWith(ToolSelectionView::class)
    }

    override fun onDock() {
        logger.info("Docking Startup Page!")
    }

    override fun onUndock() {
        logger.info("Undocking Startup Page!")
    }
}
