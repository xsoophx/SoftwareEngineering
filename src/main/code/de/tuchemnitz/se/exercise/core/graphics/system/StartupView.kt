package de.tuchemnitz.se.exercise.core.graphics.system

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.collections.FXCollections
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
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.textfield
import java.awt.Font

class StartupView : View("Software Praktikum - Gruppe 4") {
    override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")

    companion object {
        val logger = LoggerFactory.getLogger(LoginView::class.java)
    }

    val contentBox: VBox by fxid("content")
    var ageField: ComboBox<String> by singleAssign()
    var sexField: ComboBox<String> by singleAssign()
    var visionField: ComboBox<String> by singleAssign()

    init {
        with(contentBox) {
            gridpane {
                row {
                    label("Please enter your data")
                }
                row {
                    label("Alter:")
                    ageField = combobox<String> {
                        //items = FXCollections.observableArrayList((1..99).toList().toTypedArray().contentToString())
                        items = FXCollections.observableArrayList()
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    label("Sex:")
                    sexField = combobox<String> {
                        items = FXCollections.observableArrayList(arrayListOf(1..99).toString())
                        this.value = items[0] //has to be initialized to avoid reading errors
                    }
                }
                row {
                    label("Impaired Vision:")
                    visionField = combobox<String> {
                        items = FXCollections.observableArrayList(arrayListOf(1..99).toString())
                        this.value = items[0] //has to be initialized to avoid reading errors
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

    fun printGitButton() {
        logger.info("GIT Button pressed")
    }

    override fun onDock() {
        logger.info("Docking Login Page!")
    }

    override fun onUndock() {
        logger.info("Undocking Login Page!")
    }
}