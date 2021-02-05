package de.tuchemnitz.se.exercise.core.graphics.system

import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.action
import tornadofx.button
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label
import tornadofx.passwordfield
import tornadofx.row
import tornadofx.singleAssign
import tornadofx.textfield

class LoginView : MainBarView("Software Praktikum - Gruppe 4") {
    override val root: BorderPane by fxml("/views/MainViewTemplate.fxml")

    companion object {
        val logger: Logger = LoggerFactory.getLogger(LoginView::class.java)
    }

    var loginEmailField: TextField by singleAssign()
    var loginPasswordField: PasswordField by singleAssign()

    init {
        with(contentBox) {
            gridpane {
                row {
                    label("E-Mail Address:")
                    loginEmailField = textfield()
                }
                row {
                    label("Password:")
                    loginPasswordField = passwordfield()
                }
            }

            hbox(15) {
                button("go back") {
                    action {
                        logger.info("Return Button pressed")
                    }
                }

                button("login") {
                    action {
                        logger.info("Email: " + loginEmailField.text)
                        logger.info("PW: " + loginPasswordField.text)
                    }
                }
            }
        }
    }

    override fun onDock() {
        logger.info("Docking Login Page!")
    }

    override fun onUndock() {
        logger.info("Undocking Login Page!")
    }
}
