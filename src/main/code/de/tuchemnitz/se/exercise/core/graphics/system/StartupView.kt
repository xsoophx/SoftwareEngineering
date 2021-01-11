package de.tuchemnitz.se.exercise.core.graphics.system

import de.tu_chemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.collections.FXCollections
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.combobox
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label
import tornadofx.passwordfield
import tornadofx.px
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.style
import tornadofx.textfield
import java.awt.Font

class StartupView : View("Software Praktikum - Gruppe 4") {
    override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")

    companion object {
        val logger = LoggerFactory.getLogger(LoginView::class.java)
    }

    val contentBox: VBox by fxid("content")
    var ageField: ComboBox<Int> by singleAssign()
    var sexField: ComboBox<String> by singleAssign()
    var visionField: ComboBox<String> by singleAssign()

    init {
        with(contentBox) {
            gridpane {
                alignment=Pos.CENTER
                style{
                    fontSize = 15.px
                }
                row {
                    label("Please enter your data"){
                        style {
                            fontSize = 30.px
                        }
                    }
                }
                row {
                    label("Age:")
                    ageField = combobox<Int> {
                        items = FXCollections.observableArrayList((1..99).toList())
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    label("Sex:")
                    sexField = combobox<String> {
                        items = FXCollections.observableArrayList("Male", "Female", "Diverse")
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    label("Impaired Vision:")
                    visionField = combobox<String> {
                        items = FXCollections.observableArrayList("No", "Yes")
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row{
                    button("Confirm"){
                        action {
                            logger.info("Age: " + ageField.value.toString() + "\nSex: " + sexField.value.toString() + "\nVision impaired: " + visionField.value.toString())
                        }
                    }
                }
            }

            hbox(15) {
                button("go back") {
                    action {
                        logger.info("Return Button pressed")
                    }
                }
            }
        }
    }

    /*private val configManager: ConfigManager by inject()
    val UserConfig = //--> main>persist>data

    fun test(){
        configManager.saveConfig(UserConfig)
    }*/

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