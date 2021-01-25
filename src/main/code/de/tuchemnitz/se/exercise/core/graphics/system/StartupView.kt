package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.combobox
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label
import tornadofx.px
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.style
import tornadofx.textfield

class StartupView : View("Software Praktikum - Gruppe 4") {
    override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")

    companion object {
        val logger = LoggerFactory.getLogger(LoginView::class.java)
    }

    private val configManager: ConfigManager by inject()
    val contentBox: VBox by fxid("content")
    var firstNameField: TextField by singleAssign()
    var surnameField: TextField by singleAssign()
    var ageField: ComboBox<Int> by singleAssign()
    var genderField: ComboBox<Gender> by singleAssign()
    var visionField: ComboBox<Boolean> by singleAssign()

    init {
        with(contentBox) {
            alignment = Pos.CENTER
            spacing = 64.0
            label("Please enter your data") {
                style {
                    fontSize = 32.px
                }
            }
            gridpane {
                alignment = Pos.CENTER
                style{
                    fontSize = 16.px
                }
                row {
                    label("First Name: ")
                    firstNameField = textfield()
                }
                row {
                    label("Surname: ")
                    surnameField = textfield()
                }
                row {
                    label("Age*:")
                    ageField = combobox<Int> {
                        items = FXCollections.observableArrayList((1..99).toList())
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    label("Gender*:")
                    genderField = combobox<Gender> {
                        items = FXCollections.observableArrayList(Gender.values().toList())
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    label("Impaired Vision*:")
                    visionField = combobox<Boolean> {
                        items = FXCollections.observableArrayList(false, true)
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    button("Confirm") {
                        action {
                            confirmInput()
                        }
                    }
                }
                row {
                    label("*: mandatory"){
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

    fun confirmInput() {
        val userConfig = UserData(
            firstName = firstNameField.text,
            surname = surnameField.text,
            age = ageField.value,
            gender = genderField.value,
            visionImpaired = visionField.value
        )
        configManager.saveConfig(userConfig)
        replaceWith(ToolSelectionView::class)
    }

    fun printGitButton() {
        logger.info("GIT Button pressed")
    }

    override fun onDock() {
        logger.info("Docking Startup Page!")
    }

    override fun onUndock() {
        logger.info("Undocking Startup Page!")
    }
}